package com.example.springbootsql.entity;

import java.util.List;

/**
 * Created by: Changze
 * Date: 2019/4/20
 * Time: 23:44
 */
public class FbUser {
    private String name;
    private String facebookUrl;
    private String relationship;
    private String favorite_quotes;
    private String introduction;
    private String gender;
    private String language;
    private String interest_in;
    private String religious_views;
    private String political_views;
    private String birth_day;
    private String professional_skills;
    private String ui_mask;
    private List<String> friendsList;

    public List<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<String> friendsList) {
        this.friendsList = friendsList;
    }

    public String getUi_mask() {
        return ui_mask;
    }

    public void setUi_mask(String ui_mask) {
        this.ui_mask = ui_mask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getFavorite_quotes() {
        return favorite_quotes;
    }

    public void setFavorite_quotes(String favorite_quotes) {
        this.favorite_quotes = favorite_quotes;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getInterest_in() {
        return interest_in;
    }

    public void setInterest_in(String interest_in) {
        this.interest_in = interest_in;
    }

    public String getReligious_views() {
        return religious_views;
    }

    public void setReligious_views(String religious_views) {
        this.religious_views = religious_views;
    }

    public String getPolitical_views() {
        return political_views;
    }

    public void setPolitical_views(String political_views) {
        this.political_views = political_views;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public String getProfessional_skills() {
        return professional_skills;
    }

    public void setProfessional_skills(String professional_skills) {
        this.professional_skills = professional_skills;
    }
}
