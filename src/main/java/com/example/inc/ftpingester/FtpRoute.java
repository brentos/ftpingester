package com.example.inc.ftpingester;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FtpRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("ftp://");
		
	}

}
