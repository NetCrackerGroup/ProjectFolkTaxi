package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "User_ID")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Report{" +
                "Report_ID=" + Report_ID +
                ", reportReason='" + reportReason + '\'' +
                ", reportText='" + reportText + '\'' +
                ", wasConsidered=" + wasConsidered +
                ", moderatorId=" + moderatorId +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return getReport_ID().equals(report.getReport_ID()) &&
                getReportReason().equals(report.getReportReason()) &&
                getReportText().equals(report.getReportText()) &&
                getWasConsidered().equals(report.getWasConsidered()) &&
                getModeratorId().equals(report.getModeratorId()) &&
                getUser().equals(report.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReport_ID(), getReportReason(), getReportText(),
                getWasConsidered(), getModeratorId(), getUser());

    }
}
