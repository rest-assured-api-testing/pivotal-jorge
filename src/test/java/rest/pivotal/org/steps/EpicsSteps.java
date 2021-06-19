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

public class EpicsSteps {
    public ApiRequest apiRequest = new ApiRequest();
    InfoManager infoManager = new InfoManager();
    public ApiResponse apiResponse;

    @Before
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", infoManager.getConfig().getProperty("TOKEN"));
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
    }

    @Given("I build {string} request")
    public void iBuildRequest(String method) {
        apiRequest.setMethod(ApiMethod.valueOf(method));
    }

    @When("I execute {string} request")
    public void iExecuteRequest(String method) {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.addPathParam("epic_id", "4790780");
        apiResponse = ApiManager.execute(apiRequest);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String arg0) {
        Assert.assertEquals(HttpStatus.SC_OK, apiResponse.getStatusCode());
    }

    @Given("I build a {string} epic request with invalid ID")
    public void iBuildAEpicRequestWithInvalidID(String arg0) {
        apiRequest.setMethod(ApiMethod.valueOf(arg0));
    }

    @When("I execute an epic end point request")
    public void iExecuteAnEpicEndPointRequest() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.addPathParam("epic_id", "abcd1234");
        apiResponse = ApiManager.execute(apiRequest);
    }

    @Then("The epic response should be {string}")
    public void theEpicResponseShouldBe(String arg0) {
        Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, apiResponse.getStatusCode());
    }
}
