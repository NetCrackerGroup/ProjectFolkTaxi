package com.netcracker.DTO;

public class CodeDTO {
    private String code;

    public CodeDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CodeDTO{" +
                "code='" + code + '\'' +
                '}';
    }
}
