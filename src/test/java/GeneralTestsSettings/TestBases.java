package GeneralTestsSettings;

import api.ApiRequest;
import GeneralInfoManagement.InfoManager;
import org.testng.annotations.BeforeTest;

public class TestBases {
    public static ApiRequest apiRequest = new ApiRequest();
    InfoManager infoManager = new InfoManager();
    @BeforeTest
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", infoManager.getConfig().getProperty("TOKEN"));
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
    }

}
