package com.netcracker.RenderTemplate;

import com.netcracker.entities.InfoContent;
import com.netcracker.entities.InfoMap;
import com.netcracker.repositories.InfoMapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Pattern;

@Component
public class DataParser implements DataParsable{

    @Autowired
    private InfoMapRepository infoMapRepository;
    private static final Logger LOG = LoggerFactory.getLogger(DataParsable.class);

    public DataParser() { }

    @Override
    public Collection<InfoMap> parser(InfoContent infoContent) {
        Collection<InfoMap> infomaps = new LinkedList<>();
        String text = infoContent.getText();
        LOG.debug("InfoContent :  {}" , infoContent);
        String[] lines = text.split(Pattern.quote("$$$"));
        for ( int i = 0; i < lines.length; i++ ) {
            if ( i % 2 == 1 ) {
                InfoMap infoMap = new InfoMap();
                infoMap.setInfoKey(lines[i]);
                infomaps.add(infoMap);
            }
        }
        return infomaps;
    }
}