package sait.frms.gui;

import java.awt.*;
import javax.swing.*;

//import sait.frms.gui.FlightsTab.MyListSelectionListener;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase 
{
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	private JList<Reservation> reservationsList;
	
	private DefaultListModel<Reservation> reservationModel;
	
	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) 
	{
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
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
			
		reservationModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationModel);
		
		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);		
		
		panel.add(scrollPane);
		
		return panel;
	}

	/**
	 * Creates the east panel
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
		JLabel code_textPanelLabel = new JLabel("Code: ");
		JTextField codeText = new JTextField(10);
		codeText.setEditable(false);
		
		JLabel flight_textPanelLabel = new JLabel("Flight: ");
		JTextField flightText = new JTextField(10);
		flightText.setEditable(false);
		
		JLabel airline_textPanelLabel = new JLabel("Airline: ");
		JTextField airlineText = new JTextField(10);
		airlineText.setEditable(false);
		
		JLabel cost_textPanelLabel = new JLabel("Cost: ");
		JTextField costText = new JTextField(10);
		costText.setEditable(false);
		
		JLabel name_textPanelLabel = new JLabel("Name: ");
		JTextField nameText = new JTextField(10);
		
		JLabel citizenship_textPanelLabel = new JLabel("Citizenship: ");
		JTextField citizenshipText = new JTextField(10);
		
		JLabel status_textPanelLabel = new JLabel("Status: ");
		JComboBox statusBox = new JComboBox();
		
		textPanel.add(code_textPanelLabel);
		textPanel.add(codeText);
		textPanel.add(flight_textPanelLabel);
		textPanel.add(flightText);
		textPanel.add(airline_textPanelLabel);
		textPanel.add(airlineText);
		textPanel.add(cost_textPanelLabel);
		textPanel.add(costText);
		textPanel.add(name_textPanelLabel);
		textPanel.add(nameText);
		textPanel.add(citizenship_textPanelLabel);
		textPanel.add(citizenshipText);
		textPanel.add(status_textPanelLabel);
		textPanel.add(statusBox);
		
		panel.add(textPanel, BorderLayout.CENTER);
		
		JButton update = new JButton ("Update");
		panel.add(update, BorderLayout.SOUTH);
		
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
		
		JLabel title = new JLabel("Search", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 20));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel labelPanel = new JPanel(new GridLayout(3,1));
		JPanel textPanel = new JPanel(new GridLayout(3,1));		
		
		JLabel code_textLabel = new JLabel("Code: ");
		JTextField code_text = new JTextField();		
		JLabel airline_textLabel = new JLabel("Airline: ");
		JTextField airline_text = new JTextField();
		JLabel name_textLabel = new JLabel("Name: ");
		JTextField name_text = new JTextField();
		
		labelPanel.add(code_textLabel);
		textPanel.add(code_text);
		labelPanel.add(airline_textLabel);
		textPanel.add(airline_text);
		labelPanel.add(name_textLabel);
		textPanel.add(name_text);
		
		panel.add(labelPanel, BorderLayout.WEST);
		panel.add(textPanel, BorderLayout.CENTER);
		
		JButton findFlight = new JButton ("Find Flights");
		panel.add(findFlight, BorderLayout.SOUTH);
		return panel;
	}


}
