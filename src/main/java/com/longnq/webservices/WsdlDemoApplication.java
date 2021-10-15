package com.longnq.webservices;


import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import com.longnq.customers.CustomerPortType;


@SpringBootApplication(scanBasePackages = "com.longnq")
@EntityScan(basePackages ="com.longnq.customers" )
public class WsdlDemoApplication {

	@Autowired
	Bus bus;
	
	@Autowired
	CustomerPortType customerPortType;
	
	public static void main(String[] args) {
		SpringApplication.run(WsdlDemoApplication.class, args);
		
	}
	
	@Bean
	public Endpoint endpoint() {
		Endpoint endpoint = new EndpointImpl(bus,customerPortType);
		endpoint.publish("/services/customerService");
		
		return endpoint;
	}
}
