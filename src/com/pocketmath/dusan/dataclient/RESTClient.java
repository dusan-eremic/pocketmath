package com.pocketmath.dusan.dataclient;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * An implementation of the {@link DataClient} to access the data via REST APIs.
 * 
 * @author Dusan Eremic
 *
 */
@Singleton
public class RESTClient implements DataClient {

	/** The HTTP client */
	private final Client client;
	/** The base API path */
	private final String basePath;
	/** The x-api-key */
	private final String apiKey;

	/**
	 * Constructor
	 * 
	 * @param basePath
	 *            The base API path
	 * @param apiKey
	 *            The x-api-key
	 */
	public RESTClient(final String basePath, final String apiKey) {
		this.basePath = basePath;
		this.apiKey = apiKey;

		JacksonJsonProvider json = new JacksonJsonProvider();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		json.setMapper(mapper);
		client = ClientBuilder.newClient().register(json);
	}

	/**
	 * Finds all the entities of a given class.
	 * 
	 * @param clazz
	 *            Entity class to look for
	 * @return A list of all entities of a given class
	 */
	@Override
	public <T> List<T> findAll(final Class<T> clazz) {

		final String path = clazz.getAnnotation(Path.class).value();

		if (path == null) {
			throw new IllegalArgumentException("Unsupported entity class " + clazz.getName());
		}

		final ParameterizedType parameterizedGenericType = new ParameterizedType() {
			public Type[] getActualTypeArguments() {
				return new Type[] { clazz };
			}

			public Type getRawType() {
				return List.class;
			}

			public Type getOwnerType() {
				return List.class;
			}
		};

		final GenericType<List<T>> genericType = new GenericType<List<T>>(parameterizedGenericType) {
		};

		final Response response = client.target(basePath).path(path).request().header("x-api-key", apiKey)
				.accept(MediaType.APPLICATION_JSON).get();

		if (response.getStatus() != 200) {
			throw new RuntimeException("Error : HTTP code : " + response.getStatus());
		}

		return response.readEntity(genericType);
	}

}
