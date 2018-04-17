package unico.restfull.jms;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import unico.restfull.document.StringTransaction;
import unico.restfull.repositories.StringTransactionRepository;

/**
 * Implementing the JMS queue
 * 
 * @author Arnon Ruangthanawes
 */
@Component
public class StringTransactionReceiver {

	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger(StringTransactionReceiver.class.getName());
	
	/**
	 * Inject the repository
	 */
	@Autowired
	private StringTransactionRepository stringTransactionRepository;

	/**
	 * Receive the JMS message and add it to the repository.
	 * @param transaction
	 */
	@JmsListener(destination = "AddStringTransactionDestination", containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(StringTransaction transaction) {
		logger.info("Received <" + transaction + ">");
		stringTransactionRepository.save(transaction);
	}

}
