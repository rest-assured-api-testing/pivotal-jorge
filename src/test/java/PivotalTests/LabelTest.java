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

import GeneralTestsSettings.LabelBases;
import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Label;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LabelTest extends LabelBases {
    @Test
    public void ItShouldGetAllLabelsOKStatusCode() {
        apiRequest.setEndpoint("/projects/2505284/labels");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addQueryParam("date_format", "millis");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "getALabel")
    public void ItShouldVerifyLabelJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Label label = apiResponse.getBody(Label.class);
        apiResponse.validateBodySchema("schemas/label.json");

    }

    @Test(groups = "getALabel")
    public void ItShouldVerifyLabelName() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Label label = apiResponse.getBody(Label.class);
        String expected = "label test";
        String actual = label.getName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnNotFoundForInvalidLabelID() {
        apiRequest.setEndpoint("projects/2505284/labels/{label_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("label_id", "abcd123");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnNotFoundForInvalidLabelEndpoint() {
        apiRequest.setEndpoint("projects/2505284/labeles/{label_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("label_id", "23172059");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "CreateLabel")
    public void CreateALabel() {
        apiRequest.setEndpoint("projects/2505284/labels");
        apiRequest.setBody("{\"name\":\"labelcreated\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        label = apiResponse.getBody(Label.class);
        String expected = "labelcreated";
        String actual = label.getName();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "updateLabel")
    public void UpdateALabel() {
        apiRequest.setBody("{\"name\":\"updatedlabel\"}");
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        label = apiResponse.getBody(Label.class);
        String expected = "updatedlabel";
        String actual = label.getName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidBodyUpdateLabel() {
        apiRequest.setEndpoint("projects/2505284/labels/{label_id}");
        apiRequest.addPathParam("label_id", "23172057");
        apiRequest.setBody("{\"name\":\"\"}");
        apiRequest.setMethod(ApiMethod.PUT);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidBodyUpdateLabel() {
        apiRequest.setEndpoint("projects/2505284/labeles/{label_id}");
        apiRequest.addPathParam("label_id", "23172057");
        apiRequest.setBody("{\"name\":\"label007\"}");
        apiRequest.setMethod(ApiMethod.PUT);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidBodyCreateLabel() {
        apiRequest.setEndpoint("projects/2505284/labels");
        apiRequest.setBody("{\"name\":\"\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidLabelEndpoint() {
        apiRequest.setEndpoint("projects/2505284/labeles");
        apiRequest.setBody("{\"name\":\"label007\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "DeleteLabel")
    public void deleteALabel() {
        apiRequest.setEndpoint("projects/2505284/labels/{label_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("label_id", label.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnBadRequestForIncorrectLabelID() {
        apiRequest.setEndpoint("projects/2505284/labels/{label_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("label_id", "asdasdas");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnNotFoundForIncorrectLabelEndpoint() {
        apiRequest.setEndpoint("projects/2505284/labeles/{label_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("label_id", "23172057");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
}
