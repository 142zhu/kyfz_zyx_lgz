package com.ruoyi.kyfz.domain;

import java.util.List;

public class jsonMarch {
    private List<String> project_id;
    private String expert_account;
    private String match_score;
    private List<String> award_id;
    private List<String> certificate_id;
    private List<String> thesis_id;
    private List<String> other_id;

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

    public void setCertificate_id(List<String> certificate_id) {
        this.certificate_id = certificate_id;
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

    public List<String> getCertificate_id() {
        return certificate_id;
    }

    public List<String> getThesis_id() {
        return thesis_id;
    }

    public void setOther_id(List<String> other_id) {
        this.other_id = other_id;
    }

    public List<String> getOther_id() {
        return other_id;
    }

}
