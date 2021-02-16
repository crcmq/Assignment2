package sait.frms.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SystemWindow extends JFrame {
	final int WINDOW_WIDTH = 800;
	final int WINDOW_HEIGHT = 800;
	JButton flightsButton = new JButton("Flights");
	JButton reservationButton = new JButton("Reservation");
	JPanel tabs;
	
	JPanel flights;
	JPanel reservation;
	JLabel panelTitle;
	JLabel userPanelTitle;
	
	public SystemWindow () {
		setTitle ("Flight Reservation Management System");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// This is actually a panel containing two buttons
		tabs = new JPanel(new GridLayout(1,2));
		tabs.add(flightsButton);
		tabs.add(reservationButton);
		add(tabs, BorderLayout.NORTH);
		
		flightsButton.addActionListener(new ButtonListener());
		
		setVisible(true);	
	}
	
	private class ButtonListener implements ActionListener{
		
		public void actionPerformed (ActionEvent e) {
			
		}
	}
	
	
	/*
	 * This main method is for test only, should be deleted after 
	 */
	public static void main (String[] args) {
		new SystemWindow();
	}
}
