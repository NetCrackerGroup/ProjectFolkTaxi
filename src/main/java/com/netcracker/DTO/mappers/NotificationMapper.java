package com.netcracker.DTO.mappers;

import com.netcracker.DTO.InfoContentDTO;
import com.netcracker.DTO.NotificationDTO;
import com.netcracker.entities.InfoContent;
import com.netcracker.entities.InfoMap;
import com.netcracker.entities.Notification;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Convert;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class NotificationMapper extends AbstractMapper<Notification, NotificationDTO> {

    @Autowired
    private InfoContentMapper infoContentMapper;

    public NotificationMapper() {
        super(Notification.class, NotificationDTO.class);
    }

    @PostConstruct
    public void setupMapper () {

        Converter< Collection<InfoMap> , Collection<String> > convert =
                (ctg) -> {
                    Collection<String> mapInfo = new LinkedList<>();
                    mapInfo.addAll(
                            ctg.getSource()
                                    .stream()
                                    .map(x -> String.format("%s : %s", x.getInfoKey(), x.getInfoValue()))
                                    .collect(Collectors.toCollection(LinkedList::new))
                    );

                    return mapInfo;
                };
        Converter<InfoContent, InfoContentDTO> converterInfo =
                ctg -> {
                    InfoContentDTO infoContentDTO = infoContentMapper.toDto(ctg.getSource());
                    return infoContentDTO;
                };
        mapper.typeMap(Notification.class, NotificationDTO.class).addMappings( mapper -> {
                    mapper.using(convert).map(Notification::getTemplatevalues, NotificationDTO::setTemplatevalues);
                    mapper.using(converterInfo).map(Notification::getInfoContent, NotificationDTO::setInfoContent);
                }
        );
    }

}
