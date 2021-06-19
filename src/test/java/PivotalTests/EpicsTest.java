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

import GeneralTestsSettings.EpicBases;
import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Epic;
import org.testng.Assert;
import org.testng.annotations.*;

public class EpicsTest extends EpicBases {

    @Test
    public void ItShouldGetAllEpicsOKStatusCode() {
        apiRequest.setEndpoint("/projects/2505284/epics");
        apiRequest.setMethod(ApiMethod.GET);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "getAEpic")
    public void ItShouldVerifyEpicJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Epic epic = apiResponse.getBody(Epic.class);
        apiResponse.validateBodySchema("schemas/epic.json");

    }

    @Test(groups = "getAEpic")
    public void ItShouldVerifyEpicName() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Epic epic = apiResponse.getBody(Epic.class);
        String expected = "Test Epic";
        String actual = epic.getName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnBatRequestForInvalidEpicID() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("epic_id", "abcd123");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnNotFoundForInvalidEpicEndpoint() {
        apiRequest.setEndpoint("/projects/2505284/pics/{epic_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("epic_id", "4790780");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "CreateAEpic")
    public void CreateAEpic() {
        apiRequest.setEndpoint("/projects/2505284/epics");
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        epic = apiResponse.getBody(Epic.class);
        String expected = "CreatedEpic";
        String actual = epic.getName();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "updateAEpic")
    public void UpdateAEpic() {
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        epic = apiResponse.getBody(Epic.class);
        String expected = "CreatedEpic";
        String actual = epic.getName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidBodyUpdateEpic() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.addPathParam("epic_id", "4791804");
        apiRequest.setBody("{\"name\":\"\"}");
        apiRequest.setMethod(ApiMethod.PUT);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidBodyUpdateEpic() {
        apiRequest.setEndpoint("/projects/2505284/epicos/{epic_id}");
        apiRequest.addPathParam("epic_id", "4791804");
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        apiRequest.setMethod(ApiMethod.PUT);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidBody() {
        apiRequest.setEndpoint("/projects/2505284/epics");
        apiRequest.setBody("\"name\":\"CreatedEpic\"");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidEpicID() {
        apiRequest.setEndpoint("/projects/2505284/pics");
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "DeleteAEpic")
    public void deleteAEpic() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("epic_id", epic.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnBadRequestForIncorrectEpicID() {
        apiRequest.setEndpoint("/projects/2505284/epics/{epic_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("epic_id", "asdasdas");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnNotFoundForIncorrectEpicEndpoint() {
        apiRequest.setEndpoint("/projects/2505284/epiks/{epic_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("epic_id", "100");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
}
