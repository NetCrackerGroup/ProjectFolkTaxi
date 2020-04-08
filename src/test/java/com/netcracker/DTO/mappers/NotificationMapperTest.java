//package com.netcracker.DTO.mappers;
//
//import com.netcracker.DTO.NotificationDTO;
//import com.netcracker.controllers.ChatsController;
//import com.netcracker.entities.InfoMap;
//import com.netcracker.entities.Notification;
//import com.netcracker.repositories.InfoContentRepository;
//import com.netcracker.repositories.UserRepository;
//import com.netcracker.entities.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.LinkedList;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class NotificationMapperTest {
//
//    private static final Logger LOG = LoggerFactory.getLogger(NotificationMapperTest.class);
//
//    @Autowired
//    private InfoContentRepository infoContentRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private NotificationMapper notificationMapper;
//
//    @Before
//    public void setUp() throws Exception {
//
//    }
//
//    @Test
//    public void notificationMapper() {
//        LOG.debug("start Test");
//        Notification notification = new Notification();
//        LOG.debug("Declare notification : {}" , notification);
//        User user = userRepository.findUserByUserId(1l);
//        notification.setUser(user);
//        notification.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
//        notification.setInfoContent(infoContentRepository.findByKey("user_create_group").get());
//        notification.setNotificationId(1l);
//        Collection<InfoMap> infoMaps = new LinkedList<>();
//        infoMaps.add(new InfoMap("test", "test"));
//        notification.setTemplatevalues( infoMaps );
//        NotificationDTO  notificationDTO = notificationMapper.toDto(notification);
//        assertEquals(notification.getInfoContent(), notificationDTO.getInfoContent());
//        LOG.debug("Timestamp : {}" , Timestamp.valueOf(LocalDateTime.now()));
//        Collection<String> maps = notificationDTO.getTemplatevalues();
//
//    }
//
//}