package com.netcracker.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JourneyThanksPassegerDTO {

    private BigDecimal price;
    private Long routeId;
    private LocalDate date;

    public JourneyThanksPassegerDTO() {
    }

    public JourneyThanksPassegerDTO(BigDecimal price, Long routeId, LocalDate date) {
        this.price = price;
        this.routeId = routeId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "JourneyThanksPassegerDTO{" +
                "price=" + price +
                ", routeId=" + routeId +
                ", date=" + date +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
