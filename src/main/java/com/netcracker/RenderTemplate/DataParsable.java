package com.netcracker.RenderTemplate;

import com.netcracker.entities.InfoContent;
import com.netcracker.entities.InfoMap;

import java.util.Collection;

public interface DataParsable {
    Collection<InfoMap> parser(InfoContent infoContent);
}
