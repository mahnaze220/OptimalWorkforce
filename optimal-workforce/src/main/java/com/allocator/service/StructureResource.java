package com.allocator.service;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */
public class StructureResource {

	Integer seniorResource;
	Integer juniorResource;

	public StructureResource(Integer seniorResource, Integer juniorResource) {
		this.seniorResource = seniorResource;
		this.juniorResource = juniorResource;
	}

	public Integer getSeniorResource() {
		return seniorResource;
	}

	public Integer getJuniorResource() {
		return juniorResource;
	}

	@Override
	public String toString() {
		return "senior: " + seniorResource + ",junior: " + juniorResource;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((juniorResource == null) ? 0 : juniorResource.hashCode());
		result = prime * result + ((seniorResource == null) ? 0 : seniorResource.hashCode());
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
		if (juniorResource == null) {
			if (other.juniorResource != null)
				return false;
		} else if (!juniorResource.equals(other.juniorResource))
			return false;
		if (seniorResource == null) {
			if (other.seniorResource != null)
				return false;
		} else if (!seniorResource.equals(other.seniorResource))
			return false;
		return true;
	}
}
