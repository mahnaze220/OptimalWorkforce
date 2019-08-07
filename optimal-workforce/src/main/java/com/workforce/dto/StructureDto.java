package com.workforce.dto;

import java.util.Arrays;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@Validated
public class StructureDto {

	@JsonProperty("ROOM_LIST")
	private Integer[] rooms;

	@JsonProperty("SENIOR_CAPACITY")
	private Integer seniorCapacity;

	@JsonProperty("JUNIOR_CAPACITY")
	private Integer juniorCapacity;

	public StructureDto(Integer[] rooms, Integer seniorCapacity, Integer juniorCapacity) {
		super();
		this.rooms = rooms;
		this.seniorCapacity = seniorCapacity;
		this.juniorCapacity = juniorCapacity;
	}

	/**
	 * Get Rooms
	 * @return Rooms
	 **/
	@ApiModelProperty(value = "")
	public Integer[] getRooms() {
		return rooms;
	}

	public void setRooms(Integer[] rooms) {
		this.rooms = rooms;
	}

	/**
	 * Get SeniorCapacity
	 * @return SeniorCapacity
	 **/
	@ApiModelProperty(value = "")
	public Integer getSeniorCapacity() {
		return seniorCapacity;
	}

	public void setSeniorCapacity(Integer seniorCapacity) {
		this.seniorCapacity = seniorCapacity;
	}

	/**
	 * Get JuniorCapacity
	 * @return JuniorCapacity
	 **/
	@ApiModelProperty(value = "")
	public Integer getJuniorCapacity() {
		return juniorCapacity;
	}

	public void setJuniorCapacity(Integer juniorCapacity) {
		this.juniorCapacity = juniorCapacity;
	}

	@Override
	public String toString() {
		return "StructureCleanerDto [rooms=" + Arrays.toString(rooms) + ", seniorCapacity=" + seniorCapacity
				+ ", juniorCapacity=" + juniorCapacity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((juniorCapacity == null) ? 0 : juniorCapacity.hashCode());
		result = prime * result + Arrays.hashCode(rooms);
		result = prime * result + ((seniorCapacity == null) ? 0 : seniorCapacity.hashCode());
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
		StructureDto other = (StructureDto) obj;
		if (juniorCapacity == null) {
			if (other.juniorCapacity != null)
				return false;
		} else if (!juniorCapacity.equals(other.juniorCapacity))
			return false;
		if (!Arrays.equals(rooms, other.rooms))
			return false;
		if (seniorCapacity == null) {
			if (other.seniorCapacity != null)
				return false;
		} else if (!seniorCapacity.equals(other.seniorCapacity))
			return false;
		return true;
	}
	
}

