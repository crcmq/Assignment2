package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.exception.NullCitizenshipException;
import sait.frms.exception.NullClientNameException;
import sait.frms.manager.*;
import sait.frms.problemdomain.*;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase 
{

	private FlightManager flightManager;
	private ReservationManager reservationManager;
	private JList<Flight> flightsList;
	private DefaultListModel<Flight> flightsModel;
	private JTextField flightText;
	private JTextField airLineText;
	private JTextField dayText;
	private JTextField timeText;
	private JTextField costText;
	private JTextField nameText;
	private JTextField citizenshipText;
	private JComboBox fromBox;
	private JComboBox toBox;
	private JComboBox dayBox;
	private JScrollPane scrollPane;
	public ArrayList<Flight> foundFlights;
	
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) 
	{
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;
		
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the east panel.
	 * @return JPanel that goes in east
	 */
	private JPanel createEastPanel() 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 20));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(7,2));
		JLabel flight_textPanelLabel = new JLabel("Flight: ");
		flightText = new JTextField(10);
		flightText.setEditable(false);
		
		JLabel airLine_textPanelLabel = new JLabel("Airline: ");
		airLineText = new JTextField(10);
		airLineText.setEditable(false);
		
		JLabel day_textPanelLabel = new JLabel("Day: ");
		dayText = new JTextField(10);
		dayText.setEditable(false);
		
		JLabel time_textPanelLabel = new JLabel("Time: ");
		timeText = new JTextField(10);
		timeText.setEditable(false);
		
		JLabel cost_textPanelLabel = new JLabel("Cost: ");
		costText = new JTextField(10);
		costText.setEditable(false);
		
		JLabel name_textPanelLabel = new JLabel("Name: ");
		nameText = new JTextField(10);
		
		JLabel citizenship_textPanelLabel = new JLabel("Citizenship: ");
		citizenshipText = new JTextField(10);
		
		textPanel.add(flight_textPanelLabel);
		textPanel.add(flightText);
		textPanel.add(airLine_textPanelLabel);
		textPanel.add(airLineText);
		textPanel.add(day_textPanelLabel);
		textPanel.add(dayText);
		textPanel.add(time_textPanelLabel);
		textPanel.add(timeText);
		textPanel.add(cost_textPanelLabel);
		textPanel.add(costText);
		textPanel.add(name_textPanelLabel);
		textPanel.add(nameText);
		textPanel.add(citizenship_textPanelLabel);
		textPanel.add(citizenshipText);
		
		panel.add(textPanel, BorderLayout.CENTER);
		
		JButton reserve = new JButton ("Reserve");
		reserve.addActionListener(new reserveListener());
		panel.add(reserve, BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Creates the south panel
	 * @return JPanel that goes in south
	 */
	private JPanel createSouthPanel() 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 20));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel labelPanel = new JPanel(new GridLayout(3,1));
		JPanel textPanel = new JPanel(new GridLayout(3,1));			
		
		String[] dayList = {FlightManager.WEEKDAY_ANY, FlightManager.WEEKDAY_MONDAY, FlightManager.WEEKDAY_TUESDAY, FlightManager.WEEKDAY_WEDNESDAY,
							FlightManager.WEEKDAY_THURSDAY, FlightManager.WEEKDAY_FRIDAY, FlightManager.WEEKDAY_SATURDAY, FlightManager.WEEKDAY_SUNDAY};
		
		
		JLabel from_textLabel = new JLabel("From: ");
		fromBox = new JComboBox(flightManager.getAirports().toArray());
		JLabel to_textLabel = new JLabel("To: ");
		toBox = new JComboBox(flightManager.getAirports().toArray());
		JLabel day_textLabel = new JLabel("Day: ");
		dayBox = new JComboBox(dayList);
		
		labelPanel.add(from_textLabel);
		textPanel.add(fromBox);
		labelPanel.add(to_textLabel);
		textPanel.add(toBox);
		labelPanel.add(day_textLabel);
		textPanel.add(dayBox);
		
		panel.add(labelPanel, BorderLayout.WEST);
		panel.add(textPanel, BorderLayout.CENTER);
		
		JButton findFlight = new JButton ("Find Flights");
		findFlight.addActionListener(new findFlightListener());
		panel.add(findFlight, BorderLayout.SOUTH);
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		flightsModel = new DefaultListModel<Flight>();
		flightsList = new JList<>(flightsModel);
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		scrollPane = new JScrollPane(flightsList);
		
		flightsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		
		return panel;
	}
	
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) 
		{
			int idx = e.getLastIndex();
			Flight f = foundFlights.get(idx);
			String flightCode = f.getCode();
			String airline = f.getAirline();
			String day = f.getWeekday();
			String time = f.getTime();
			double cost = f.getCostPerSeat();
			
			flightText.setText(flightCode);
			airLineText.setText(airline);
			dayText.setText(day);
			timeText.setText(time);
			costText.setText(String.format("%.2f", cost));
		}	
	}
	
	private class findFlightListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
						
			String from = (String) fromBox.getSelectedItem();
			String to = (String) toBox.getSelectedItem();
			String day = (String) dayBox.getSelectedItem();
			
			System.out.println(from);
			System.out.println(to);
			System.out.println(day);
			//JOptionPane.showMessageDialog(null, "test");
			
			foundFlights = flightManager.findFlights(from, to, day);

			// search flight according to the info
			for (Flight f : foundFlights) {
				System.out.println(f);
				flightsModel.addElement(f);
			}
			
		}
	}
	
	private class reserveListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			String flightCode = flightText.getText();
			String clientName = nameText.getText();
			String clientCitizenShip = citizenshipText.getText();
			
			Flight foundFlight = flightManager.findFlightByCode(flightCode);
			try {
				Reservation r = reservationManager.makeReservation(foundFlight, clientName, clientCitizenShip);
				String reservationCode = r.getCode();
				
				JOptionPane.showMessageDialog(null, "Your reservation code is: " + reservationCode);
			}
			catch (NullClientNameException ce) {
				JOptionPane.showMessageDialog(null, "Client name cannot be empty");
			}
			catch (NullCitizenshipException cite) {
				JOptionPane.showMessageDialog(null, "Client citizenship cannot be empty");
			}

		}
	}
}