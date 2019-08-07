package com.workforce.service;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */
public class Cleaner {

	Integer seniorCleaner;
	Integer juniorCleaner;

	public Cleaner(Integer seniorCleaner, Integer juniorCleaner) {
		this.seniorCleaner = seniorCleaner;
		this.juniorCleaner = juniorCleaner;
	}

	public Integer getSeniorCleaner() {
		return seniorCleaner;
	}

	public Integer getJuniorCleaner() {
		return juniorCleaner;
	}

	@Override
	public String toString() {
		return "senior: " + seniorCleaner + ",junior: " + juniorCleaner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((juniorCleaner == null) ? 0 : juniorCleaner.hashCode());
		result = prime * result + ((seniorCleaner == null) ? 0 : seniorCleaner.hashCode());
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
		Cleaner other = (Cleaner) obj;
		if (juniorCleaner == null) {
			if (other.juniorCleaner != null)
				return false;
		} else if (!juniorCleaner.equals(other.juniorCleaner))
			return false;
		if (seniorCleaner == null) {
			if (other.seniorCleaner != null)
				return false;
		} else if (!seniorCleaner.equals(other.seniorCleaner))
			return false;
		return true;
	}
}
