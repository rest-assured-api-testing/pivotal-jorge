import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import entities.WorkSpaces;
import org.testng.Assert;
import org.testng.annotations.*;

public class WorkSpacesTest {

    static ApiRequest apiRequest = new ApiRequest();
    public WorkSpaces workSpaces;

    @BeforeTest
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", "599e3817e376dc26345552c4aa198143");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
    }
    @BeforeMethod(onlyForGroups = "getAWorkSpace")
    public void getAProjectsConfig() {
        apiRequest.setEndpoint("/my/workspaces/{workspace_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("workspace_id", "876161");
    }
    @Test
    public void ItShouldGetAllWorkSpacesOKStatusCode() {
        apiRequest.setEndpoint("/my/workspaces");
        apiRequest.setMethod(ApiMethod.GET);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
    @Test(groups = "getAWorkSpace")
    public void ItShouldVerifyWorkSpaceJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        WorkSpaces workSpaces = apiResponse.getBody(WorkSpaces.class);
        apiResponse.validateBodySchema("schemas/workspace.json");
    }
    @Test(groups = "getAWorkSpace")
    public void ItShouldVerifyWorkSpaceName() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        WorkSpaces workSpaces = apiResponse.getBody(WorkSpaces.class);
        String expected = "A new workspace";
        String actual = workSpaces.getName();
        Assert.assertEquals(actual,expected);
    }
    @Test(groups = "CreateAWorkSpace")
    public void CreateAWorkSpace() {
        apiRequest.setEndpoint("/my/workspaces");
        apiRequest.setBody("{\"name\":\"WorkSpaceCreated\",\"project_ids\":[2505284]}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        workSpaces = apiResponse.getBody(WorkSpaces.class);
        String expected = "WorkSpaceCreated";
        String actual = workSpaces.getName();
        Assert.assertEquals(actual,expected);
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

    @Test(groups = "DeleteAWorkSpace")
    public void deleteAProject(){
        apiRequest.setEndpoint("/my/workspaces/{workspace_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("workspace_id",workSpaces.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @AfterMethod
    public void CleanObjects(){
        workSpaces = new WorkSpaces();
        apiRequest.clearPathParms();
    }
}
