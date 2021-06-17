import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import entities.Project;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Projects {
    ApiRequest apiRequest = new ApiRequest();
    @BeforeMethod
    public void setGeneralConfig() {
        apiRequest = new ApiRequest();
        apiRequest.addHeader("X-TrackerToken", "599e3817e376dc26345552c4aa198143");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
    }

    @Test
    public void getAllProjects() {
        apiRequest.setEndpoint("/projects");
        apiRequest.setMethod(ApiMethod.GET);

        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void getAccount() {
        apiRequest.setEndpoint("/accounts/{account_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("account_id","1155196");

        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
}
