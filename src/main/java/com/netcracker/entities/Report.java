package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Report")
public class Report {

    @Id
    @Column(name = "Report_ID")
    private Long Report_ID;

    @NotNull
    @Column(name = "Report_Reason")
    private String reportReason;

    @Column(name = "Report_Text")
    private String reportText;

    @NotNull
    @Column(name = "Was_Considered")
    private Boolean wasConsidered;

    @NotNull
    @Column (name = "Moderator_ID")
    private Long moderatorId;

    @NotNull
    @Column(name = "User_ID")
    private Long userId;

    public Report() {
    }

    public Long getReport_ID() {
        return Report_ID;
    }

    public void setReport_ID(Long report_ID) {
        Report_ID = report_ID;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public Boolean getWasConsidered() {
        return wasConsidered;
    }

    public void setWasConsidered(Boolean wasConsidered) {
        this.wasConsidered = wasConsidered;
    }

    public Long getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(Long moderatorId) {
        this.moderatorId = moderatorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
