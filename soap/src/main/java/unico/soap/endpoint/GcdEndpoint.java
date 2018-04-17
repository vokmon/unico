package unico.soap.endpoint;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import localhost._8080.xml.gcdc.GcdListRequest;
import localhost._8080.xml.gcdc.GcdListResponse;
import localhost._8080.xml.gcdc.GcdRequest;
import localhost._8080.xml.gcdc.GcdResponse;
import localhost._8080.xml.gcdc.GcdSumRequest;
import localhost._8080.xml.gcdc.GcdSumResponse;
import unico.soap.service.GcdService;

/**
 * The service endpoint of Greatest Common Divisor (GCD) 
 * 
 * @author Arnon Ruangthanawes
 *
 */
@Endpoint
public class GcdEndpoint {

	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger(GcdEndpoint.class.getName());
	
	/**
	 * The {@link GcdService}
	 */
	@Autowired
	GcdService gcdService;
	
	/**
	 * The service method for getting greatest common divisor of the two integers 
	 * @param request {@link GcdRequest}
	 * @return greatest common divisor of the two integers
	 */
	@PayloadRoot(namespace = "http://localhost:8080/xml/gcdc", localPart = "GcdRequest")
    @ResponsePayload
    public GcdResponse gcd(@RequestPayload GcdRequest request) {
        logger.info("gcd receives: "+request.getNum1()+"   "+request.getNum2());
 
        GcdResponse response = new GcdResponse();
        response.setGcd(gcdService.gcd(request.getNum1(), request.getNum2()));
        return response;
    }
	
	/**
	 * The service for getting a list of all the computed greatest common divisors.
	 * 
	 * @param request {@link GcdListRequest}
	 * @return list of all the computed greatest common divisors.
	 */
	@PayloadRoot(namespace = "http://localhost:8080/xml/gcdc", localPart = "GcdListRequest")
    @ResponsePayload
    public GcdListResponse gcdList(@RequestPayload GcdListRequest request) {
        logger.info("gcdList receive a request to get all the computed greatest common divisors");
 
        GcdListResponse response = new GcdListResponse();
        List<Integer> allGcd = gcdService.getAllComputedGcd();
        for (Integer i: allGcd) {
        	response.getGcd().add(i);
        }
        return response;
    }
	
	/**
	 * The service for getting sum of all computed greatest common divisors
	 * 
	 * @param request {@link GcdSumRequest}
	 * @return sum of all computed greatest common divisor
	 */
	@PayloadRoot(namespace = "http://localhost:8080/xml/gcdc", localPart = "GcdSumRequest")
    @ResponsePayload
    public GcdSumResponse gcdSum(@RequestPayload GcdSumRequest request) {
        logger.info("gcdSum receive a request to get the sum of all computed greatest common divisors");
        int sum = gcdService.gcdSum();
        GcdSumResponse response = new GcdSumResponse();
        response.setSum(sum);
        return response;
    }
}
