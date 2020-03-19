package com.netcracker.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "App_Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "user_id")
	private Long userId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id")
	private City cityId;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "fio")
	private String fio;
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "email", unique = true)
	private String email;
	
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	Collection<Route> routes;
  
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    Collection<Group> groups;

    @Column(name = "Passenger_Rating")
    private Double passengerRating;

    @Column(name="Password")
	   private String password;

    @Column(name ="Role_name")
	 private String securityRole;



	@Column(name = "Driver_Rating")
	private Double driverRating;
	
	public User() {
		
	}


	public String getSecurityRole() {
		return securityRole;
	}

	public void setSecurityRole(String securityRole) {
		this.securityRole = securityRole;
	}

	public User(@NotNull @Size(min = 1, max = 200) String fio,
				@NotNull @Size(min = 1, max = 100) String email,
				@NotNull @Size(min = 1, max = 20) String phoneNumber,
				@NotNull City city,
				@NotNull @Size(min = 1, max = 100) String password,
				@NotNull String securityRole
	) {
		this.fio = fio;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.driverRating = Double.valueOf(0);
		this.passengerRating = Double.valueOf(0);
		this.cityId = city;
		this.password = password;
		this.securityRole = securityRole;

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

	public Double getPassengerRating() { return passengerRating; }

	public void setRoutes(Collection<Route> routes) { this.routes = routes; }

	public void setGroups(Collection<Group> groups) { this.groups = groups; }

	public void setPassengerRating(Double passengerRating) { this.passengerRating = passengerRating; }

	public void setDriverRating(Double driverRating) { this.driverRating = driverRating; }

	public Double getDriverRating() { return driverRating; }

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Collection<Route> getRoutes() { return routes; }

	public Collection<Group> getGroups() { return groups; }
	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

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
