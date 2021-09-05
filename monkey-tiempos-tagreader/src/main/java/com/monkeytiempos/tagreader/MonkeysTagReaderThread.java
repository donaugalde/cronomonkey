package com.monkeytiempos.tagreader;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impinj.octane.OctaneSdkException;
import com.impinj.octane.Settings;
import com.monkeytiempos.tagreader.impinj.impl.ImpinjReaderMonkeys;
import com.monkeytiempos.tagreader.impinj.impl.TagReportListenerMonkeysImplementation;

@Service
public class MonkeysTagReaderThread extends Thread {

	@Autowired
	private ImpinjReaderMonkeys reader;

	@Autowired
	private TagReportListenerMonkeysImplementation listener;

	private static final long RECONNECTION_TIME = 1000L;

	// TODO - DUP - We need to move this to be taken from a configuration db table
	private static final String HOSTNAME = "speedwayr-11-0F-D0";

	@Override
	public void run() {
		try {
			// We connect for the first time
			attemptToConnect();

			while (true) {
				sleepThread(1000);
				if (!pingForStatus() || !reader.isConnected()) {
					disconnect();
					attemptToConnect();
				}
			}

		} catch (Exception e) {
		}
	}

	private void attemptToConnect() throws Exception {
		boolean connectionSuccessful = false;
		while (!connectionSuccessful) {
			connectionSuccessful = connect();
			if (connectionSuccessful) {
				break;
			} else {
				System.err.println("THE APPLICATION IS NOT READING TAGS AT THIS POINT! VERIFY CONNECTION TO SPEEDWAY!");
				System.err.println("THE APPLICATION IS NOT READING TAGS AT THIS POINT! VERIFY CONNECTION TO SPEEDWAY!");
				System.err.println("THE APPLICATION IS NOT READING TAGS AT THIS POINT! VERIFY CONNECTION TO SPEEDWAY!");
				System.err.println("THE APPLICATION IS NOT READING TAGS AT THIS POINT! VERIFY CONNECTION TO SPEEDWAY!");
				System.err.println("THE APPLICATION IS NOT READING TAGS AT THIS POINT! VERIFY CONNECTION TO SPEEDWAY!");
				sleepThread(RECONNECTION_TIME);
			}
		}
	}

	private boolean connect() {
		boolean connectionSuccessful = false;
		try {
			// Connect
			System.out.println("Connecting to " + HOSTNAME);
			reader.connect(HOSTNAME);
			// Get the default settings
			Settings settings = reader.queryDefaultSettings();
			// Apply the new settings
			reader.applySettings(settings);
			// connect a listener
			reader.setTagReportListener(listener);
			// Start the reader
			reader.start();
			System.out.println("Successfully connected to " + HOSTNAME);
			connectionSuccessful = true;
		} catch (OctaneSdkException e) {
			disconnect();
			System.err.println(e.getMessage());
//            e.printStackTrace();
		} catch (Exception e) {
			disconnect();
			System.err.println(e.getMessage());
//            e.printStackTrace();
		}
		return connectionSuccessful;
	}

	private void disconnect() {
		try {
			System.out.println("Stopping connection to " + HOSTNAME);
			reader.stop();
			System.out.println("Disconnecting from " + HOSTNAME);
			reader.disconnect();
			System.out.println("Successfully disconnected from " + HOSTNAME);
		} catch (OctaneSdkException e) {
			System.err.println(e.getMessage());
//            e.printStackTrace();
		}
	}

	private void sleepThread(long millis) {
		try {
			MonkeysTagReaderThread.sleep(millis);
		} catch (InterruptedException e) {
//			e.printStackTrace();
		}
	}

	private boolean pingForStatus() {
		boolean status = false;
		try {
			InetAddress address = InetAddress.getByName(HOSTNAME);
			status = address.isReachable(1000);
		} catch (Exception e) {
//            e.printStackTrace();
		}
		return status;
	}

}
