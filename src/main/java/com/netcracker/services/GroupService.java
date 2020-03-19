package com.netcracker.services;

import com.netcracker.DTO.GroupDto;
import com.netcracker.entities.Group;
import com.netcracker.entities.TypeGroup;
import com.netcracker.entities.User;
import com.netcracker.models.CategoryNotification;
import com.netcracker.repositories.GroupRepository;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import javax.mail.MessagingException;
import java.util.Optional;


@Service
public class GroupService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupService.class);

    private GroupRepository groupRepository;
    private TypeGroupService typeGroupService;
    private AuthUserComponent authUserComponent;

    @Autowired
    public GroupService(GroupRepository groupRepository,
                        TypeGroupService typeGroupService,
                        AuthUserComponent authUserComponent
    ) {
        this.groupRepository = groupRepository;
        this.typeGroupService = typeGroupService;
        this.authUserComponent = authUserComponent;
    }


    /*
    *Если существует группа с переданным именем, то метод вернет null
    *В ином случае вернет группу.
     */
    public Group createGroup(String name, String nameType ) throws MessagingException {
        LOG.debug("Мы тут");
        if ( groupRepository.findGroupByGroupName(name).isPresent() ) {
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
        LOG.debug("{}", group.toString());
        return group;
    }


    public Group addUserInGroup(String link) {

        Optional<Group> group = groupRepository.findGroupByCityLink(link);

        if (group.isPresent()) {
            group.get().getUsers().add(authUserComponent.getUser());
        }

        return group.get();
    }

    public Group getGroupById(Long id) {
        LOG.debug("Get group by id {}", id);
        Optional<Group> possible = groupRepository.findById(id);
        return possible.orElse(null);
    }

    public Iterable<Group> getAllGroups() {
        LOG.debug("Get groups");

        Iterable<Group> groups = groupRepository.findAll() ;
        return groups;
    }

    public Group deleteUserInGroup(Long groupId) {
        User user = authUserComponent.getUser();
        Optional<Group> group = groupRepository.findById(groupId);
        if ( group.isPresent() ) {
            Group group2 = group.get();
            if (group2.getUsers().contains(user)) {
                group2.getUsers().remove(user);
            }
            groupRepository.save(group2);
        }
        return group.get();
    }

    public Group addUserInGroup(Long groupId) {
        User user = authUserComponent.getUser();
        Optional<Group> group = groupRepository.findById(groupId);
        LOG.debug("group : {}", group.get());
        if ( group.isPresent() ){
            group.get().getUsers().add(user);
            groupRepository.save(group.get());
            LOG.debug("users group : {}" , group.get().getUsers());
        }
        return group.get();
    }

    public Boolean checkUserIsInvolvedInGroup(Long groupId) {

        Optional<Group> possibleGroup = groupRepository.findById(groupId);
        LOG.debug("id : {}, group : ", groupId, possibleGroup.isPresent());

        Boolean result = false;
        if ( possibleGroup.isPresent() ) {
            LOG.debug("#### checkUserIsInvolved #####");
            User user = authUserComponent.getUser();
            LOG.debug("{}", user);
            Group group = possibleGroup.get();
            for ( Group temp : user.getGroups() ) {
                if ( temp.equals(group) ) {
                    result = true;
                    break;
                }
            }
        }
        return result;

    }
}
