package com.netcracker.repositories;

import com.netcracker.entities.RateHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateHistoryRepository extends CrudRepository<RateHistory, Long> {
}
