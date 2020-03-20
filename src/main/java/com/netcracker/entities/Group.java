package com.netcracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

@Entity
@Table(name = "User_Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_generator")
    @SequenceGenerator(name = "group_id_generator", sequenceName = "group_id_seq", allocationSize = 1)
    @Column(name = "Group_ID")
    private Long groupId;

    @Column(name = "Group_Name")
    private String groupName;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "Type_Group_ID")
    private TypeGroup typeGroup;

    public Group() {
    }
    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(   String groupName,
                    String cityLink,
                    TypeGroup typeGroup) {
        this.groupName = groupName;
        this.cityLink = cityLink;
        this.typeGroup = typeGroup;
        this.users = new LinkedList<>();
        this.moderators = new LinkedList<>();
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    @JoinTable(
            name = "User_In_Group",
            joinColumns = { @JoinColumn(name = "Group_ID") },
            inverseJoinColumns = { @JoinColumn(name = "User_ID") }
    )
    Collection<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_moderators",
            joinColumns = { @JoinColumn(name = "Group_ID") },
            inverseJoinColumns = { @JoinColumn(name = "User_ID") }
    )
    Collection<User> moderators;

    @Column(name = "Link")
    private String cityLink;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCityLink() {
        return cityLink;
    }

    public void setCityLink(String cityLink) {
        this.cityLink = cityLink;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public TypeGroup getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(TypeGroup typeGroup) {
        this.typeGroup = typeGroup;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<User> getModerators() {
        return moderators;
    }

    public void setModerators(Collection<User> moderators) {
        this.moderators = moderators;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(getGroupId(), group.getGroupId()) &&
                Objects.equals(getGroupName(), group.getGroupName()) &&
                Objects.equals(getTypeGroup(), group.getTypeGroup()) &&
                Objects.equals(getUsers(), group.getUsers()) &&
                Objects.equals(getModerators(), group.getModerators()) &&
                Objects.equals(getCityLink(), group.getCityLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getGroupName(), getTypeGroup(), getUsers(), getModerators(), getCityLink());
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", typeGroup=" + typeGroup +
                ", users=" + users +
                ", moderators=" + moderators +
                ", cityLink='" + cityLink + '\'' +
                '}';
    }
}
