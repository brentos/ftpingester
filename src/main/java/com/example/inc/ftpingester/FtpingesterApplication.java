package com.example.inc.ftpingester;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FtpingesterApplication {

	public static void main(String[] args) throws FtpException {
		SpringApplication.run(FtpingesterApplication.class, args);
		
		FtpServerFactory serverFactory = new FtpServerFactory();
		FtpServer server = serverFactory.createServer();
		server.start();
		
	}
	

}
