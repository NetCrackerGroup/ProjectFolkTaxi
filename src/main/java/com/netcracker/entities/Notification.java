package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Notification")
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
    @Column(name = "User_ID")
    private Long userId;


}
