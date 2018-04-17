package unico.soap.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * The data repository for the computed greatest common divisors
 * 
 * @author Arnon Ruangthanawes
 */
public class GcdTransactionRepository {

	/**
	 * Mock database to store the values added.
	 */
	List<Integer> database = new ArrayList<Integer>();
	
	public void save(int data) {
		database.add(data);
	}

	/**
	 * Get all the data in the database.
	 * @return All the data in the database.
	 */
	public List<Integer> getAll() {
		return database;
	}
}
