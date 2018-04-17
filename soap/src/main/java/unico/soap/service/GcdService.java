package unico.soap.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import unico.soap.repository.GcdTransactionRepository;

/**
 * The business service of Greatest Common Divisor (GCD) 
 * 
 * @author Arnon Ruangthanawes
 *
 */
public class GcdService {

	/**
	 * Inject the database
	 */
	@Autowired
	GcdTransactionRepository gcdTransactionRepository;
	
	/**
	 * Compute the greatest common divisor of the two integers.
	 * 
	 * @param a first integer
	 * @param b second integer
	 * @return the greatest common divisor of the two integers.
	 */
	public int gcd(int a, int b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		
		int result = gcd.intValue();
		gcdTransactionRepository.save(result);
		return result;
	}

	/**
	 * Get the list of all the computed greatest common divisors from a database.
	 * @return The list of all the computed greatest common divisors from a database.
	 */
	public List<Integer> getAllComputedGcd() {
		return gcdTransactionRepository.getAll();
	}
	
	/**
	 * Get the sum of all computed greatest common divisors from a database.
	 * @return The sum of all computed greatest common divisors from a database.
	 */
	public int gcdSum() {
		List<Integer> allGcd = getAllComputedGcd();
		int sum = allGcd.stream().mapToInt(Integer::intValue).sum();
		return sum;
	}

}
