package unico.restfull.repositories;

import java.util.ArrayList;
import java.util.List;

import unico.restfull.config.document.StringTransaction;

/**
 * The data repository for {@link StringTransaction}
 * 
 * @author Arnon Ruangthanawes
 */
public class StringTransactionRepository {

	/**
	 * Mock database to store the values added.
	 */
	List<Integer> database = new ArrayList<Integer>();
	
	public void save(StringTransaction data) {
		database.add(data.getValue1());
		database.add(data.getValue2());
	}

	/**
	 * Get all the data in the database.
	 * @return All the data in the database.
	 */
	public List<Integer> getAll() {
		return database;
	}
}
