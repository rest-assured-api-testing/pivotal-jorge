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

public class AccountSteps {
    public  ApiRequest apiRequest = new ApiRequest();
    InfoManager infoManager = new InfoManager();
    ApiResponse apiResponse;

    @Before
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", infoManager.getConfig().getProperty("TOKEN"));
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
    }

    @Given("I build a {string} account request")
    public void iBuildAAccountRequest(String arg0) {
        apiRequest.setMethod(ApiMethod.valueOf(arg0));
    }

    @When("I execute a Account endpoint request")
    public void iExecuteAAccountEndpointRequest() {
        apiRequest.setEndpoint("/accounts/{account_id}");
        apiRequest.addPathParam("account_id", "1155196");
        apiResponse = ApiManager.execute(apiRequest);
    }

    @Then("The Response status should be {string}")
    public void theResponseStatusShouldBe(String arg0) {
        Assert.assertEquals(HttpStatus.SC_OK, apiResponse.getStatusCode());
    }

    @Given("I build a {string} account request with invalid ID")
    public void iBuildAAccountRequestWithInvalidID(String arg0) {
        apiRequest.setMethod(ApiMethod.valueOf(arg0));
    }

    @When("I execute an account end point request")
    public void iExecuteAnAccountEndPointRequest() {
        apiRequest.setEndpoint("/accounts/{account_id}");
        apiRequest.addPathParam("account_id", "abc123");
        apiResponse = ApiManager.execute(apiRequest);
    }

    @Then("The response should be {string}")
    public void theResponseShouldBe(String arg0) {
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, apiResponse.getStatusCode());
    }
}
