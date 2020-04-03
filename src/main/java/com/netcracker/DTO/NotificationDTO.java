package com.netcracker.DTO;


import com.netcracker.entities.InfoContent;

import java.sql.Timestamp;
import java.text.CollationElementIterator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NotificationDTO {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    private InfoContentDTO infoContent;
    Collection<String> templatevalues;
    private Timestamp timestamp;


    public NotificationDTO() {
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public InfoContentDTO getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(InfoContentDTO infoContent) {
        this.infoContent = infoContent;
    }

    public Collection<String> getTemplatevalues() {
        return templatevalues;
    }

    public void setTemplatevalues(Collection<String> templatevalues) {
        this.templatevalues = templatevalues;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
