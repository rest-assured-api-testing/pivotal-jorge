import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import entities.Epic;
import org.testng.Assert;
import org.testng.annotations.*;

public class Epics {

    static ApiRequest apiRequest = new ApiRequest();
    public Epic epic;

    @BeforeTest
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", "599e3817e376dc26345552c4aa198143");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
    }
    @BeforeMethod(onlyForGroups = "getAEpic")
    public void getAProjectsConfig() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("epic_id", "4790780");
    }
    @Test
    public void ItShouldGetAllEpicsOKStatusCode() {
        apiRequest.setEndpoint("/projects/2505284");
        apiRequest.setMethod(ApiMethod.GET);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
    @Test(groups = "getAEpic")
    public void ItShouldVerifyJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Epic epic = apiResponse.getBody(Epic.class);
        apiResponse.validateBodySchema("schemas/epic.json");

    }
    @Test(groups = "getAEpic")
    public void ItShouldVerifyProjectName() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Epic epic = apiResponse.getBody(Epic.class);
        String expected = "Test Epic";
        String actual = epic.getName();
        Assert.assertEquals(actual,expected);
    }
    @Test(groups = "CreateAEpic")
    public void CreateAProject() {
        apiRequest.setEndpoint("/projects/2505284/epics");
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        epic = apiResponse.getBody(Epic.class);
        String expected = "CreatedEpic";
        String actual = epic.getName();
        Assert.assertEquals(actual,expected);
    }
    @AfterMethod(onlyForGroups = "CreateAEpic")
    public void CleanCreatedProject() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("epic_id", epic.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }
    @BeforeMethod(onlyForGroups = "DeleteAEpic")
    public void deleteProjectsConfig() {
        apiRequest.setEndpoint("/projects/2505284/epics");
        apiRequest.setBody("{\"name\":\"ThisEpicWillBeDeleted\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        epic = apiResponse.getBody(Epic.class);
    }

    @Test(groups = "DeleteAEpic")
    public void deleteAProject(){
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("epic_id", epic.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @AfterMethod
    public void CleanObjects(){
        epic = new Epic();
        apiRequest.clearPathParms();
    }
}
