package com.netcracker.DTO;

import com.netcracker.entities.TypeGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class GroupDto {

    private static final Logger LOG = LoggerFactory.getLogger(GroupDto.class);


    private Long groupId;
    private String groupName;
    private TypeGroup typeGroup;
    private String cityLink;
    private Collection<Long> users;

    public GroupDto() {
    }


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public TypeGroup getTypeGroup() {
        return typeGroup;
    }

    public String getCityLink() {
        return cityLink;
    }

    public void setCityLink(String cityLink) {
        this.cityLink = cityLink;
    }

    public void setTypeGroup(TypeGroup typeGroup) {
        this.typeGroup = typeGroup;
    }

    public Collection<Long> getUsers() {
        return users;
    }

    public void setUsers(Collection<Long> users) {
        LOG.debug("setUsers : {}", users);
        this.users = users;
        LOG.debug("GroupDto : {}", this);

    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", typeGroup=" + typeGroup +
                ", users=" + users +
                '}';
    }
}
