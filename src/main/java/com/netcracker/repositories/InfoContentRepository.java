package com.netcracker.repositories;

import org.springframework.data.repository.CrudRepository;
import com.netcracker.entities.InfoContent;

import java.util.Optional;

public interface InfoContentRepository extends CrudRepository <InfoContent, Long> {
    Optional<InfoContent> findByKey(String key);
}
