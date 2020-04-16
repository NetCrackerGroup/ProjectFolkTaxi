package com.netcracker.services;


import com.netcracker.entities.Complain;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.repositories.ComplainRepository;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainService {
    private static final Logger LOG = LoggerFactory.getLogger(ComplainService.class);

    @Autowired
    private ComplainRepository  complainRepository;

    @Autowired
    private UserRepository userRepository;



    public Complain getComplainById(Long complainId){

        LOG.debug("[ getById(complainId :{}", complainId);

        Complain complain = complainRepository.findById(complainId).orElse(null);


        LOG.debug("](return : {})", complain);

        return complain;

    }

    public Iterable<Complain> getAllComplains(){

        LOG.debug("[ getAllComplains");

        Iterable<Complain> complains= complainRepository.findAll();

        LOG.debug("](return : {})",complains);

        return complains;

    }

    public void deleteComplain(Long complainId){
        LOG.debug("[ deleteComplain(complainId : {})", complainId);

        complainRepository.deleteById(complainId);
    }

    public List<Complain> findComplainsByUser(Long userId){

        LOG.debug("[ findComplainsByUser(userId :{}", userId);

        List<Complain> complains = complainRepository.findComplainsByAdresat_UserId(userId);

        LOG.debug("](return : {})", complains);

        return complains;
    }

    public Complain sendComplain(Long userId,String text){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails)auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        LOG.debug("[ sendComplainToUser(userId :{}", userId);

        User adresat = userRepository.findUserByUserId(userId);

        Complain complain = new Complain(user,adresat,text);
        adresat.setNumberOfComplaints(adresat.getNumberOfComplaints()+1);


        complainRepository.save(complain);
        userRepository.save(adresat);

        LOG.debug("](return : {})", complain);

        return complain;



    }


}
