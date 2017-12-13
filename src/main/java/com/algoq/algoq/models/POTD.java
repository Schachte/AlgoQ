package com.algoq.algoq.models;

import org.springframework.context.annotation.Description;
import java.util.ArrayList;

@Description("Handles the fields required for processing problem of the day")
public class POTD {

    private String subject;
    private String problemTitle;
    private String problemDescription;
    private ArrayList<POTDResources> resources;

    POTD(String problemTitle, String problemDescription, ArrayList<POTDResources> resources) {
        this.problemTitle = problemTitle;
        this.problemDescription = problemDescription;
        this.resources = resources;
    }

    public POTD() {

    }

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
}

