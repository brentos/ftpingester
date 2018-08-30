package com.example.inc.ftpingester;

import java.util.List;

public class CsvFileProcessor {

	public void processCsv(List<List<String>> data) {
		for(List<String> line : data) {
			System.out.printf("First col: %s second col: %s third col: %s\n", line.get(0), line.get(1), line.get(2));
		}
	}
}
