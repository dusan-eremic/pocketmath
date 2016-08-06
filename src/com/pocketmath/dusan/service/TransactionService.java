package com.pocketmath.dusan.service;

import java.math.BigDecimal;
import java.util.List;

import com.pocketmath.dusan.model.Transaction;

/**
 * The set of services for handling the {@link Transaction} entity.
 * 
 * @author Dusan Eremic
 *
 */
public interface TransactionService {

	/**
	 * Finds the transaction with the highest value
	 * 
	 * @return The transaction with the highest value
	 */
	Transaction findMaxTransaction();

	/**
	 * Finds transactions by year and sort them by value (high to small)
	 * 
	 * @param year
	 *            A year to look for
	 * @return List of all transaction created in a given year
	 */
	List<Transaction> findByYear(int year);

	/**
	 * Find the average of transactions' values from the traders living in a
	 * given city.
	 * 
	 * @param city
	 *            A city to look for.
	 * @return THe transactions' average for a given city.
	 */
	BigDecimal getAverageByCity(String city);
}
