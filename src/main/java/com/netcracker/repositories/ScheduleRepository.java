package com.netcracker.repositories;

import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ScheduleRepository extends CrudRepository<Schedule, Route> {
    Schedule findScheduleByRouteId(Route routeId);

}
