package com.netcracker.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Collection;

//доделать
//доделать
//доделать
//доделать
//доделать
//доделать
//доделать
@Entity
@Table(name = "Group_1")
public class Group {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Group_ID")
    private int groupId;

    @Column(name = "Group_Name")
    private String groupName;

    public Group() {
    }
    public Group(String groupName) {
        this.groupName = groupName;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_In_Group",
            joinColumns = { @JoinColumn(name = "Group_ID") },
            inverseJoinColumns = { @JoinColumn(name = "User_ID") }
    )
    private Collection<User> users;

    @Column(name = "Link")
    private String cityLink;

}
