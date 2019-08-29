package com.allocator.data;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */
public class StructureResource {

	private Integer room;

	private Integer numberOfSeniors;

	private Integer numberOfJuniors;

	public StructureResource(Integer room, Integer numberOfSeniors, Integer numberOfJuniors) {
		super();
		this.room = room;
		this.numberOfSeniors = numberOfSeniors;
		this.numberOfJuniors = numberOfJuniors;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public Integer getNumberOfSeniors() {
		return numberOfSeniors;
	}

	public void setNumberOfSeniors(Integer numberOfSeniors) {
		this.numberOfSeniors = numberOfSeniors;
	}

	public Integer getNumberOfJuniors() {
		return numberOfJuniors;
	}

	public void setNumberOfJuniors(Integer numberOfJuniors) {
		this.numberOfJuniors = numberOfJuniors;
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
		StructureResource other = (StructureResource) obj;
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

	@Override
	public String toString() {
		return "room=" + room + "{senior:" + numberOfSeniors + ", junior:"
				+ numberOfJuniors + "}";
	}
}
