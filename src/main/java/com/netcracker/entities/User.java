package com.netcracker.entities;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity
@Table(name = "User_1")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @NotNull
    @Column(name = "User_ID")
    private int userId;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "City_ID")
    private City city;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    Collection<Group> groups;

    private String FIO;

    public User(){}
    @Column(name = "Email")
    private String email;

    public int getUserId() {
        return userId;
    }

    public City getCity() {
        return city;
    }

    public String getFIO() {
        return FIO;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User(@Size(min = 1, max = 50) String FIO,
                @Size(min = 1, max = 100) String phoneNumber,
                String email,
                City city) {
        this.FIO = FIO;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;

    }

    @Column(name = "Phone_Number")
    private String phoneNumber;





}
