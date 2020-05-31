package com.netcracker.repositories;

import com.netcracker.entities.StatusThank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusThankRepository extends CrudRepository<StatusThank, Long> {
}
