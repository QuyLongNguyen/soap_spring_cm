package com.longnq.webservices;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.WSConstants;
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
		EndpointImpl endpoint = new EndpointImpl(bus,customerPortType);
		Map<String,Object> properties = new HashMap<>();
		properties.put(ConfigurationConstants.ACTION, ConfigurationConstants.USERNAME_TOKEN);
		properties.put(ConfigurationConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		properties.put(ConfigurationConstants.PW_CALLBACK_CLASS, PasswordCallbackImpl.class.getName());
		
		WSS4JInInterceptor interceptor = new WSS4JInInterceptor(properties);
		endpoint.getInInterceptors().add(interceptor);
		endpoint.publish("/services/customerService");
		
		return endpoint;
	}
}
