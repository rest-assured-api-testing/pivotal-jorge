package GeneralTestsSettings;

import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.WorkSpaces;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WorkSpacesBases extends TestBases{

    public WorkSpaces workSpaces;

    @BeforeMethod(onlyForGroups = "getAWorkSpace")
    public void getAProjectsConfig() {
        apiRequest.setEndpoint("/my/workspaces/{workspace_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("workspace_id", "876161");
    }
    @AfterMethod(onlyForGroups = "CreateAWorkSpace")
    public void CleanCreatedWorkSpace() {
        apiRequest.setEndpoint("/my/workspaces/{workspace_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("workspace_id",workSpaces.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }
    @BeforeMethod(onlyForGroups = "DeleteAWorkSpace")
    public void deleteProjectsConfig() {
        apiRequest.setEndpoint("/my/workspaces");
        apiRequest.setBody("{\"name\":\"WorkSpaceToBeDeleted\",\"project_ids\":[2505284]}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        workSpaces = apiResponse.getBody(WorkSpaces.class);
    }
    @AfterMethod
    public void CleanObjects(){
        workSpaces = new WorkSpaces();
        apiRequest.clearPathParms();
    }
}
