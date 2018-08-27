package com.example.inc.ftpingester;

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
			.log(LoggingLevel.INFO, "Processing file ${header.CamelFileName} from host ${header.CamelFileHost}")
			.to("bean:textFileProcessor"); // This is the name of the spring bean to invoke
		
	}

}
