package PivotalTests;

import GeneralTestsSettings.CommentsBases;
import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Comments;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommentsTest extends CommentsBases {
    @Test
    public void ItShouldGetAllCommentsOKStatusCode() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments");
        apiRequest.setMethod(ApiMethod.GET);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "getAComment")
    public void ItShouldVerifyCommentJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Comments comments = apiResponse.getBody(Comments.class);
        apiResponse.validateBodySchema("schemas/comments.json");

    }

    @Test(groups = "getAComment")
    public void ItShouldVerifyCommentText() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Comments comments = apiResponse.getBody(Comments.class);
        String expected = "If this is a consular ship, then where is the ambassador \uD83D\uDC45?";
        String actual = comments.getText();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "updateComment")
    public void UpdateAComment() {
        apiRequest.setBody("{\"text\":\"I´m an updated comment\"}");
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        comments = apiResponse.getBody(Comments.class);
        String expected = "I´m an updated comment";
        String actual = comments.getText();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidBodyForUpdateComment() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments/{comment_id}");
        apiRequest.addPathParam("comment_id", "225009376");
        apiRequest.setBody("{\"text\":\"\"}");
        apiRequest.setMethod(ApiMethod.PUT);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidBodyForUpdateComment() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comentario/{comment_id}");
        apiRequest.addPathParam("comment_id", "225009376");
        apiRequest.setBody("{\"text\":\"goodComment\"}");
        apiRequest.setMethod(ApiMethod.PUT);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnBatRequestForInvalidCommentID() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments/{comment_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("comment_id", "abc123");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ItShouldReturnNotFoundForInvalidCommentEndpoint() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comentarios/{comment_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("comment_id", "225009369");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "CreateComment")
    public void CreateAComment() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments");
        apiRequest.setBody("{\"text\":\"Created comment\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        comments = apiResponse.getBody(Comments.class);
        String expected = "Created comment";
        String actual = comments.getText();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnBadRequestForInvalidCommentBody() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments");
        apiRequest.setBody("{\"text\":\"\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNotFoundForInvalidCommentID() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comentarios");
        apiRequest.setBody("{\"text\":\"goodComment\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "DeleteComment")
    public void deleteAComment() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments/{comment_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("comment_id", comments.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnBadRequestForIncorrectCommentID() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments/{comment_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("comment_id", "abcd123");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ShouldReturnNotFoundForIncorrectCommentEndpoint() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comentarios/{comment_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("comment_id", "225009376");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
}
