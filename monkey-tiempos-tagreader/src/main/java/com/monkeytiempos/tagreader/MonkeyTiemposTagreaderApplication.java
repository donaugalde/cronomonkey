package com.monkeytiempos.tagreader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.monkeytiempos.tagreader.ui.StartFrame;

@SpringBootApplication
public class MonkeyTiemposTagreaderApplication implements CommandLineRunner {
	
	@Autowired
	private MonkeysTagReaderThread monkeysTagReaderThread;

	public static void main(String[] args) {
		new SpringApplicationBuilder(MonkeyTiemposTagreaderApplication.class)
        	.headless(false)
        	.web(WebApplicationType.NONE)
        	.run(args);
	}
	
	@Override
	public void run(String... args) {
		monkeysTagReaderThread.start();
	}

    /**
     * Creates the {@link DemoFrame} object and returns it.
     *
     * This @Bean could have been replaced by a @Component annotation being
     * added to the {@link DemoFrame} class.
     *
     * @return the application window
     */
    @Bean
    public StartFrame frame() {
        return new StartFrame();
    }

}
