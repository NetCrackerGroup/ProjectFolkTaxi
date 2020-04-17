package com.netcracker.controllers;

import com.netcracker.DTO.ComplainDto;
import com.netcracker.DTO.mappers.ComplainMapper;
import com.netcracker.entities.Complain;
import com.netcracker.services.ComplainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Complains")
public class ComplainController {

    private static final Logger LOG = LoggerFactory.getLogger(ComplainController.class);


    @Autowired
    private ComplainService complainService;

    @Autowired
    private ComplainMapper complainMapper;

    @DeleteMapping("/deleteComplain")
    public void deleteComplain(@RequestParam(value = "id") Long complainId) {

        LOG.info("[ deleteComplain(complainId : {})", complainId);

        complainService.deleteComplain(complainId);

        LOG.info("]");
    }

    @PostMapping("/sendComplain/{adresatId}")
    public ComplainDto sendComlain(@PathVariable(value = "adresatId") Long adresatId, @RequestParam(value="text") String text){

        LOG.info("[sendComplain(adresatId : {})", adresatId);

        Complain complain = complainService.sendComplain(adresatId, text);
        ComplainDto complainDto = complainMapper.toDto(complain);

        LOG.info("]");

        return complainDto;

    }

    @GetMapping("/getComplains/{userId}")
    public List<ComplainDto> findComplainsByUser(@PathVariable(value = "userId") Long userId){
        LOG.info("[findComplainsByUser(userId:{}", userId);

        List<Complain> complains = complainService.findComplainsByUser(userId);

        List<ComplainDto>newlist= new ArrayList<ComplainDto>();
        for (Complain complain: complains) {
            newlist.add(complainMapper.toDto(complain));
        }
        return newlist;
    }

    @GetMapping("/getComplain/{complainId}")
    public ComplainDto getComplainById(@RequestParam(value = "complainId")Long complainId){
        LOG.info("[findComplainById(complainId:{}", complainId);

        Complain complain = complainService.getComplainById(complainId);

        ComplainDto complainDto = complainMapper.toDto(complain);

        return complainDto;
    }
}
