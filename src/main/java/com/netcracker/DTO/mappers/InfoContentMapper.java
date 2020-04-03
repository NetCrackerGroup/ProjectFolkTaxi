package com.netcracker.DTO.mappers;

import com.netcracker.DTO.InfoContentDTO;
import com.netcracker.entities.InfoContent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InfoContentMapper extends AbstractMapper<InfoContent, InfoContentDTO> {

    public InfoContentMapper() {
        super(InfoContent.class, InfoContentDTO.class);
    }
}

