package com.example.inc.ftpingester;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FtpRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// &localWorkDirectory=target/tmp
		from("ftp://localhost:2221/test?username=admin&password=admin&move=.done&moveFailed=.error&readLock=rename&localWorkDirectory=target/tmp")
			.log(LoggingLevel.INFO, "Processing file ${file:name}")
			.to("bean:oprFileProcessor");
		
	}

}
