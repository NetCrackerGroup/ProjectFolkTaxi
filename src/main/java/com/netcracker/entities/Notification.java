package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_id_generator")
    @SequenceGenerator(name = "report_id_generator", sequenceName = "report_id_seq", allocationSize = 1)
    @NotNull
    @Column(name = "Notification_ID")
    private Long notificationId;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "Info_ID")
    private InfoContent infoContent;

    @NotNull
    @Column(name = "delivery_channel")
    private String channel;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "User_ID")
    private User user;



    public Notification() {
    }

    public Notification(    @NotNull InfoContent infoContent,
                            @NotNull String deliveryChannel
    ) {
        this.infoContent = infoContent;
    }

    public Notification(    @NotNull InfoContent infoContent,
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(getNotificationId(), that.getNotificationId()) &&
                Objects.equals(infoContent, that.infoContent) &&
                Objects.equals(getChannel(), that.getChannel()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotificationId(), infoContent, getChannel(), getUser());
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", text='" + infoContent.getText() + '\'' +
                ", channel='" + channel + '\'' +
                ", user=" + user +
                '}';
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
