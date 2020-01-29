package com.netcracker.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "Cities")
@Component
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_generator")
	@SequenceGenerator(name = "city_id_generator", sequenceName = "city_id_seq", allocationSize = 1)
	@Column(name = "city_id")
	private Long cityId;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "city_name")
	private String cityName;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "city_map")
	private String cityMap;
	
	public City(){
		
	}
	
	public City(@NotNull @Size(min = 1, max = 50) String cityName,
				@NotNull @Size(min = 1, max = 200) String cityMap) {
		this.cityName = cityName;
		this.cityMap = cityMap;
	}
	
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityMap() {
		return cityMap;
	}

	public void setCityMap(String cityMap) {
		this.cityMap = cityMap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityId);
	}

	@Override
	public boolean equals(Object obj) {
		 if (this == obj) return true;
		 if (obj == null || getClass() != obj.getClass()) return false;
		 City city = (City) obj;
		 return cityId.equals(city.cityId);
	}

	@Override
	public String toString() {
	    return "City{" +
	            "cityId=" + cityId +
	            ", cityName='" + cityName +
	            ", cityMap=" + cityMap +
	            '}';
	}
}
