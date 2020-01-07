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

    public Notification(    @NotNull String text,
                            @NotNull String deliveryChannel
    ) {
        this.text = text;
        this.deliveryChannel = deliveryChannel;
    }

    public Notification(    @NotNull String text,
                            @NotNull String deliveryChannel,
                            @NotNull User user
    ) {
        this.text = text;
        this.deliveryChannel = deliveryChannel;
        this.user = user;
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

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", text='" + text + '\'' +
                ", deliveryChannel='" + deliveryChannel + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(getNotificationId(), that.getNotificationId()) &&
                Objects.equals(getText(), that.getText()) &&
                Objects.equals(getDeliveryChannel(), that.getDeliveryChannel()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotificationId(), getText(), getDeliveryChannel(), getUser());
    }
}
