package com.pocketmath.dusan;

import javax.inject.Inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.pocketmath.dusan.dataclient.DataClient;
import com.pocketmath.dusan.dataclient.RESTClient;
import com.pocketmath.dusan.service.TraderService;
import com.pocketmath.dusan.service.TransactionService;
import com.pocketmath.dusan.service.impl.TraderServiceImpl;
import com.pocketmath.dusan.service.impl.TransactionServiceImpl;

/**
 * The Main class
 * 
 * @author Dusan Eremic
 *
 */
public class App {

	private final static String basePath = "https://fvjkpkflnc.execute-api.us-east-1.amazonaws.com/prod";

	@Inject
	private TraderService traderService;

	@Inject
	private TransactionService transactionService;

	public static void main(String[] args) {

		// Check if x-api-key is provided
		if (args.length == 0 || args[0] == null || args[0].isEmpty()) {
			System.err.println("x-api-key is not provided as arg[0]");
			System.exit(1);
		}

		// Dependency injection bindings
		Guice.createInjector(new AbstractModule() {

			@Override
			protected void configure() {
				bind(DataClient.class).toInstance(new RESTClient(basePath, args[0]));
				bind(TraderService.class).to(TraderServiceImpl.class);
				bind(TransactionService.class).to(TransactionServiceImpl.class);
			}
		}).getInstance(App.class).run();
	}

	/**
	 * A sample run as request per Pocketmath assignment
	 */
	public void run() {
		try {
			System.out.println("\nFind all traders from Singapore and sort them by name:");
			traderService.findByCity("Singapore").stream().forEach(System.out::println);

			System.out.println("\nFind the transaction with the highest value:");
			System.out.println(transactionService.findMaxTransaction());

			System.out.println("\nFind all transactions in the year 2016 and sort them by value (high to small):");
			transactionService.findByYear(2016).forEach(System.out::println);

			System.out.print("\nFind the average of transactions' values from the traders living in Beijing.: ");
			System.out.println(transactionService.getAverageByCity("Beijing"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}