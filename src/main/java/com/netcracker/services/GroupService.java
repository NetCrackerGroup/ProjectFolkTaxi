package com.netcracker.services;

import com.netcracker.Application;
import com.netcracker.DTO.GroupDto;
import com.netcracker.entities.Chat;
import com.netcracker.DTO.mappers.GroupMapper;
import com.netcracker.entities.Group;
import com.netcracker.entities.TypeGroup;
import com.netcracker.entities.User;
import com.netcracker.models.CategoryNotification;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.repositories.GroupRepository;
import com.netcracker.repositories.UserRepository;
import com.netcracker.services.Channels.ApplicationSenderService;
import com.netcracker.services.Channels.ChatSenderService;
import com.netcracker.services.Channels.EmailServiceImpl;
import com.netcracker.services.Channels.FillInfoContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class GroupService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupService.class);

    private EmailServiceImpl emailService;
    private GroupRepository groupRepository;
    private TypeGroupService typeGroupService;
    private AuthUserComponent authUserComponent;
    private ChatRepository chatRepository;
    private NotificationService notificationService;
    private InfoContentService infoContentService;
    private ChatSenderService chatSenderService;
    private UserRepository userRepository;
    private ApplicationSenderService applicationSenderService;

    @Autowired
    public void setEmailService(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }


    @Autowired
    public GroupService(GroupRepository groupRepository,
                        TypeGroupService typeGroupService,
                        AuthUserComponent authUserComponent,
                        ChatRepository chatRepository,
                        NotificationService notificationService,
                        InfoContentService infoContentService,
                        ChatSenderService chatSenderService,
                        UserRepository userRepository,
                        ApplicationSenderService applicationSenderService
    ) {
        this.groupRepository = groupRepository;
        this.typeGroupService = typeGroupService;
        this.authUserComponent = authUserComponent;
        this.chatRepository = chatRepository;
        this.notificationService = notificationService;
        this.infoContentService = infoContentService;
        this.chatSenderService = chatSenderService;
        this.userRepository = userRepository;
        this.applicationSenderService = applicationSenderService;
    }


    /*
     *Если существует группа с переданным именем, то метод вернет null
     *В ином случае вернет группу.
     */
    public Group createGroup(String name, String nameType) throws Exception {
        LOG.debug("Мы тут");
        if (groupRepository.findGroupByGroupName(name).isPresent()) {
            LOG.debug("Сюда ?");
            return null;
        }

        /*
         **  Шифрование ссылки
         */
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(name.getBytes());
        String link = Hex.toHexString(digest); // Готовая ссылка

        TypeGroup typeGroup = typeGroupService.getTypeGroupByName(nameType);
        LOG.debug("Type Group : {}", typeGroup);
        LOG.debug("create group - name : \'{}\' , link :  \'{}\'", name, link);


        User user = authUserComponent.getUser();
        LOG.debug("----Notificate");
        Group group = new Group(name, link, typeGroup);
        group.getUsers().add(user);// Добавление создателя в группу, в качестве участника
        group.getModerators().add(user); // Добавление создателя в модераторы
        groupRepository.save(group);

        Chat chat = new Chat(null, group);
        chat.setGroup(group);
        chatRepository.save(chat);
        chatSenderService.setChat(chat);

        LOG.debug("{}", group.toString());
        return group;

    }

    public Group addUserInGroup (String link){
        Optional<Group> group = groupRepository.findGroupByCityLink(link);
        if (group.isPresent()) {
            group.get().getUsers().add(authUserComponent.getUser());
        }

        return group.get();
    }

    public Group getGroupById (Long id){
        LOG.debug("Get group by id {}", id);
        Optional<Group> possible = groupRepository.findById(id);
        return possible.orElse(null);
    }

    public Iterable<Group> getAllGroups () {
        LOG.debug("Get groups");

        Iterable<Group> groups = groupRepository.findAll();
        return groups;
    }

    public Group deleteUserInGroup (Long groupId){
        User user = authUserComponent.getUser();
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            Group group2 = group.get();
            if (group2.getUsers().contains(user)) {
                group2.getUsers().remove(user);
            }
            groupRepository.save(group2);
        }
        return group.get();
    }

    public Group addUserInGroup (Long groupId) throws Exception {
        User user = authUserComponent.getUser();
        Optional<Group> group = groupRepository.findById(groupId);
        Collection<User> notUsers = group.get().getNotUsers();
        Collection<Long> idNotUsers = new ArrayList<Long>();
        /*for ( User notUser: group.get().getNotUsers()){
            idNotUsers.add(notUser.getUserId());
        }
        boolean contain = false;
        if(idNotUsers.contains(authUserComponent.getUser().getUserId()))
        {
            contain = true;
        }*/
        LOG.debug("group : {}", group.get());
        if (group.isPresent()) {
            group.get().getUsers().add(user);
            Map<String, String> maps = new HashMap<String, String>();
            maps.put("groupName", group.get().getGroupName());
            FillInfoContent fillInfoContent = new FillInfoContent(maps);
            notificationService.notify(
                    infoContentService.getInfoContentByKey("user_entered_group"),
                    applicationSenderService,
                    user,
                    fillInfoContent
            );
            groupRepository.save(group.get());
            LOG.debug("users group : {}", group.get().getUsers());
        }
        return group.get();
    }

    public boolean checkUserIsInvolvedInGroup (Long groupId)
    {
        Optional<Group> possibleGroup = groupRepository.findById(groupId);
        LOG.debug("id : {}, group : ", groupId, possibleGroup.isPresent());

        boolean result = false;
        if (possibleGroup.isPresent()) {
            LOG.debug("#### checkUserIsInvolved #####");
            User user = authUserComponent.getUser();
            LOG.debug("{}", user);
            Group group = possibleGroup.get();
            for (Group temp : user.getGroups()) {
                if (temp.equals(group)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    public Group deleteUserCompletely (Long groupId,Long userId) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());
        Group group = groupRepository.findById(groupId).orElse(null);

        Collection<User> moderators = group.getModerators();
        LOG.debug("[ moderators( :{}", moderators);
        Collection<Long> idmoderators = new ArrayList<Long>();
        for (User moderator : group.getModerators()) {
            idmoderators.add(moderator.getUserId());
        }
        boolean moderator = false;
        LOG.debug("[ moderators( :{}", idmoderators);
        LOG.debug("[ user( :{}", user.getUserId());

        if (idmoderators.contains(user.getUserId())) {
            moderator = true;
        }
        LOG.debug("[ moderator( :{}", moderator);
        if (moderator) {
            Collection<User> usersNotInGroup = group.getNotUsers();
            Collection<User> users = group.getUsers();
            User deleteUser = userRepository.findUserByUserId(userId);
            usersNotInGroup.add(deleteUser);
            users.remove(deleteUser);
            group.setUsers(users);
            group.setNotUsers(usersNotInGroup);
        }
        LOG.debug("[ users( :{}", group.getUsers());
        groupRepository.save(group);

        return group;


    }
    public boolean checkUserIsModeratorGroup (Long groupId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());
        Group group = groupRepository.findById(groupId).orElse(null);

        Collection<User> moderators = group.getModerators();
        LOG.debug("[ moderators( :{}", moderators);

        Collection<Long> idmoderators = new ArrayList<Long>();
        for (User moderator : group.getModerators()) {
            idmoderators.add(moderator.getUserId());
        }
        boolean moderator = false;
        LOG.debug("[ moderators( :{}", idmoderators);
        LOG.debug("[ user( :{}", user.getUserId());

        if (idmoderators.contains(user.getUserId())) {
            moderator = true;
        }
        LOG.debug("[ moderator( :{}", moderator);
        return moderator;
    }


    }
