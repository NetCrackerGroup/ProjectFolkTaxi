package com.netcracker.DTO.mappers;

import com.netcracker.DTO.ComplainDto;
import com.netcracker.DTO.UserModerDto;
import com.netcracker.entities.Complain;
import com.netcracker.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ComplainMapper extends AbstractMapper<Complain, ComplainDto> {

    public ComplainMapper() {
        super(Complain.class, ComplainDto.class);
    }
    @Autowired
    private UserModMapper userModMapper;
    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Complain.class, ComplainDto.class).addMapping(Complain::getUser, ComplainDto::setUser).setPostConverter(
                context -> {
                    Complain complain = context.getSource();
                    ComplainDto complainDto = context.getDestination();
                    User user = complain.getUser();
                    UserModerDto userDto = userModMapper.toDto(user);
                    complainDto.setUser(userDto);

                    return complainDto;
                }
                ).addMapping(Complain::getAdresat, ComplainDto::setAdresat).setPostConverter(
                context -> {
                    Complain complain = context.getSource();
                    ComplainDto complainDto = context.getDestination();
                    User adresat = complain.getAdresat();
                    UserModerDto adresatDto = userModMapper.toDto(adresat);
                    complainDto.setAdresat(adresatDto);

                    return complainDto;
                }
                );



    }


}
