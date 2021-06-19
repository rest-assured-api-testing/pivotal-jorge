/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Jorge Rodrigo Cáceres Velasco
 */

import GeneralInfoManagement.InfoManager;
import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import entities.Account;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AccountTest {
    public static ApiRequest apiRequest = new ApiRequest();
    InfoManager infoManager = new InfoManager();
    ApiResponse apiResponse;

    @BeforeTest
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", infoManager.getConfig().getProperty("TOKEN"));
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
    }

    @Test
    public void ShouldReturnOKStatusForAccountInfo() {
        apiRequest.setEndpoint("/accounts/{account_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("account_id", "1155196");
        apiResponse = ApiManager.execute(apiRequest);
        int expected = HttpStatus.SC_OK;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void ShouldVerifyJsonSchema() {
        apiRequest.setEndpoint("/accounts/{account_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("account_id", "1155196");
        apiResponse = ApiManager.execute(apiRequest);
        Account account = apiResponse.getBody(Account.class);
        apiResponse.validateBodySchema("schemas/account.json");

    }

    @Test
    public void ShouldVerifyAccountName() {
        apiRequest.setEndpoint("/accounts/{account_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("account_id", "1155196");
        apiResponse = ApiManager.execute(apiRequest);
        Account account = apiResponse.getBody(Account.class);
        String expected = "Untitled";
        String actual = account.getName();
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void ShouldReturnNotFoundForInvalidAccountID() {
        apiRequest.setEndpoint("/accounts/{account_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("account_id", "abc123");
        apiResponse = ApiManager.execute(apiRequest);
        int expected = HttpStatus.SC_NOT_FOUND;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldNotFoundStatusForInvalidEndPoint() {
        apiRequest.setEndpoint("/cuentas/{account_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("account_id", "1155196");
        apiResponse = ApiManager.execute(apiRequest);
        int expected = HttpStatus.SC_NOT_FOUND;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
}
