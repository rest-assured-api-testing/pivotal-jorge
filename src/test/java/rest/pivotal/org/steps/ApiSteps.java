package rest.pivotal.org.steps;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.testng.Assert;
public class ApiSteps {
    ApiRequest apiRequest = new ApiRequest();
    ApiResponse apiResponse;

    private String token = "599e3817e376dc26345552c4aa198143";
    private String baseUri ="https://www.pivotaltracker.com/services/v5";
    @Given("I build {string} request")
    public void iBuildRequest(String method) {
        apiRequest.addHeader("X-TrackerToken", "599e3817e376dc26345552c4aa198143");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
        apiRequest.setMethod(ApiMethod.valueOf(method));
    }

    @When("I execute {string} request")
    public void iExecuteRequest(String method) {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.addPathParam("epic_id", "4790780");
        apiResponse=ApiManager.execute(apiRequest);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String arg0) {
        Assert.assertEquals( HttpStatus.SC_OK,apiResponse.getStatusCode());
    }
}
