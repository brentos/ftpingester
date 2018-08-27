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

@SpringBootApplication
public class FtpingesterApplication {

	public static void main(String[] args) throws FtpException {
		SpringApplication.run(FtpingesterApplication.class, args);
		
		// This is ugly and only being used as an example
		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory listenerFactory = new ListenerFactory();
		listenerFactory.setPort(2221);
		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		userManagerFactory.setFile(new File("users.properties"));
		userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
		serverFactory.setUserManager(userManagerFactory.createUserManager());
		serverFactory.addListener("default", listenerFactory.createListener());
		FtpServer server = serverFactory.createServer();
		server.start();
		
	}
	

}
