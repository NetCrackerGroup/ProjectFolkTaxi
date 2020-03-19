package com.netcracker.entities;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "info"//,
    /*uniqueConstraints = {
        @UniqueConstraint(columnNames = "")
    }*/
)
public class InfoContent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_id_generator")
    @SequenceGenerator(name = "info_id_generator", sequenceName = "info_id_seq", allocationSize = 1)
    @Column(name = "Info_ID")
    private Long id;

    @Column(name = "Info_Key", unique = true)
    @UniqueElements
    private String key;

    @Column(name = "Info_Subject")
    private String subject;

    @Column(name = "Info_Text")
    private String text;

    public InfoContent() {
    }

    public InfoContent(String subject, String text) {
        this.subject = subject;
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoContent that = (InfoContent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getText());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
