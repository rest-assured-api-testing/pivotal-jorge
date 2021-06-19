package PivotalTests; /**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Jorge Rodrigo Cáceres Velasco
 */

import GeneralTestsSettings.LoginTestBases;
import api.ApiManager;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MeTest extends LoginTestBases {

    @Test
    public void LoginShouldReturnOKStatus() {
        apiRequest.setUserName(infoManager.getConfig().getProperty("USER"));
        apiRequest.setPassword(infoManager.getConfig().getProperty("PASSWORD"));
        apiResponse = ApiManager.executeWithUserPassword(apiRequest);
        int expected = HttpStatus.SC_OK;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void LoginShouldReturnForbiddenStatusForInvalidPASSWORD() {
        apiRequest.setUserName(infoManager.getConfig().getProperty("USER"));
        apiRequest.setPassword(infoManager.getConfig().getProperty("INVALID_PASSWORD"));
        apiResponse = ApiManager.executeWithUserPassword(apiRequest);
        int expected = HttpStatus.SC_FORBIDDEN;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void LoginShouldForbiddenStatusForInvalidUser() {
        apiRequest.setUserName(infoManager.getConfig().getProperty("INVALID_USER"));
        apiRequest.setPassword(infoManager.getConfig().getProperty("PASSWORD"));
        apiResponse = ApiManager.executeWithUserPassword(apiRequest);
        int expected = HttpStatus.SC_FORBIDDEN;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
}
