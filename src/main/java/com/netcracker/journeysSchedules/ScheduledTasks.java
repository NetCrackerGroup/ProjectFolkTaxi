package com.netcracker.journeysSchedules;

import com.netcracker.entities.Chat;
import com.netcracker.entities.Journey;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.repositories.JourneyRepository;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.rootsearch.LongMapper;
import com.netcracker.services.Channels.ChatSenderService;
import com.netcracker.services.Channels.EmailServiceImpl;
import com.netcracker.services.InfoContentService;
import com.netcracker.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ScheduledTasks {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    InfoContentService infoContentService;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    ChatSenderService chatSenderService;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    DataSource dataSource;

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);





    //убери один ноль
    @Scheduled(fixedRate = 300000)
    public void getFinishedRoutes() throws MessagingException {
        LOG.info("[getFinishedRoutes ");

        String schedQuery = "select s.route_id  from schedules s\n" +
                "                                     inner join passenger_in_route\n" +
                "                                    on passenger_in_route.route_id = s.route_id\n" +
                "                                    where ((now())::timestamp  -   (s.time_of_journey::time + interval '1 hour')::time )::time < (interval '00:05:00')::time\n" +
                "                                    and (? & CAST(S.schedule_day AS INT) <> 0  or s.start_day = now()::date )\n" +
                "                                    group by s.route_id having count(s.route_id) > 1;";



//        select s.route_id,  s.time_of_journey  from schedules s
//        inner join (
//                select route_id, count(route_id) as countOfPassengers from passenger_in_route
//        group by route_id
//                                    ) as passOnRoute
//        on passOnRoute.route_id = s.route_id
//
//        where passOnRoute.countOfPassengers > 1
//        and
//                ((now()+ interval '3 hour')::timestamp  -   (s.time_of_journey::time + interval '1 hour')::time )::time < (interval '00:05:00')::time
//        and (8 & CAST(S.schedule_day AS INT) <> 0  or s.start_day = now()::date );


        JdbcTemplate template = new JdbcTemplate(dataSource);
        LocalDate date = LocalDate.now();
        int val  = date.getDayOfWeek().getValue();
        List<Long> idsFromQuery = template.query(schedQuery, new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException{
                        preparedStatement.setInt(1, val);
                    }
                },
                new LongMapper()
        );
        ArrayList<Long> ids = new ArrayList<>(idsFromQuery);
        if (ids.size() != 0) {
            for (Long id: ids) {
                Route currentRoute = routeRepository.findRouteByRouteId(id);
                Collection<User> users = currentRoute.getUsers();
                Journey journey = new Journey(date, users, currentRoute, currentRoute.getDriverId());

                journeyRepository.save(journey);

//                for (User u:
//                     users) {
//                    notificationService.notify(
//                            infoContentService.getInfoContentByKey("journey_is_over"),
//                            emailService,
//                            u
//                            );
//                }
                Chat chat = chatRepository.findByRoute(currentRoute);
                chatSenderService.setChat(chat);
                notificationService.notify(
                        infoContentService.getInfoContentByKey("journey_is_over"),
                        chatSenderService,
                        currentRoute
                );


            }
        }
        LOG.info("] return : {}", ids);
    }
}
