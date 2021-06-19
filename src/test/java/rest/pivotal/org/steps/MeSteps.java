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

public class MeSteps {
    public InfoManager infoManager = new InfoManager();
    public ApiRequest apiRequest = new ApiRequest();
    public ApiResponse apiResponse;

    @Before
    public void SetGeneralConfig() {
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.setEndpoint("/me");

    }

    @Given("I build a {string} Login request")
    public void iBuildALoginRequest(String arg0) {
        apiRequest.setMethod(ApiMethod.valueOf(arg0));
    }
    @When("I execute me request")
    public void iExecuteMeRequest() {
        apiRequest.setUserName(infoManager.getConfig().getProperty("USER"));
        apiRequest.setPassword(infoManager.getConfig().getProperty("PASSWORD"));
        apiResponse = ApiManager.executeWithUserPassword(apiRequest);
    }
    @Then("The response Should return OK Status")
    public void theResponseShouldReturnOKStatus() {
        Assert.assertEquals(HttpStatus.SC_OK, apiResponse.getStatusCode());
    }

    @Given("I build a {string} login request with invalid password")
    public void iBuildALoginRequestWithInvalidPassword(String arg0) {
        apiRequest.setMethod(ApiMethod.valueOf(arg0));
    }

    @When("I execute a login end point request")
    public void iExecuteALoginEndPointRequest() {
        apiRequest.setUserName(infoManager.getConfig().getProperty("USER"));
        apiRequest.setPassword(infoManager.getConfig().getProperty("INVALID_PASSWORD"));
        apiResponse = ApiManager.executeWithUserPassword(apiRequest);
    }

    @Then("The login response should be {string}")
    public void theLoginResponseShouldBe(String arg0) {
        Assert.assertEquals(HttpStatus.SC_FORBIDDEN, apiResponse.getStatusCode());
    }
}
