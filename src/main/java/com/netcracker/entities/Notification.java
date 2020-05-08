package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;
import java.sql.Timestamp;

@Entity
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_id_generator")
    @SequenceGenerator(name = "report_id_generator", sequenceName = "report_id_seq", allocationSize = 1)
    @NotNull
    @Column(name = "Notification_ID")
    private Long notificationId;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Info_ID")
    private InfoContent infoContent;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "infotonotification",
            joinColumns = {@JoinColumn(name = "notification_id")},
            inverseJoinColumns = {@JoinColumn(name = "infomap_id")}
    )
    Collection<InfoMap> templatevalues;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_ID")
    private User user;

    @Column(name = "time")
    private Timestamp timestamp;

    @Column(name = "Was_Watched")
    @NotNull
    private boolean wasWatched;

    public Notification(@NotNull InfoContent infoContent, Collection<InfoMap> templatevalues, @NotNull User user) {
        this.infoContent = infoContent;
        this.templatevalues = templatevalues;
        this.user = user;
    }

    public Notification() {
    }

    public Notification(
            @NotNull InfoContent infoContent,
            @NotNull String deliveryChannel
    ) {
        this.infoContent = infoContent;
    }

    public Notification(
            @NotNull InfoContent infoContent,
            @NotNull String deliveryChannel,
            @NotNull User user
    ) {
        this.infoContent = infoContent;
        this.user = user;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public InfoContent getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(InfoContent infoContent) {
        this.infoContent = infoContent;
    }

    public Collection<InfoMap> getTemplatevalues() {
        return templatevalues;
    }

    public void setTemplatevalues(Collection<InfoMap> templatevalues) {
        this.templatevalues = templatevalues;
    }

    public boolean isWasWatched() {
        return wasWatched;
    }

    public void setWasWatched(boolean wasWatched) {
        this.wasWatched = wasWatched;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
