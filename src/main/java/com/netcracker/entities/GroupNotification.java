package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "Group_Notification")
public class GroupNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GroupNotification_id_generator")
    @SequenceGenerator(name = "GroupNotification_id_generator", sequenceName = "group_notification_id", allocationSize = 1)
    @Column(name = "group_Notification_ID")
    private Long notificationId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Group_ID")
    private Group group;

    @NotNull
    @ManyToOne
    @JoinColumn( name = "Content")
    private InfoContent infoContent;

    public GroupNotification() {
    }

    public GroupNotification(@NotNull Group group, @NotNull InfoContent infoContent) {
        this.group = group;
        this.infoContent = infoContent;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public InfoContent getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(InfoContent infoContent) {
        this.infoContent = infoContent;
    }

    @Override
    public String toString() {
        return "GroupNotification{" +
                "notificationId=" + notificationId +
                ", group=" + group +
                ", infoContent=" + infoContent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupNotification that = (GroupNotification) o;
        return Objects.equals(getNotificationId(), that.getNotificationId()) &&
                Objects.equals(getGroup(), that.getGroup()) &&
                Objects.equals(getInfoContent(), that.getInfoContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotificationId(), getGroup(), getInfoContent());
    }
}
