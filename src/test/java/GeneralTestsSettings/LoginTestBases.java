package GeneralTestsSettings;

import GeneralInfoManagement.InfoManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import org.testng.annotations.BeforeTest;

public class LoginTestBases {
    public InfoManager infoManager = new InfoManager();
    public static ApiRequest apiRequest = new ApiRequest();
    public ApiResponse apiResponse;
    @BeforeTest
    public void SetGeneralConfig(){
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.setEndpoint("/me");

    }
}
