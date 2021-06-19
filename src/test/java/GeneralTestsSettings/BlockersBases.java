package GeneralTestsSettings;

import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Blocker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BlockersBases extends TestBases{
    public Blocker blocker;

    @BeforeMethod(onlyForGroups = "getABlocker")
    public void getABlockerConfig() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/blockers/{blocker_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("blocker_id", "2894869");
    }
    @AfterMethod(onlyForGroups = "CreateABlocker")
    public void CleanCreatedBlocker() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/blockers/{blocker_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("blocker_id", blocker.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }
    @BeforeMethod(onlyForGroups = "DeleteABlocker")
    public void deleteBlockersConfig() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/blockers");
        apiRequest.setBody("{\"description\":\"ThisBlockerWillBeDeleted\",\"person_id\":3403373,\"resolved\":true}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        blocker = apiResponse.getBody(Blocker.class);
    }
    @BeforeMethod(onlyForGroups = "updateABlocker")
    public void updateBlockerName() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/blockers/{blocker_id}");
        apiRequest.addPathParam("blocker_id","2893412");
        apiRequest.setMethod(ApiMethod.PUT);

    }
    @AfterMethod
    public void CleanObjects(){
        blocker = new Blocker();
        apiRequest.clearPathParms();
    }
}
