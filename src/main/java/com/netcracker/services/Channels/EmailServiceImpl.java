package com.netcracker.services.Channels;

import com.netcracker.entities.InfoMap;
import com.netcracker.entities.Recipient;
import com.netcracker.entities.User;
import com.netcracker.models.DomenUser;
import com.netcracker.entities.InfoContent;
import freemarker.cache.TemplateLoader;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class EmailServiceImpl implements Deliverable{

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    private JavaMailSender emailSender;
    private DomenUser domenUser;

    @Value("${dev.url}")
    private String url;

    @Autowired
    Configuration configuration;

    @Autowired
    private EmailServiceImpl(
        JavaMailSender javaMailSender,
        DomenUser domenUser )
    {
        this.emailSender = javaMailSender;
        this.domenUser = domenUser;
    }


    @Override
    public void deliver(InfoContent message, Recipient recipient, Collection<InfoMap> infoMaps) throws MessagingException {

            User user = (User) recipient;

            message.getText();

            LOG.debug("InfoMaps : {}" , infoMaps);

            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException, TemplateException {
                    mimeMessage.setFrom(domenUser.getDomenName());
                    //mimeMessage.setText(message);

                    //Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
                    //cfg.setDirectoryForTemplateLoading();
                   // cfg.setTemplateLoader();
                    Map<String, Object> root = new HashMap<>();
                    String[] text = message.getText().split(Pattern.quote("$$$"));
                    LOG.debug("Text : {}",  Arrays.toString(text));
                    for ( int i = 1; i < text.length; i+=2 ){
                        LOG.debug("{} template : {}", i, text[i]);
                        for ( InfoMap infoMap : infoMaps) {
                            LOG.debug("{} infomaps : {}" , i, infoMap.getInfoKey());
                            if (text[i].trim().equals(infoMap.getInfoKey().trim())) {
                                text[i] = infoMap.getInfoValue().trim();
                                break;
                            }
                        }
                    }
                    root.put("msg", String.join(" ", text).trim());
                    root.put("url", url);

                    Template temp = configuration.getTemplate("notification.ftl");
                    Writer writer = new StringWriter() ;
                    temp.process(root, writer);

                    String html = writer.toString();
                    html = html.replace("\\n", "<br/>");

                    mimeMessage.setContent(html, "text/html; charset=UTF-8");
                    mimeMessage.setSubject(message.getSubject(), "UTF-8");
                    mimeMessage.setSentDate(new Date());
                    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

                    mimeMessage.setHeader("Content-Type","text/html; charset=UTF-8");
                    mimeMessage.setHeader("Content-Transfer-Encoding","base64");
                }
            };
            emailSender.send(preparator);
    }
}