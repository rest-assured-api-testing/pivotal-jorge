import GeneralTestsSettings.StoryBases;
import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoryTest extends StoryBases {

    @Test
    public void ItShouldGetAllEpicsOKStatusCode() {
        apiRequest.setEndpoint("/projects/2505284/stories");
        apiRequest.setMethod(ApiMethod.GET);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 200;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual, expected);
    }
    @Test(groups = "getAStory")
    public void ItShouldVerifyEpicJsonSchema() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Story story = apiResponse.getBody(Story.class);
        apiResponse.validateBodySchema("schemas/story.json");

    }
    @Test(groups = "getAStory")
    public void ItShouldVerifyEpicName() {
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Story story = apiResponse.getBody(Story.class);
        String expected = "history2";
        String actual = story.getName();
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void ItShouldReturnBatRequestForInvalidEpicID() {
        apiRequest.setEndpoint("/projects/2505284/stories/{stories_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("stories_id", "abcd123");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void ItShouldReturnNotFoundForInvalidEpicEndpoint() {
        apiRequest.setEndpoint("/projects/2505284/estories/{stories_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("stories_id", "4790780");
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @Test(groups = "CreateAStory")
    public void CreateAEpic() {
        apiRequest.setEndpoint("/projects/2505284/stories");
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        story = apiResponse.getBody(Story.class);
        String expected = "CreatedEpic";
        String actual = story.getName();
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void shouldReturnBadRequestForInvalidBody(){
        apiRequest.setEndpoint("/projects/2505284/stories");
        apiRequest.setBody("\"name\":\"CreatedEpic\"");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void shouldReturnNotFoundForInvalidEpicID(){
        apiRequest.setEndpoint("/projects/2505284/estories");
        apiRequest.setBody("{\"name\":\"CreatedEpic\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }

    @Test(groups = "DeleteAStory")
    public void deleteAEpic(){
        apiRequest.setEndpoint("/projects/2505284/stories/{stories_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("stories_id", story.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 204;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void ShouldReturnBadRequestForIncorrectEpicID(){
        apiRequest.setEndpoint("/projects/2505284/stories/{stories_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("stories_id", "asdasdas");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 400;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void ShouldReturnNotFoundForIncorrectEpicEndpoint(){
        apiRequest.setEndpoint("/projects/2505284/estories/{stories_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("stories_id", "100");
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        int expected = 404;
        int actual = apiResponse.getStatusCode();
        Assert.assertEquals(actual,expected);
    }
}
