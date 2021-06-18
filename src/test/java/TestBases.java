import api.ApiRequest;
import org.testng.annotations.BeforeTest;

public class TestBases {
    static ApiRequest apiRequest = new ApiRequest();
    @BeforeTest
    public void setGeneralConfig() {
        apiRequest.addHeader("X-TrackerToken", "599e3817e376dc26345552c4aa198143");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
    }

}
