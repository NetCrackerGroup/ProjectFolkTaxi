package com.netcracker.services;

import com.netcracker.DTO.GroupDto;
import com.netcracker.entities.Group;
import com.netcracker.entities.TypeGroup;
import com.netcracker.repositories.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;


@Service
public class GroupService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupService.class);

    private GroupRepository groupRepository;
    private TypeGroupService typeGroupService;
    private GroupMapper groupMapper;

    @Autowired
    public GroupService(    GroupRepository groupRepository,
                            TypeGroupService typeGroupService,
                            GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.typeGroupService = typeGroupService;
        this.groupMapper = groupMapper;
    }

    public GroupDto createGroup(String name, String nameType ) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(name.getBytes());
        String link = Hex.toHexString(digest);
        TypeGroup typeGroup = typeGroupService.getTypeGroupByName(nameType);
        LOG.debug("Type Group : {}", typeGroup);
        LOG.debug("create group - name : \'{}\' , link :  \'{}\'", name, link);
        Group group = new Group(name, link, typeGroup);
        groupRepository.save(group);
        LOG.debug("{}", group.toString());
        return getGroupById(group.getGroupId());
    }

    public GroupDto getGroupById(Long id) {
        LOG.debug("Get group by id {}", id);
        Optional<Group> possible = groupRepository.findById(id);
        return possible.isPresent() ? groupMapper.toDto(possible.get()) : null;
    }

    public Iterable<GroupDto> getAllGroups() {
        LOG.debug("Get groups");

        Iterable<Group> groups = groupRepository.findAll();
        Collection<GroupDto> groupDtos = new LinkedList<GroupDto>();
        for (Group group: groups) {
            groupDtos.add(groupMapper.toDto(group));
        }

        return groupDtos;
    }
}
