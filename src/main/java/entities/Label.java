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

public class Label {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String project_id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String name;
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

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
