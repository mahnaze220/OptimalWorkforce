package com.workforce.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@Validated
public class StructureCleanerDto {

	@JsonProperty("room")
	private Integer room;

	@JsonProperty("senior")
	private Integer numberOfSeniors;

	@JsonProperty("junior")
	private Integer numberOfJuniors;

	public StructureCleanerDto(Integer room, Integer numberOfSeniors, Integer numberOfJuniors) {
		super();
		this.room = room;
		this.numberOfSeniors = numberOfSeniors;
		this.numberOfJuniors = numberOfJuniors;
	}

	/**
	 * Get room
	 * @return room
	 **/
	@ApiModelProperty(value = "")
	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	/**
	 * Get numberOfSeniors
	 * @return numberOfSeniors
	 **/
	@ApiModelProperty(value = "")
	public Integer getNumberOfSeniors() {
		return numberOfSeniors;
	}

	public void setNumberOfSeniors(Integer numberOfSeniors) {
		this.numberOfSeniors = numberOfSeniors;
	}

	/**
	 * Get numberOfJuniors
	 * @return numberOfJuniors
	 **/
	@ApiModelProperty(value = "")
	public Integer getNumberOfJuniors() {
		return numberOfJuniors;
	}

	public void setNumberOfJuniors(Integer numberOfJuniors) {
		this.numberOfJuniors = numberOfJuniors;
	}

	@Override
	public String toString() {
		return "room=" + room + "{senior:" + numberOfSeniors + ", junior:"
				+ numberOfJuniors + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numberOfJuniors == null) ? 0 : numberOfJuniors.hashCode());
		result = prime * result + ((numberOfSeniors == null) ? 0 : numberOfSeniors.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
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
		StructureCleanerDto other = (StructureCleanerDto) obj;
		if (numberOfJuniors == null) {
			if (other.numberOfJuniors != null)
				return false;
		} else if (!numberOfJuniors.equals(other.numberOfJuniors))
			return false;
		if (numberOfSeniors == null) {
			if (other.numberOfSeniors != null)
				return false;
		} else if (!numberOfSeniors.equals(other.numberOfSeniors))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}
	
}
