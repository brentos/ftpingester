package com.example.inc.ftpingester;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FtpRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {

		from("ftp://{{ftp.host}}:{{ftp.port}}/test?username={{ftp.username}}"
				+ "&password={{ftp.password}}&move=.done/${file:onlyname}.${date:now:yyyyMMddHH24mmssSSS}&moveFailed=.error"
				+ "&readLock=rename&localWorkDirectory={{ftp.workingDir}}")
			.routeId("ftpRoute")
			.log(LoggingLevel.INFO, "Processing file ${header.CamelFileName} from host ${header.CamelFileHost}")
			.choice()
				.when(header(Exchange.FILE_NAME).regex(".*\\.[cC][sS][vV]$"))
					.unmarshal().csv()
					.to("bean:csvFileProcessor")
				.otherwise()
					.to("bean:textFileProcessor"); // This is the name of the spring bean to invoke
		
	}

}
