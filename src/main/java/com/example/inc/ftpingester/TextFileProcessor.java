package com.example.inc.ftpingester;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class TextFileProcessor {

	// This method gets called because it has a compatible argument (and because it is the only method in this class)
	public void processFile(InputStream input) throws IOException {
		
	    String text = IOUtils.toString(input, StandardCharsets.UTF_8.name());
	    System.out.println(text);
		
	}

}
