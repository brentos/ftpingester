package com.example.inc.ftpingester;

import java.io.File;

import javax.annotation.PreDestroy;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedFtpServer {

	private FtpServer ftpServer;
	
	public EmbeddedFtpServer() {
		// This is ugly and only being used as an example
		// This just sets up the embedded FTP Server
		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory listenerFactory = new ListenerFactory();
		listenerFactory.setPort(2221);
		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		userManagerFactory.setFile(new File("users.properties"));
		userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
		serverFactory.setUserManager(userManagerFactory.createUserManager());
		serverFactory.addListener("default", listenerFactory.createListener());
		ftpServer = serverFactory.createServer();
		try {
			ftpServer.start();
		}
		catch(FtpException e) {
			System.out.println(e);
			System.exit(1);
		}
	}
	
	@PreDestroy
	private void stop() {
		if(ftpServer != null && !ftpServer.isStopped()) {
			ftpServer.stop();
		}
	}
}
