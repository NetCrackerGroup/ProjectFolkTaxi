package com.netcracker.services;

import com.netcracker.entities.InfoContent;
import com.netcracker.repositories.InfoContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfoContentService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupService.class);


    private InfoContentRepository infoContentRepository;

    @Autowired
    public void setInfoRepository(InfoContentRepository infoContentRepository) {
        this.infoContentRepository = infoContentRepository;
    }

    public InfoContent getInfoContentByKey (String key) {
        Optional<InfoContent> infoContent = infoContentRepository.findByKey(key);
        if ( !infoContent.isPresent() ) {
            LOG.error(" Database don't have info with key : {}", key);
        }
        return infoContent.get();
    }
}
