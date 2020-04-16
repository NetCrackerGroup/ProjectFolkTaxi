package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "Complains")
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complain_id_generator")
    @SequenceGenerator(name = "complain_id_generator", sequenceName = "complain_id", allocationSize = 1)
    @NotNull
    @Column(name = "Complain_ID")
    private Long ComplainId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Adresat_ID")
    private User adresat;

    @Column(name = "Complain_Text")
    private String text;


    public Complain(){

    }

    public Complain(User user,User adresat,String text){
        this.user = user;
        this.adresat = adresat;
        this.text = text;
    }
    public Long getComplainId() {
        return ComplainId;
    }

    public void setComplainId(Long complainId) {
        ComplainId = complainId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAdresat() {
        return adresat;
    }

    public void setAdresat(User adresat) {
        this.adresat = adresat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complain complain = (Complain) o;
        return ComplainId.equals(complain.ComplainId) &&
                user.equals(complain.user) &&
                adresat.equals(complain.adresat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ComplainId, user, adresat);
    }

    @Override
    public String toString() {
        return "Complain{" +
                "ComplainId=" + ComplainId +
                ", user=" + user +
                ", adresat=" + adresat +
                ", text='" + text + '\'' +
                '}';
    }
}
