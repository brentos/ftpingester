package com.example.inc.ftpingester;

import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FtpingesterApplication {

	public static void main(String[] args) throws FtpException {
		SpringApplication.run(FtpingesterApplication.class, args);
		
	}
	
	// The name of this bean is used in the "bean:" endpoint
	@Bean
	public TextFileProcessor textFileProcessor() {
		return new TextFileProcessor();
	}
	
	@Bean
	public CsvFileProcessor csvFileProcessor() {
		return new CsvFileProcessor();
	}

}
