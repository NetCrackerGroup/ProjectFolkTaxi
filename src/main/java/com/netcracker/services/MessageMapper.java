package com.netcracker.services;

import com.netcracker.DTO.MessageDto;
import com.netcracker.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MessageMapper extends AbstractMapper<Message, MessageDto> {
    public MessageMapper() {
        super(Message.class, MessageDto.class);
    }

     public List<MessageDto>mapInntoDto(List<Message> entities){

       return entities.stream().map(e -> this.toDto(e)).collect(Collectors.toList());
        //entities.forEach(e -> dtos.add(this.toDto(e)));
    }
  public Page<MessageDto> maoPagetoDto(Pageable pageRequest, Page<Message> source){
        List<MessageDto> dtos = mapInntoDto(source.getContent());
        return new PageImpl<>(dtos,pageRequest,source.getTotalElements());
    }
}
