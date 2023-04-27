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

        // Generate listTags response
        DataCatalogClient.ListTagsPagedResponse listTags =
            dataCatalogClient.listTags(listTagsRequest);
        
        // Print each tag name
        listTags.iterateAll().forEach(
                tag -> {
                  System.out.println("Tag name : " + tag.getName());
                });
    }
    
}
