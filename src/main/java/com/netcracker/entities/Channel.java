package com.netcracker.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channel_id_generator")
    @SequenceGenerator(name = "channel_id_generator", sequenceName = "channel_id_seq", allocationSize = 1)
    @Column(name = "Channel_ID")
    private Long id;

    @Column(name = "Channel_Type", unique = true)
    private String type;

    public Channel(String type) {
        this.type = type;
    }

    public Channel() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(getId(), channel.getId()) &&
                Objects.equals(getType(), channel.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
