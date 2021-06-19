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
    public void SetGeneralConfig() {
        apiRequest.setBaseUri(infoManager.getConfig().getProperty("BASE_URI"));
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.setEndpoint("/me");

    }
}
