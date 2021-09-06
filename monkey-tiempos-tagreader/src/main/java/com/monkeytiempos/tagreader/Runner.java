package com.monkeytiempos.tagreader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.monkeytiempos.tagreader.ui.StartFrame;

/**
 * Launches the application GUI.
 *
 * @author Paul Campbell
 */
@Component
public class Runner implements CommandLineRunner {

    /**
     * Pull in the JFrame to be displayed.
     */
    @Autowired
    private StartFrame frame;

    @Override
    public void run(String... args) throws Exception {
    	
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

}
