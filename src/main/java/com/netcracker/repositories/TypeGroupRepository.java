package com.netcracker.repositories;

import com.netcracker.entities.TypeGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeGroupRepository extends CrudRepository<TypeGroup, Long> {
    TypeGroup findTypeGroupByNameType(String nameType);
}
