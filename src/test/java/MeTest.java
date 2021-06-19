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
