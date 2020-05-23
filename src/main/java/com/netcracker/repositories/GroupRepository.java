package com.netcracker.repositories;

import com.netcracker.entities.Group;

import com.netcracker.entities.TypeGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.entities.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    Optional<Group> findGroupByGroupName(String groupName);
    Optional<Group> findGroupByCityLink(String link);
    Collection<Group> findGroupsByTypeGroup(TypeGroup type);
}
