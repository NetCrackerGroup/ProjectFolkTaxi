package com.netcracker.DTO.responses;

public class SuccessEntryGroup extends StatusResponse {
    private Long groupId;

    public SuccessEntryGroup(String status, Long groupId) {
        super(status);
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}