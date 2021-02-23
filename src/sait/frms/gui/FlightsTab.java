package sait.frms.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase 
{
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;
	
	private DefaultListModel<Flight> flightsModel;
	
	/**
	 * Creates the components for flights tab.
	 */
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
		JTextField flightText = new JTextField(10);
		flightText.setEditable(false);
		
		JLabel airLine_textPanelLabel = new JLabel("Airline: ");
		JTextField airLineText = new JTextField(10);
		airLineText.setEditable(false);
		
		JLabel day_textPanelLabel = new JLabel("Day: ");
		JTextField dayText = new JTextField(10);
		dayText.setEditable(false);
		
		JLabel time_textPanelLabel = new JLabel("Time: ");
		JTextField timeText = new JTextField(10);
		timeText.setEditable(false);
		
		JLabel cost_textPanelLabel = new JLabel("Cost: ");
		JTextField costText = new JTextField(10);
		costText.setEditable(false);
		
		JLabel name_textPanelLabel = new JLabel("Name: ");
		JTextField nameText = new JTextField(10);
		
		JLabel citizenship_textPanelLabel = new JLabel("Citizenship: ");
		JTextField citizenshipText = new JTextField(10);
		
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
		
		JLabel from_textLabel = new JLabel("From: ");
		JComboBox fromBox = new JComboBox();
		JLabel to_textLabel = new JLabel("To: ");
		JComboBox toBox = new JComboBox();
		JLabel day_textLabel = new JLabel("Day: ");
		JComboBox dayBox = new JComboBox();
		
		labelPanel.add(from_textLabel);
		textPanel.add(fromBox);
		labelPanel.add(to_textLabel);
		textPanel.add(toBox);
		labelPanel.add(day_textLabel);
		textPanel.add(dayBox);
		
		panel.add(labelPanel, BorderLayout.WEST);
		panel.add(textPanel, BorderLayout.CENTER);
		
		JButton findFlight = new JButton ("Find Flights");
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
		
		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);
		
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
			
		}
		
	}
}