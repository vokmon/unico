package unico.restfull.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import unico.restfull.config.constant.Constant;
import unico.restfull.config.document.StringTransaction;
import unico.restfull.repositories.StringTransactionRepository;

/**
 * The controller to manipulate strings
 * 
 * @author Arnon Ruangthanawes
 *
 */
@RestController
public class StringController {

	/**
	 * The {@JmsTemplate} for sending message.
	 */
	private JmsTemplate jmsTemplate;

	/**
	 * Request status for successful case.
	 */
	private final String result_success = "Successfully sent %s and %s to Jms.";

	/**
	 * Request status for faled case.
	 */
	private final String result_failed = "Failed sent %s and %s to Jms.";

	/**
	 * Inject the repository
	 */
	@Autowired
	private StringTransactionRepository stringTransactionRepository;

	@Autowired
	private ConnectionFactory connectionFactory;

	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger(StringController.class.getName());
	
	@PostConstruct
	public void init() {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
	}

	@GetMapping("/send")
	public String push(@RequestParam("i1") int i1, @RequestParam("i2") int i2) {
		try {
			/*
			 * Receive data from the url as get parameter in order to test
			 * easily.
			 */
			StringTransaction transaction = new StringTransaction();
			transaction.setValue1(i1);
			transaction.setValue1(i2);

			/* Add the requested values to the jms queue and sent */
			jmsTemplate.convertAndSend(Constant.STRING_ADDED_TRANSACTION_DESTINATION, transaction);

			return String.format(result_success, i1, i2);
		} catch (JmsException e) {
			logger.log(Level.SEVERE, "An error occurs while sending a message to jms service", e);
			return String.format(result_failed, i1, i2);
		}

	}

	/**
	 * Get the list of all elements added to queue from a database
	 * 
	 * @return a list of all the elements ever added to the queue from a
	 *         database in the order added as a JSON structure.
	 */
	@GetMapping("/list")
	public List<Integer> list() {
		return stringTransactionRepository.getAll();
	}

}
