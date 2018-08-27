package com.example.inc.ftpingester;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FtpRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// &localWorkDirectory=target/tmp
		from("ftp://localhost:2221/test?username=admin&password=admin&move=.done&moveFailed=.error&readLock=rename&localWorkDirectory=target/tmp")
			.convertBodyTo(File.class).process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					File file = (File)exchange.getIn().getBody();
					System.out.println(file.getName());
				}
				
			}).to("bean:oprFileProcessor");
		
	}

}
