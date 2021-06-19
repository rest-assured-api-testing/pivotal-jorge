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

import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Epic;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class EpicBases extends TestBases{
    public Epic epic;


    @BeforeMethod(onlyForGroups = "getAEpic")
    public void getAEpicConfig() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("epic_id", "4790780");
    }
    @AfterMethod(onlyForGroups = "CreateAEpic")
    public void CleanCreatedEpic() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("epic_id", epic.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }
    @BeforeMethod(onlyForGroups = "DeleteAEpic")
    public void deleteEpicsConfig() {
        apiRequest.setEndpoint("/projects/2505284/epics");
        apiRequest.setBody("{\"name\":\"ThisEpicWillBeDeleted\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        epic = apiResponse.getBody(Epic.class);
    }
    @BeforeMethod(onlyForGroups = "updateAEpic")
    public void updateEpicName() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.addPathParam("epic_id","4791804");
        apiRequest.setMethod(ApiMethod.PUT);

    }

    @AfterMethod
    public void CleanObjects() {
        epic = new Epic();
        apiRequest.clearPathParms();
    }
}
