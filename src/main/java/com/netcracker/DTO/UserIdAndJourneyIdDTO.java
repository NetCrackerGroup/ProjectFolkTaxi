package com.netcracker.DTO;

import java.util.Objects;

public class UserIdAndJourneyIdDTO {

    private Long journeyId;
    private Long userId;

    public UserIdAndJourneyIdDTO() {
    }

    public UserIdAndJourneyIdDTO(Long journeyId, Long userId) {
        this.journeyId = journeyId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdAndJourneyIdDTO that = (UserIdAndJourneyIdDTO) o;
        return Objects.equals(getJourneyId(), that.getJourneyId()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJourneyId(), getUserId());
    }

    @Override
    public String toString() {
        return "UserIdAndJourneyIdDTO{" +
                "journeyId=" + journeyId +
                ", userId=" + userId +
                '}';
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
