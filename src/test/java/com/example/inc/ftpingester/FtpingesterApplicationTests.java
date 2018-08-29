package com.example.inc.ftpingester;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpingesterApplicationTests {

	@Autowired
	private CamelContext camelContext;
	
	@EndpointInject(uri="direct:mock-ftp")
	private ProducerTemplate producer;
	
	@EndpointInject(uri="mock:end")
	private MockEndpoint mockEnd;
	
	
	@Before
	public void setup()  throws Exception {
		camelContext.getRouteDefinition("ftpRoute").autoStartup(true)
			.adviceWith(camelContext, new AdviceWithRouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					replaceFromWith("direct:mock-ftp");
					interceptSendToEndpoint("bean:*").to("mock:end");
					
				}
			});
	}
	
	@Test
	public void testFtpRoute() throws Exception {
		
		mockEnd.expectedMessageCount(1);
		File file = new File("src/test/resources/hello.txt");
		producer.sendBody(file);
		mockEnd.assertIsSatisfied();
	}

}
