package entities;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Comments {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String story_id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String text;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String person_id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String created_at;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String updated_at;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
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
