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

public class StoriesSteps {
    public ApiRequest apiRequest = new ApiRequest();
    InfoManager infoManager = new InfoManager();
    public ApiResponse apiResponse;

    @Before
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", infoManager.getConfig().getProperty("TOKEN"));
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
    }

    @Given("I build a {string} story request")
    public void iBuildAStoryRequest(String arg0) {
        apiRequest.setMethod(ApiMethod.valueOf(arg0));
    }

    @When("I build a story endpoint request")
    public void iBuildAStoryEndpointRequest() {
        apiRequest.setEndpoint("/projects/2505284/stories/{stories_id}");
        apiRequest.addPathParam("stories_id", "178578861");
        apiResponse = ApiManager.execute(apiRequest);
    }

    @Then("The response should be the Ok status")
    public void theResponseShouldBeTheOkStatus() {
        Assert.assertEquals(HttpStatus.SC_OK, apiResponse.getStatusCode());
    }
}
