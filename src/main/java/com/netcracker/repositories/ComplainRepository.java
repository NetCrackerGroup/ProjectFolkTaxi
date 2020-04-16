package com.netcracker.repositories;


import com.netcracker.entities.Complain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ComplainRepository extends CrudRepository<Complain, Long> {
    List<Complain> findComplainsByAdresat_UserId(Long  userId);


}
