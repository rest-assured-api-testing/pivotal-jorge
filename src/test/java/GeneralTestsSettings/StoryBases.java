package GeneralTestsSettings;

import api.ApiManager;
import api.ApiMethod;
import api.ApiResponse;
import entities.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class StoryBases extends TestBases {
    public Story story;

    @BeforeMethod(onlyForGroups = "getAStory")
    public void getAStoryConfig() {
        apiRequest.setEndpoint("/projects/2505284/stories/{stories_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("stories_id", "178578861");
    }

    @AfterMethod(onlyForGroups = "CreateAStory")
    public void CleanCreatedStory() {
        apiRequest.setEndpoint("/projects/2505284/stories/{stories_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("stories_id", story.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }

    @BeforeMethod(onlyForGroups = "DeleteAStory")
    public void deleteStoryConfig() {
        apiRequest.setEndpoint("/projects/2505284/stories");
        apiRequest.setBody("{\"name\":\"ThisStoryWillBeDeleted\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        story = apiResponse.getBody(Story.class);
    }
    @BeforeMethod(onlyForGroups = "updateAStory")
    public void updateStoryName() {
        apiRequest.setEndpoint("/projects/2505284/stories/{story_id}");
        apiRequest.addPathParam("story_id","178578861");
        apiRequest.setMethod(ApiMethod.PUT);

    }

    @AfterMethod
    public void CleanObjects() {
        story = new Story();
        apiRequest.clearPathParms();
    }
}
