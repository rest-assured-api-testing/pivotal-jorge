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

import GeneralTestsSettings.WorkSpacesBases;
import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.WorkSpaces;
import org.testng.Assert;
import org.testng.annotations.*;

public class WorkSpacesTest extends WorkSpacesBases {

    @Test
    public void ItShouldGetAllWorkSpacesOKStatusCode() {
        apiRequest.setEndpoint("/my/workspaces");
        apiRequest.setMethod(ApiMethod.GET);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnNotFoundForInvalidWorkSpaceID() {
        apiRequest.setEndpoint("/my/workspaces/{workspace_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("workspace_id", "abcd123");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnNotFoundForInvalidWorkSpaceEndpoint() {
        apiRequest.setEndpoint("/my/workespaces/{workspace_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("workspace_id", "4790780");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "getAWorkSpace")
    public void ItShouldVerifyWorkSpaceJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        WorkSpaces workSpaces = apiResponse.getBody(WorkSpaces.class);
        apiResponse.validateBodySchema("schemas/workspace.json");
    }

    @Test(groups = "getAWorkSpace")
    public void ItShouldVerifyWorkSpaceName() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        WorkSpaces workSpaces = apiResponse.getBody(WorkSpaces.class);
        String expected = "A new workspace";
        String actual = workSpaces.getName();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "CreateAWorkSpace")
    public void CreateAWorkSpace() {
        apiRequest.setEndpoint("/my/workspaces");
        apiRequest.setBody("{\"name\":\"WorkSpaceCreated\",\"project_ids\":[2505284]}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        workSpaces = apiResponse.getBody(WorkSpaces.class);
        String expected = "WorkSpaceCreated";
        String actual = workSpaces.getName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidWorkSpaceBody() {
        apiRequest.setEndpoint("/my/workspaces");
        apiRequest.setBody("\"name\":\"CreatedEpic\"");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidWorkSpaceEndPoint() {
        apiRequest.setEndpoint("/my/workespaces");
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "DeleteAWorkSpace")
    public void deleteAProject() {
        apiRequest.setEndpoint("/my/workspaces/{workspace_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("workspace_id", workSpaces.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnNotFoundForIncorrectEpicID() {
        apiRequest.setEndpoint("/my/workspaces/{workspace_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("workspace_id", "asdasdas");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnNotFoundForIncorrectWorkSpaceEndpoint() {
        apiRequest.setEndpoint("/my/workespaces/{workspace_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("workspace_id", "100");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
}
