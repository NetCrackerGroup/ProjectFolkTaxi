package com.netcracker.journeysSchedules;

import com.netcracker.entities.Journey;
import com.netcracker.entities.Route;
import com.netcracker.repositories.JourneyRepository;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.rootsearch.LongMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledTasks {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    JourneyRepository journeyRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final String driverClassName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/folktaxi";
    private static final String dbUsername = "postgres";
    private static final String dbPassword = "root";

    public static DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    //убери один ноль
    @Scheduled(fixedRate = 3000000)
    public void getFinishedRoutes() {
        LOG.info("[getFinishedRoutes ");

        String schedQuery = "select s.route_id,  s.time_of_journey  from schedules s\n" +
                "    left join (\n" +
                "        select route_id, count(route_id) as countOfPassengers from passenger_in_route\n" +
                "        group by route_id\n" +
                "    ) as passOnRoute \n" +
                "    on passOnRoute.route_id = s.route_id\n" +
                "    where \n" +
                "          s.route_id in (\n" +
                "        select r.route_id\n" +
                "        from routes r\n" +
                "        where r.count_of_places >= 1)\n" +
                "    and passOnRoute.countOfPassengers > 1 \n" +
                "    and \n" +
                "          ((now() + interval '4 hour')::time  -  s.time_of_journey::time )::time < (interval '00:05:00')::time\n" +
                "    and (? & CAST(S.schedule_day AS INT) <> 0  or s.start_day = now()::date );";

        DataSource dataSource;
        dataSource = getDataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        LocalDate date = LocalDate.now();
        date.getDayOfWeek().getValue();

        List<Long> idsFromQuery = template.query(schedQuery,
                new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, (int) Math.pow(2, date.getDayOfWeek().getValue()));
                    }
                },
                new LongMapper()
        );

        ArrayList<Long> ids = new ArrayList<>(idsFromQuery);

        if (ids.size() != 0) {
            for (Long id: ids) {
                Journey journey = new Journey();
                Route currentRoute = routeRepository.findRouteByRouteId(id);
                //что подразумевается под датой
                journey.setDate(date);
                journey.setUsers(currentRoute.getUsers());
                journey.setRoute(currentRoute);
                journeyRepository.save(journey);
            }
        }
        LOG.info("] return : {}", ids);
    }
}
