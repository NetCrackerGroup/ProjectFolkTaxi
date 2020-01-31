package com.netcracker.services;

import com.netcracker.entities.TypeGroup;
import com.netcracker.repositories.TypeGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeGroupService {

    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private TypeGroupRepository typeGroupRepository;

    public TypeGroup getTypeGroupByName (String name) {
        LOG.debug("get type group by name");

        TypeGroup possible = typeGroupRepository.findTypeGroupByNameType(name);

        return possible;
    }

}
