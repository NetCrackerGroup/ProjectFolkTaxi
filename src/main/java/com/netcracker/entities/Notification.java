package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Notification {

    @Id
    @NotNull
    @Column(name = "Notification_ID")
    private Long notificationId;

    @NotNull
    @Column(name = "Text")
    private String text;

    @NotNull
    @Column(name = "Delivery_Channel")
    private String deliveryChannel;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "User_ID")
    private User user;

    public Notification() {
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeliveryChannel() {
        return deliveryChannel;
    }

    public void setDeliveryChannel(String deliveryChannel) {
        this.deliveryChannel = deliveryChannel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
