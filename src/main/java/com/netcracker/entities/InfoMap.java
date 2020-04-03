package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "infomap")
public class InfoMap {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "infoMap_id_generator")
    @SequenceGenerator(name = "infoMap_id_generator", sequenceName = "infomap_id_seq", allocationSize = 1)
    @NotNull
    @Column(name = "infomap_id")
    private Long infoMap_ID;

    @Column(name = "infokey")
    private String infoKey;

    @Column(name = "infovalue")
    private String infoValue;


    public InfoMap() {
    }


    public InfoMap(String infoKey, String infoValue) {
        this.infoKey = infoKey;
        this.infoValue = infoValue;
    }

    @Override
    public String toString() {
        return "InfoMap{" +
                "infoMap_ID=" + infoMap_ID +
                ", infoKey='" + infoKey + '\'' +
                ", infoValue='" + infoValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoMap infoMap = (InfoMap) o;
        return Objects.equals(infoMap_ID, infoMap.infoMap_ID) &&
                Objects.equals(infoKey, infoMap.infoKey) &&
                Objects.equals(infoValue, infoMap.infoValue);
    }

    public Long getInfoMap_ID() {
        return infoMap_ID;
    }

    public void setInfoMap_ID(Long infoMap_ID) {
        this.infoMap_ID = infoMap_ID;
    }

    public String getInfoKey() {
        return infoKey;
    }

    public void setInfoKey(String infoKey) {
        this.infoKey = infoKey;
    }

    public String getInfoValue() {
        return infoValue;
    }

    public void setInfoValue(String infoValue) {
        this.infoValue = infoValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoMap_ID, infoKey, infoValue);
    }
}
