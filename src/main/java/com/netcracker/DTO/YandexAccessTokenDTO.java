package com.netcracker.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class YandexAccessTokenDTO implements Serializable {

    @SerializedName("access_token")
    public String access_token;

    @SerializedName("error")
    public String error;

    public YandexAccessTokenDTO() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "YandexAccessTokenDTO{" +
                "accessToken='" + access_token + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YandexAccessTokenDTO that = (YandexAccessTokenDTO) o;
        return Objects.equals(getAccess_token(), that.getAccess_token()) &&
                Objects.equals(getError(), that.getError());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccess_token(), getError());
    }
}