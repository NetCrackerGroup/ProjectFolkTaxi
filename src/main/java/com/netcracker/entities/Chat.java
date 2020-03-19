package com.netcracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Chats")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_id_generator")
	@SequenceGenerator(name = "chat_id_generator", sequenceName = "chat_id_seq", allocationSize = 1)
    @NotNull
    @Column(name = "Chat_ID")
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Route_ID")
    private Route route;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Group_ID")
    private Group group;

	public Chat() {
	}

	public Chat(Route route, Group group) {
		this.route = route;
		this.group = group;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatId == null) ? 0 : chatId.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
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
		Chat other = (Chat) obj;
		if (chatId == null) {
			if (other.chatId != null)
				return false;
		} else if (!chatId.equals(other.chatId))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Chat{" +
				"chatId=" + chatId +
				", group=" + group +
				'}';
	}
}
