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
package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Blocker {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String kind;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String story_id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String person_id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String resolved;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String created_at;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String updated_at;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStory_id() {
        return story_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
