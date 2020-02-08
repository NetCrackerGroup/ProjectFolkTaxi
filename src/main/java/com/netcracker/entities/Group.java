package com.netcracker.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "User_Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_generator")
	@SequenceGenerator(name = "group_id_generator", sequenceName = "group_id_seq", allocationSize = 1)
    @Column(name = "Group_ID")
    private Long groupId;

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
    Collection<User> users;

    @Column(name = "Link")
    private String cityLink;

}
