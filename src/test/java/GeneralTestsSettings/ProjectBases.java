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
import entities.Project;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ProjectBases extends TestBases {
    public Project project;

    @BeforeMethod(onlyForGroups = "getAProject")
    public void getAProjectsConfig() {
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("project_id", "2505284");
    }

    @BeforeMethod(onlyForGroups = "updateAProject")
    public void updateAProjectName() {
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.setMethod(ApiMethod.PUT);
        apiRequest.addPathParam("project_id", "2504485");
    }

    @AfterMethod(onlyForGroups = "CreateAProject")
    public void CleanCreatedProject() {
        apiRequest.setEndpoint("/projects/{project_id}");
        apiRequest.setBody("");
        apiRequest.addPathParam("project_id", project.getId());
        apiRequest.setMethod(ApiMethod.DELETE);
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
    }

    @BeforeMethod(onlyForGroups = "DeleteAProject")
    public void deleteProjectsConfig() {
        apiRequest.setEndpoint("/projects");
        apiRequest.setBody("{\"name\":\"ThisWillBeDeleted\"}");
        apiRequest.setMethod(ApiMethod.POST);
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        project = apiResponse.getBody(Project.class);
    }

    @AfterMethod
    public void CleanObjects() {
        project = new Project();
        apiRequest.clearPathParms();
    }
}
