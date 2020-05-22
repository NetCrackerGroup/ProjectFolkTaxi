package com.netcracker.DTO;

public class ResponseLong {
    private Long id;

    public ResponseLong(Long id) {
        this.id = id;
    }

    public ResponseLong() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ResponseLong{" +
                "id=" + id +
                '}';
    }
}
