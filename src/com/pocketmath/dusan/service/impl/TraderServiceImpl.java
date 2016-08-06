package com.pocketmath.dusan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.pocketmath.dusan.dataclient.DataClient;
import com.pocketmath.dusan.model.Trader;
import com.pocketmath.dusan.service.TraderService;

/**
 * The implementation of {@link TraderService}
 * 
 * @author Dusan Eremic
 *
 */
@Singleton
public class TraderServiceImpl implements TraderService {

	@Inject
	private DataClient client;

	/**
	 * Finds all traders from a given city.
	 * 
	 * @param city
	 *            City where the trader is located
	 * @return A sorted list (by name) of traders located in a given city.
	 */
	@Override
	public List<Trader> findByCity(final String city) {
		return client.findAll(Trader.class).stream().filter(t -> t.getCity().equalsIgnoreCase(city)).sorted()
				.collect(Collectors.toList());
	}
}
