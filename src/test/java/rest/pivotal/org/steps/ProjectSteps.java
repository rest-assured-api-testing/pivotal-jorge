package rest.pivotal.org.steps;

import GeneralInfoManagement.InfoManager;
import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class ProjectSteps {
    public static ApiRequest apiRequest = new ApiRequest();
    InfoManager infoManager = new InfoManager();
    public ApiResponse apiResponse;

    @Before
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", infoManager.getConfig().getProperty("TOKEN"));
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
    }

    @Given("I build a {string} request")
    public void iBuildARequest(String arg0) {
        apiRequest.setMethod(ApiMethod.valueOf(arg0));
    }

    @When("I execute a {string} request")
    public void iExecuteARequest(String arg0) {
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.addPathParam("project_id", "2505284");
        apiResponse = ApiManager.execute(apiRequest);
    }

    @Then("the response code status should be {string}")
    public void theResponseCodeStatusShouldBe(String arg0) {
        Assert.assertEquals(HttpStatus.SC_OK, apiResponse.getStatusCode());
    }
}
