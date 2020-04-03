package com.netcracker.services.Channels;

import com.netcracker.RenderTemplate.DataParser;
import com.netcracker.entities.InfoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.midi.MidiDevice;
import java.util.Collection;
import java.util.Map;


public class FillInfoContent {

    private Map<String, String> data;

    public FillInfoContent(Map<String, String> data) {
        this.data = data;
    }

    public Collection<InfoMap> fill(Collection<InfoMap> infoMaps) {
        infoMaps.forEach( v -> v.setInfoValue(data.getOrDefault(v.getInfoKey(), "null")));
        return infoMaps;
    }
}