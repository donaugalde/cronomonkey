package com.monkeytiempos.tagreader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonkeyTiemposTagreaderApplication implements CommandLineRunner {
	
	@Autowired
	private MonkeysTagReaderThread monkeysTagReaderThread;

	public static void main(String[] args) {
		SpringApplication.run(MonkeyTiemposTagreaderApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		monkeysTagReaderThread.start();
	}

}
