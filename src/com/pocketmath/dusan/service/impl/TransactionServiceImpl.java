package com.pocketmath.dusan.service.impl;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.pocketmath.dusan.dataclient.DataClient;
import com.pocketmath.dusan.model.Trader;
import com.pocketmath.dusan.model.Transaction;
import com.pocketmath.dusan.service.TransactionService;

/**
 * The implementation of {@link TransactionService}
 * 
 * @author Dusan Eremic
 *
 */
@Singleton
public class TransactionServiceImpl implements TransactionService {

	@Inject
	private DataClient client;

	/**
	 * Finds the transaction with the highest value
	 * 
	 * @return The transaction with the highest value
	 */
	@Override
	public Transaction findMaxTransaction() {
		return client.findAll(Transaction.class).stream().max((t1, t2) -> t1.getValue().compareTo(t2.getValue())).get();
	}

	/**
	 * Finds transactions by year and sort them by value (high to small)
	 * 
	 * @param year
	 *            A year to look for
	 * @return List of all transaction created in a given year
	 */
	@Override
	public List<Transaction> findByYear(final int year) {
		return client.findAll(Transaction.class).stream()
				.filter(t -> t.getTimestamp().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == year)
				.sorted((t1, t2) -> t2.getValue().compareTo(t1.getValue())).collect(Collectors.toList());
	}

	/**
	 * Find the average of transactions' values from the traders living in a
	 * given city.
	 * 
	 * @param city
	 *            A city to look for.
	 * @return THe transactions' average for a given city.
	 */
	@Override
	public BigDecimal getAverageByCity(final String city) {
		Map<String, Trader> tradersByCity = client.findAll(Trader.class).stream()
				.filter(t -> t.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toMap(Trader::getId, Function.identity()));

		List<Transaction> transactionsByCity = client.findAll(Transaction.class).stream()
				.filter(trans -> tradersByCity.containsKey(trans.getTraderId())).collect(Collectors.toList());

		return transactionsByCity.stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(BigDecimal.valueOf(transactionsByCity.size()));
	}

}
