package com.pocketmath.dusan.model;

import javax.ws.rs.Path;

/**
 * The Trader entity class.
 * 
 * @author Dusan Eremic
 *
 */
@Path("traders") // API path
public class Trader implements Comparable<Trader> {

	/** The unique trader ID */
	private String id;
	/** Trade name */
	private String name;
	/** Trader city */
	private String city;

	public Trader() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Trader other = (Trader) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", city=" + city + "]";
	}

	@Override
	public int compareTo(Trader t) {
		return this.name.compareTo(t.name);
	}
}
