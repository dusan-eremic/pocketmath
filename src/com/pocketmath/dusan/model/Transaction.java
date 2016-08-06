package com.pocketmath.dusan.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.ws.rs.Path;

/**
 * The Transaction entity class.
 * 
 * @author Dusan Eremic
 *
 */
@Path("transactions") // API path
public class Transaction implements Comparable<Transaction> {

	private Instant timestamp;
	private String traderId;
	private BigDecimal value;

	public Transaction() {

	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((traderId == null) ? 0 : traderId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Transaction other = (Transaction) obj;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (traderId == null) {
			if (other.traderId != null)
				return false;
		} else if (!traderId.equals(other.traderId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[timestamp=" + timestamp + ", traderId=" + traderId + ", value=" + value + "]";
	}

	@Override
	public int compareTo(Transaction t) {
		return this.value.compareTo(t.value);
	}
}
