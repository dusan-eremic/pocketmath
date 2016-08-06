package com.pocketmath.dusan.service;

import java.util.List;

import com.pocketmath.dusan.model.Trader;

/**
 * The set of services for handling the {@link Trader} entity.
 * 
 * @author Dusan Eremic
 *
 */
public interface TraderService {

	/**
	 * Finds all traders from a given city.
	 * 
	 * @param city
	 *            City where the trader is located
	 * @return A sorted list (by name) of traders located in a given city.
	 */
	List<Trader> findByCity(String city);

}
