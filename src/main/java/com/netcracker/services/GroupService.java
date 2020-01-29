package com.netcracker.services;

import com.netcracker.entities.Group;
import com.netcracker.repositories.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GroupService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup( String name, String link ) {
        LOG.debug("create group - name : \'{}\' , link :  \'{}\'", name, link);

        Group group = new Group(name, link);
        groupRepository.save(group);

        return group;
    }

    public Group getGroupById(Long id) {
        LOG.debug("Get group by id {}", id);

        Optional<Group> possible = groupRepository.findById(id);

        LOG.debug("Group - {}", possible.get());
        return possible.isPresent() ? possible.get() : null;
    }

    public Iterable<Group> getAllGroups() {
        LOG.debug("Get groups");

        Iterable<Group> groups = groupRepository.findAll();

        return groups;
    }
}
