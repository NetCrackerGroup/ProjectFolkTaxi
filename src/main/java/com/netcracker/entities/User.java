package com.netcracker.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "App_Users")
public class User extends Recipient{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "user_id")
	private Long userId;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id")
	private City cityId;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "fio")
	private String fio;
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name="routeId")
    Collection<Route> routes;


	@Column(name = "yandex_acount")
	private Long yandexAccount;

    public Collection<Journey> getJourneys() {
        return journeys;
    }

    public void setJourney(Collection<Journey> journey) {
        this.journeys = journey;
    }
    public void setOneMoreJourney(Journey journey) {

        if (!getJourneys().contains(journey)) {
            this.journeys.add(journey);
            journey.setUser(this);
        }
    }

    @ManyToMany(mappedBy = "users",cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    Collection<Journey> journeys;


    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @MapKey(name="groupId")
    Collection<Group> groups;

    @Column(name = "Passenger_Rating")
    private Double passengerRating;

    @Column(name="Password")
	   private String password;

    @Column(name ="Role_name")
	 private String securityRole;

	@Column(name = "Driver_Rating")
	private Double driverRating;

	@Size(min = 0, max = 100)
	@Column(name = "Info")
	private String info;

	@Column(name = "Image")
	private String image;

	public User() {	}

    public Long getNumberOfComplaints() {
        return numberOfComplaints;
    }

    public void setNumberOfComplaints(Long numberOfComplaints) {
        this.numberOfComplaints = numberOfComplaints;
    }

    @Column(name = "number_of_complaints")
    private Long numberOfComplaints;

    public Long getIsBan() {
        return isBan;
    }

    public void setIsBan(Long isBan) {
        this.isBan = isBan;
    }

    @Column(name = "is_ban")
    private Long isBan;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Column(name = "locked")
    private boolean locked;


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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) { this.info = info; }

	public Long getYandexAccount() {
		return yandexAccount;
	}

	public void setYandexAccount(Long yandexAccount) {
		this.yandexAccount = yandexAccount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return isLocked() == user.isLocked() &&
				Objects.equals(getUserId(), user.getUserId());
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", fio='" + fio + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", yandexAccount=" + yandexAccount +
				", passengerRating=" + passengerRating +
				", password='" + password + '\'' +
				", securityRole='" + securityRole + '\'' +
				", driverRating=" + driverRating +
				", info='" + info + '\'' +
				'}';
	}
}
