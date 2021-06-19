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

import GeneralTestsSettings.BlockersBases;
import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Blocker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlockersTest extends BlockersBases {
    @Test
    public void ItShouldGetAllBlockersOKStatusCode() {
        apiRequest.setEndpoint("/projects/2505284/stories/178578861/blockers/");
        apiRequest.setMethod(ApiMethod.GET);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "getABlocker")
    public void ItShouldVerifyBlockerJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Blocker blocker = apiResponse.getBody(Blocker.class);
        apiResponse.validateBodySchema("schemas/blocker.json");

    }

    @Test(groups = "getABlocker")
    public void ItShouldVerifyBlockerName() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Blocker blocker = apiResponse.getBody(Blocker.class);
        String expected = "Test Blocker";
        String actual = blocker.getDescription();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnNotFoundForInvalidBlockerID() {
        apiRequest.setEndpoint("/projects/2505284/stories/178578861/blockers/{blocker_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("blocker_id", "abcdefg123");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnNotFoundForInvalidBlockerEndpoint() {
        apiRequest.setEndpoint("/projects/2505284/stories/178578861/bloqueado/{blocker_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("blocker_id", "2894869");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "CreateABlocker")
    public void CreateABlocker() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/blockers");
        apiRequest.setBody("{\"description\":\"DeletedBlocker\",\"person_id\":3403373,\"resolved\":true}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        blocker = apiResponse.getBody(Blocker.class);
        String expected = "DeletedBlocker";
        String actual = blocker.getDescription();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "updateABlocker")
    public void UpdateABlocker() {
        apiRequest.setBody("{\"description\":\"UpdatedBlocker\"}");
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        blocker = apiResponse.getBody(Blocker.class);
        String expected = "UpdatedBlocker";
        String actual = blocker.getDescription();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidBodyForUpdateBlocker() {
        apiRequest.setEndpoint("/projects/2505284/stories/178578861/blockers/{blocker_id}");
        apiRequest.addPathParam("blocker_id", "2893412");
        apiRequest.setBody("{\"description\":\"\"}");
        apiRequest.setMethod(ApiMethod.PUT);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidEndPointForUpdateBlocker() {
        apiRequest.setEndpoint("/projects/2505284/stories/178578861/bloquer/{blocker_id}");
        apiRequest.addPathParam("blocker_id", "2893412");
        apiRequest.setBody("{\"description\":\"updateBlocker\"}");
        apiRequest.setMethod(ApiMethod.PUT);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidBodyForCreateBlocker() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/blockers");
        apiRequest.setBody("{\"description\":\"\",\"person_id\":3403373,\"resolved\":true}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidForCreateBlockerEndpoint() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/bloquer");
        apiRequest.setBody("{\"description\":\"new blocker\",\"person_id\":3403373,\"resolved\":true}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "DeleteABlocker")
    public void deleteABlocker() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/blockers/{blocker_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("blocker_id", blocker.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnBadRequestForIncorrectBlockerID() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/blockers/{blocker_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("blocker_id", "asdasdas");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnNotFoundForIncorrectBlockerEndpoint() {
        apiRequest.setEndpoint("projects/2505284/stories/178578861/bloquer/{blocker_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("blocker_id", "2893412");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
}
