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
import java.util.List;

public class Me {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String Kind;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String initials;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private TimeZone time_zone;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String api_token;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String has_google_identity;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<Account> accounts;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<Project> projects;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String receives_in_app_notifications;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String created_at;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String updated_at;

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TimeZone getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(TimeZone time_zone) {
        this.time_zone = time_zone;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getHas_google_identity() {
        return has_google_identity;
    }

    public void setHas_google_identity(String has_google_identity) {
        this.has_google_identity = has_google_identity;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReceives_in_app_notifications() {
        return receives_in_app_notifications;
    }

    public void setReceives_in_app_notifications(String receives_in_app_notifications) {
        this.receives_in_app_notifications = receives_in_app_notifications;
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
