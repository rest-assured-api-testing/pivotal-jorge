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
