package com.pocketmath.dusan.dataclient;

import java.util.List;

/**
 * The {@link DataClient} is used to access the data
 * 
 * @author Dusan Eremic
 *
 */
public interface DataClient {

	/**
	 * Finds all the entities of a given class.
	 * 
	 * @param clazz
	 *            Entity class to look for
	 * @return A list of all entities of a given class
	 */
	public <T> List<T> findAll(Class<T> clazz);

}
