package com.netcracker.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "paid_journey")
public class PaidJourney {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paid_journey_id_generator")
    @SequenceGenerator(name = "paid_journey_id_generator", sequenceName = "paid_journey_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journey_id")
    private Journey journey;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_thank_id")
    private StatusThank status;

    public PaidJourney() {
    }

    public PaidJourney(User user, Journey journey, StatusThank status) {
        this.user = user;
        this.journey = journey;
        this.status = status;
    }

    public StatusThank getStatus() {
        return status;
    }

    public void setStatus(StatusThank status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidJourney that = (PaidJourney) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "PaidJourney{" +
                "id=" + id +
                ", user=" + user +
                ", journey=" + journey +
                ", status=" + status +
                '}';
    }
}