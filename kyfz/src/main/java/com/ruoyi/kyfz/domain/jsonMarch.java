package com.ruoyi.kyfz.domain;

import java.util.List;

public class jsonMarch {
    private List<String> project_id;
    private String expert_account;
    private String match_score;
    private List<String> award_id;
    private List<String> intellectual_property_id;
    private List<String> thesis_id;
    private List<String> other_result_id;

    public void setProject_id(List<String> project_id) {
        this.project_id = project_id;
    }

    public void setExpert_account(String expert_account) {
        this.expert_account = expert_account;
    }

    public void setMatch_score(String match_score) {
        this.match_score = match_score;
    }

    public void setAward_id(List<String> award_id) {
        this.award_id = award_id;
    }

    public void setThesis_id(List<String> thesis_id) {
        this.thesis_id = thesis_id;
    }

    public List<String> getProject_id() {
        return project_id;
    }

    public String getExpert_account() {
        return expert_account;
    }

    public String getMatch_score() {
        return match_score;
    }

    public List<String> getAward_id() {
        return award_id;
    }

    public List<String> getThesis_id() {
        return thesis_id;
    }

    public void setIntellectual_property_id(List<String> intellectual_property_id) {
        this.intellectual_property_id = intellectual_property_id;
    }

    public void setOther_result_id(List<String> other_result_id) {
        this.other_result_id = other_result_id;
    }

    public List<String> getIntellectual_property_id() {
        return intellectual_property_id;
    }

    public List<String> getOther_result_id() {
        return other_result_id;
    }

}
