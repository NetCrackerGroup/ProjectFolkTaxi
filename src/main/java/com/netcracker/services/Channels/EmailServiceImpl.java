package com.netcracker.services.Channels;

import com.netcracker.entities.User;
import com.netcracker.models.DomenUser;
import com.netcracker.entities.InfoContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

@Component
public class EmailServiceImpl implements Deliverable<User>{

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    private JavaMailSender emailSender;
    private DomenUser domenUser;

    @Autowired
    private EmailServiceImpl(
        JavaMailSender javaMailSender,
        DomenUser domenUser )
    {
        this.emailSender = javaMailSender;
        this.domenUser = domenUser;
    }


    @Override
    public void deliver(InfoContent message, User user) throws MessagingException {
            message.getText();
            String html = "<div style=\"background-color: gray;\">\n" +
                    "    <div style=\"background-color : white; padding : 5px; margin : 10px 15px 0 15px; border: 1px solid black;\">\n" +
                    "        <h2 style=\"padding-left: 15px; color :darkgoldenrod\">Folk taxi</h3>\n" +
                    "        <p style=\"color: black;\"> Вы были удалены из группы $$$groupname$$$ за оскорбления! </p>\n" +
                    "    </div>\n" +
                    "    <div style=\"background-color: #000036; padding : 1em 0 1em 4em;\">\n" +
                    "        <p> \n" +
                    "            <a href=\"http://localhost:4200\" style=\"font-size:medium; color: white;\">Найти маршруты</a>\n" +
                    "        </p>\n" +
                    "    </div>\n" +
                    "</div>\n";

            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {
                    mimeMessage.setFrom(domenUser.getDomenName());
                    //   mimeMessage.setText(message);

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