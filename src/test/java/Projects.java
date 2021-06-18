import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import entities.Project;
import org.testng.Assert;
import org.testng.annotations.*;

public class Projects {

    static ApiRequest apiRequest = new ApiRequest();
    public Project project;

    @BeforeTest
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", "599e3817e376dc26345552c4aa198143");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
    }
    @BeforeMethod(onlyForGroups = "getAProject")
    public void getAProjectsConfig() {
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("project_id", "2505284");
    }
    @Test
    public void ItShouldGetAllProjectOKStatusCode() {
        apiRequest.setEndpoint("/projects");
        apiRequest.setMethod(ApiMethod.GET);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void ShouldReturnNotFoundForInvalidProjectID() {
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("project_id", "abc123");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void ShouldReturnNotFoundForInvalidProjectEndpoint() {
        apiRequest.setEndpoint("/projectos/{project_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("project_id", "2505284");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
    @Test(groups = "getAProject")
    public void ItShouldVerifyJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Project project = apiResponse.getBody(Project.class);
        apiResponse.validateBodySchema("schemas/project.json");
    }
    @Test(groups = "getAProject")
    public void ItShouldVerifyProjectName() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Project project = apiResponse.getBody(Project.class);
        String expected = "Project";
        String actual = project.getName();
        Assert.assertEquals(actual,expected);
    }
    @Test(groups = "CreateAProject")
    public void CreateAProject() {
        apiRequest.setEndpoint("/projects");
        apiRequest.setBody("{\"name\":\"CreatedProject\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        project = apiResponse.getBody(Project.class);
        String expected = "CreatedProject";
        String actual = project.getName();
        Assert.assertEquals(actual,expected);
    }
    @AfterMethod(onlyForGroups = "CreateAProject")
    public void CleanCreatedProject() {
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("project_id",project.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }
    @Test
    public void shouldReturnBadRequestForInvalidProjectBody(){
        apiRequest.setEndpoint("/projects");
        apiRequest.setBody("\"name\":\"CreatedProject\"");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void shouldReturnNotFoundForInvalidProjectID(){
        apiRequest.setEndpoint("/projectos");
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @BeforeMethod(onlyForGroups = "DeleteAProject")
    public void deleteProjectsConfig() {
        apiRequest.setEndpoint("/projects");
        apiRequest.setBody("{\"name\":\"ThisWillBeDeleted\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        project = apiResponse.getBody(Project.class);
    }

    @Test(groups = "DeleteAProject")
    public void deleteAProject(){
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("project_id",project.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @AfterMethod
    public void CleanObjects(){
        project = new Project();
        apiRequest.clearPathParms();
    }
    @Test
    public void ShouldReturnNotFoundForIncorrectProjectID(){
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("project_id", "asdasdas");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void ShouldReturnNotFoundForIncorrectProjectEndpoint(){
        apiRequest.setEndpoint("/projectos/{project_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("project_id", "100");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
}
