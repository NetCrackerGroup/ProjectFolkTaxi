//package com.netcracker;
//
//import com.netcracker.RenderTemplate.DataParsable;
//import com.netcracker.entities.InfoMap;
//import com.netcracker.services.GroupService;
//import com.netcracker.services.InfoContentService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Collection;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestParser {
//
//    private static final Logger LOG = LoggerFactory.getLogger(TestParser.class);
//    @Autowired
//    private DataParsable dataParsable;
//    @Autowired
//    InfoContentService infoContentService;
//
//    @Test
//    public void testParser() {
//
//        String template = "test $$$test$$$ test";
//        Collection<InfoMap> infomaps = dataParsable.parser(infoContentService.getInfoContentByKey("user_entered_group"));
//        infomaps.forEach(x -> LOG.debug("{}", x));
//        Assert.assertEquals(infomaps.size() , 1);
//    }
//
//}
