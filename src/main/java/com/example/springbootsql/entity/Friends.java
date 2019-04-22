package com.example.springbootsql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by: Changze
 * Date: 2019/4/22
 * Time: 19:39
 */
@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String other_names;
    private String facebook_url;
    private String relationship;
    private String favorite_quotes;
    private String introduction;
    private String gender;
    private String language;
    private String interest_in;
    private String religious_views;
    private String political_views;
    private String birth_day;
    private String blood_type;
    private String email;
    private String websites;
    private String social_link;
    private String mobile_phone;
    private String professional_skills;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther_names() {
        return other_names;
    }

    public void setOther_names(String other_names) {
        this.other_names = other_names;
    }

    public String getFacebook_url() {
        return facebook_url;
    }

    public void setFacebook_url(String facebook_url) {
        this.facebook_url = facebook_url;
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

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsites() {
        return websites;
    }

    public void setWebsites(String websites) {
        this.websites = websites;
    }

    public String getSocial_link() {
        return social_link;
    }

    public void setSocial_link(String social_link) {
        this.social_link = social_link;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getProfessional_skills() {
        return professional_skills;
    }

    public void setProfessional_skills(String professional_skills) {
        this.professional_skills = professional_skills;
    }
}
