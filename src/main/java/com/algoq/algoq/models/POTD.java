package com.algoq.algoq.models;

import org.springframework.context.annotation.Description;
import java.util.ArrayList;
import java.util.Optional;

@Description("Handles the fields required for processing problem of the day")
public class POTD {

    private String subject;
    private String problemTitle;
    private String problemDescription;
    private String potdCode;
    private ArrayList<POTDResources> resources;

    public POTD(String problemTitle, String problemDescription, ArrayList<POTDResources> resources) {
        this.problemTitle = problemTitle;
        this.problemDescription = problemDescription;
        this.resources = resources;
    }

    public POTD(String potd_title, String potd_description, ArrayList<POTDResources> linkResources, String potdCode) {
        this.problemTitle = potd_title;
        this.problemDescription = potd_description;
        this.resources = linkResources;
        this.potdCode = potdCode;
    }

    public POTD() {}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public ArrayList<POTDResources> getResources() {
        return resources;
    }

    public void setResources(ArrayList<POTDResources> resources) {
        this.resources = resources;
    }

    public String getPotdCode() {
        return potdCode;
    }

    public void setPotdCode(String potdCode) {
        this.potdCode = potdCode;
    }
}

