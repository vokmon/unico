package unico.restfull.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import unico.restfull.repositories.StringTransactionRepository;

/**
 * Restfull App Configuration. Defines the classes that will be scanned for the
 * web interface.
 * 
 * @author Arnon Ruangthanawes
 */

@Configuration
@EnableWebMvc
@EnableJms
@ComponentScan({ "unico.restfull.controller", "unico.restfull.jms" })
public class AppConfig {

	/**
	 * Create the connection factory to JMS
	 * @return
	 */
	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		return connectionFactory;
	}

	/**
	 * Create {@link JmsListenerContainerFactory}
	 * @param connectionFactory the {ConnectionFactory}
	 * @return the {@link JmsListenerContainerFactory}
	 */
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		return factory;
	}

	/**
	 * Create a repository to store the message
	 * 
	 * @return {@link StringTransactionRepository}
	 */
	@Bean
	public StringTransactionRepository stringTransactionRepository() {
		return new StringTransactionRepository();
	}

	/**
	 * Serialize message content to json using object
	 * 
	 * @return the {@link MessageConverter}
	 */
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		converter.setObjectMapper(objectMapper);
		return converter;
	}
}
