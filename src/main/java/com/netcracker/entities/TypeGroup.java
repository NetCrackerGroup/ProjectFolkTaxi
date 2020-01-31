package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="Type_Group")
public class TypeGroup {

    @Id
    @NotNull
    @Column(name = "Type_Group_ID")
    private Long id;

    @NotNull
    @Column(name="Name_Type")
    private String nameType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String name_type) {
        this.nameType = name_type;
    }

    public TypeGroup() { }

    public TypeGroup(@NotNull Long id, @NotNull String name_type) {
        this.id = id;
        this.nameType = name_type;
    }

    @Override
    public String toString() {
        return "TypeGroup{" +
                "id=" + id +
                ", name_type='" + nameType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeGroup typeGroup = (TypeGroup) o;
        return Objects.equals(getId(), typeGroup.getId()) &&
                Objects.equals(getNameType(), typeGroup.getNameType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameType());
    }
}
