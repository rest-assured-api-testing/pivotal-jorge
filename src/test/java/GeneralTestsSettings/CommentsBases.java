package GeneralTestsSettings;

import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Comments;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommentsBases extends TestBases{
    public Comments comments;
    @BeforeMethod(onlyForGroups = "getAComment")
    public void getACommentConfig() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments/{comment_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("comment_id", "225009369");
    }

    @AfterMethod(onlyForGroups = "CreateComment")
    public void CleanCreatedComment() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments/{comment_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("comment_id", comments.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }

    @BeforeMethod(onlyForGroups = "DeleteComment")
    public void deleteCommentConfig() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments");
        apiRequest.setBody("{\"text\":\"I´m a comment\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        comments = apiResponse.getBody(Comments.class);
    }

    @BeforeMethod(onlyForGroups = "updateComment")
    public void updateCommentText() {
        apiRequest.setEndpoint("/projects/2510264/stories/178595534/comments/{comment_id}");
        apiRequest.addPathParam("comment_id", "225009376");
        apiRequest.setMethod(ApiMethod.PUT);

    }

    @AfterMethod
    public void CleanObjects() {
        comments = new Comments();
        apiRequest.clearPathParms();
    }
}
