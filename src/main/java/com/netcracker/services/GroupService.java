package com.netcracker.services;

import com.netcracker.entities.Group;
import com.netcracker.entities.TypeGroup;
import com.netcracker.repositories.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.util.Optional;


@Service
public class GroupService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TypeGroupService typeGroupService;

    public Group createGroup( String name, String nameType ) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(name.getBytes());
        String link = Hex.toHexString(digest);
        TypeGroup typeGroup = typeGroupService.getTypeGroupByName(nameType);
        LOG.debug("Type Group : {}", typeGroup);
        LOG.debug("create group - name : \'{}\' , link :  \'{}\'", name, link);
        Group group = new Group(name, link, typeGroup);
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
