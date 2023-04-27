import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.StatusCode.Code;
import com.google.cloud.datacatalog.v1.*;
import org.threeten.bp.Duration;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class ListTags {
    public static void main(String[] args) throws IOException {
        // TODO(developer): Replace these variables before running the sample.
        String projectId = "MY_PROJECT_ID";
        String location = "MY_LOCATION";
        String entryGroupId = "MY_ENTRY_GROUP"
        String entryId = "MY_ENTRY"
        EntryName entryName = EntryName.of(projectId, location, entryGroupId, entryId);
        listTags(entryName);
    }
    public static void listTags(EntryName entryName) {

        // Sets retry multiplier, retry delays, and timeout
        RetrySettings retrySettings = RetrySettings.newBuilder()
            .setRetryDelayMultiplier(1.3)
            .setInitialRetryDelay(Duration.ofMillis(100L))
            .setMaxRetryDelay(Duration.ofMillis(2 * 60 * 1000L)) // 2 minutes
            .setTotalTimeout(Duration.ofMillis(9 * 60 * 1000L)) // 9 minutes
            .build();
        // Sets retryable codes
        HashSet<Code> retryableCodes = new HashSet<>(Arrays.asList(
                Code.RESOURCE_EXHAUSTED,
                Code.INTERNAL,
                Code.UNAVAILABLE
        ));
        // Configures retry settings and retryable codes
        DataCatalogSettings.Builder dataCatalogSettingsBuilder = DataCatalogSettings.newBuilder();
        dataCatalogSettingsBuilder.listTagsSettings()
            .setRetrySettings(retrySettings)
            .setRetryableCodes(retryableCodes);
        
        DataCatalogClient dataCatalogClient = DataCatalogClient.create(dataCatalogSettingsBuilder.build());
        
        ListTagsRequest listTagsRequest = ListTagsRequest.newBuilder()
               // Explicitly set pageSize to the max value 1K to avoid making multiple API calls        
               // when entries have more than 10 tags.   
            .setPageSize(1000)
            .setParent(entryName.toString())
            .build();

        // Generate listTags response
        DataCatalogClient.ListTagsPagedResponse listTags =
            dataCatalogClient.listTags(listTagsRequest);
        
        // Print each tag name
        listTags.iterateAll().forEach(
                tag -> {
                  System.out.println("Tag name : " + tag.getName());
                });
        // Test that retry 
    }
    
}
