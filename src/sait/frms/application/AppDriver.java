package sait.frms.application;


import java.io.FileNotFoundException;
import java.io.IOException;

import sait.frms.gui.*;

/**
 * Application driver.
 * This is a Flight reservation management system. 
 * Users can search flights and make reservations under Flight tab
 * They also can update their reservations under Reservation tab
 * When a reservation is set to inactive, it means the reservation is deleted. But the record still exists in the binary file.
 * 
 * @author Mengqiu (Roger) Chen, Ebele Egenti, AJ Russell De Leon, Dmitriy Fominykh
 * @version 2021-3-22
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
