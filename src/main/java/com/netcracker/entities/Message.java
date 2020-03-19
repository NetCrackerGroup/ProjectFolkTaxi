package com.netcracker.entities;

import java.time.LocalDate;
import java.util.Comparator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_generator")
	@SequenceGenerator(name = "notification_id_generator", sequenceName = "notification_id_seq", allocationSize = 1)
    @NotNull
    @Column(name = "Message_ID")
    private Long messageId;

	public Message() {
	}

	@Column(name = "Text")
    private String text;
	
	@NotNull
    @Column(name = "Date_Of_Sending")
    private LocalDate dateOfSending;

	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Chat_ID")
    private Chat chat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_Message_ID")
	private User user;

	public Message(String text, @NotNull LocalDate dateOfSending, Chat chat) {
		this.text = text;
		this.dateOfSending = dateOfSending;
		this.chat = chat;

	}
	public static final Comparator<Message> comparatordate =
			(Message a, Message b) -> (comparer(a,b));

	public static  int comparer(Message a,Message b){
		java.time.LocalDate a1 = a.getDateOfSending();
		java.time.LocalDate b1 = b.getDateOfSending();
		int rezult;
		if (a1.isBefore(b1)) {
			rezult = 1;
		}
		if(a1.isAfter((b1))){
			rezult = -1;
		}
		else rezult = 0;
		return rezult;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getDateOfSending() {
		return dateOfSending;
	}

	public void setDateOfSending(LocalDate dateOfSending) {
		this.dateOfSending = dateOfSending;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chat == null) ? 0 : chat.hashCode());
		result = prime * result + ((dateOfSending == null) ? 0 : dateOfSending.hashCode());
		result = prime * result + ((messageId == null) ? 0 : messageId.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (chat == null) {
			if (other.chat != null)
				return false;
		} else if (!chat.equals(other.chat))
			return false;
		if (dateOfSending == null) {
			if (other.dateOfSending != null)
				return false;
		} else if (!dateOfSending.equals(other.dateOfSending))
			return false;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message{" +
				"messageId=" + messageId +
				", chat=" + chat +
				", user=" + user +
				'}';
	}
}
