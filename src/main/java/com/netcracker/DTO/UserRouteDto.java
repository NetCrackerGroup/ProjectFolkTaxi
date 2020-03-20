
package com.netcracker.DTO;

public class UserRouteDto {
    private String fio;
    private Double driverRating;

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setDriverRating(Double driverRating) {
        this.driverRating = driverRating;
    }

    public Double getDriverRating() {
        return driverRating;
    }

    public String getFio() {
        return fio;
    }

}

