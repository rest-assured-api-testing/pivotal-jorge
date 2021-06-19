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
import entities.Label;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LabelBases extends TestBases {
    public Label label;

    @BeforeMethod(onlyForGroups = "getALabel")
    public void getALabelConfig() {
        apiRequest.setEndpoint("projects/2505284/labels/{label_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("label_id", "23172059");
    }

    @AfterMethod(onlyForGroups = "CreateLabel")
    public void CleanCreatedLabel() {
        apiRequest.setEndpoint("projects/2505284/labels/{label_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("label_id", label.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }

    @BeforeMethod(onlyForGroups = "DeleteLabel")
    public void deleteLabelConfig() {
        apiRequest.setEndpoint("projects/2505284/labels");
        apiRequest.setBody("{\"name\":\"ThisLabelWillBeDeleted\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        label = apiResponse.getBody(Label.class);
    }

    @BeforeMethod(onlyForGroups = "updateLabel")
    public void updateLabelName() {
        apiRequest.setEndpoint("/projects/2505284/labels/{label_id}");
        apiRequest.addPathParam("label_id", "23172057");
        apiRequest.setMethod(ApiMethod.PUT);

    }

    @AfterMethod
    public void CleanObjects() {
        label = new Label();
        apiRequest.clearPathParms();
        apiRequest.clearQueryParams();
    }
}
