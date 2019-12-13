package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
//import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user_1")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "user_id")
	private Long userId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City cityId;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "fio")
	private String fio;
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "email")
	private String email;
	
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	Collection<Route> routes;
	
	public User() {
		
	}
	
	/*@OneToOne(mappedBy = "users", fetch = FetchType.EAGER)
	  Collection<City> cities;*/
	
	public User(@NotNull @Size(min = 1, max = 200) String fio,
				@NotNull @Size(min = 1, max = 20) String phoneNumber
				) {
		this.fio = fio;
		this.phoneNumber = phoneNumber;
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
		this.cityId = cityId;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    User user = (User) obj;
	    return userId.equals(user.userId);
	}

	@Override
	public String toString() {
		return "User{"+
				"userId=" + userId + 
				", cityId=" + cityId + 
				", fio=" + fio + 
				", email=" + email + 
				", phoneNumber=" + phoneNumber + 
				"}";
	}
	
}
