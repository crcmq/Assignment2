package sait.frms.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frms.exception.NullCitizenshipException;
import sait.frms.exception.NullClientNameException;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {

	private ReservationManager reservationManager;
	private JList<Reservation> reservationsList;
	private DefaultListModel<Reservation> reservationModel;
	private JTextField code_text;
	private JTextField airline_text;
	private JTextField name_text;
	private ArrayList<Reservation> foundReservations;
	private JScrollPane scrollPane;
	private JTextField codeText;
	private JTextField flightText;
	private JTextField airlineText;
	private JTextField costText;
	private JTextField nameText;
	private JTextField citizenshipText;
	private JComboBox<String> statusBox;
	private Reservation selectedReservation;  // selected reservation from scroll pane
	
	/**
	 * Creates the components for reservations tab.
	 * @param reservationManager ReservationManager object
	 */
	public ReservationsTab(ReservationManager reservationManager) {
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
	private JPanel createNorthPanel() {
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
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());	
		
		reservationModel = new DefaultListModel<>();
		
		reservationsList = new JList<>(reservationModel);
		
		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		scrollPane = new JScrollPane(this.reservationsList);
		
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		
		return panel;
	}

	/**
	 * Creates the east panel
	 * @return JPanel that goes in east
	 */
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 20));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(7,2));
		
		JLabel code_textPanelLabel = new JLabel("Code: ");
		codeText = new JTextField(10);
		codeText.setEditable(false);
		
		JLabel flight_textPanelLabel = new JLabel("Flight: ");
		flightText = new JTextField(10);
		flightText.setEditable(false);
		
		JLabel airline_textPanelLabel = new JLabel("Airline: ");
		airlineText = new JTextField(10);
		airlineText.setEditable(false);
		
		JLabel cost_textPanelLabel = new JLabel("Cost: ");
		costText = new JTextField(10);
		costText.setEditable(false);
		
		JLabel name_textPanelLabel = new JLabel("Name: ");
		nameText = new JTextField(10);
		
		JLabel citizenship_textPanelLabel = new JLabel("Citizenship: ");
		citizenshipText = new JTextField(10);
		
		String[] activeStatus = {"Active", "Inactive"};
		JLabel status_textPanelLabel = new JLabel("Status: ");
		statusBox = new JComboBox<String>(activeStatus);
		
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
		update.addActionListener(new updateListener());
		panel.add(update, BorderLayout.SOUTH);
		
		return panel;
	}

	/**
	 * Creates the south panel
	 * @return JPanel that goes in south
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Search", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 20));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel labelPanel = new JPanel(new GridLayout(3,1));
		JPanel textPanel = new JPanel(new GridLayout(3,1));		
		
		JLabel code_textLabel = new JLabel("Code: ");
		code_text = new JTextField();		
		JLabel airline_textLabel = new JLabel("Airline: ");
		airline_text = new JTextField();
		JLabel name_textLabel = new JLabel("Name: ");
		name_text = new JTextField();
		
		labelPanel.add(code_textLabel);
		textPanel.add(code_text);
		labelPanel.add(airline_textLabel);
		textPanel.add(airline_text);
		labelPanel.add(name_textLabel);
		textPanel.add(name_text);
		
		panel.add(labelPanel, BorderLayout.WEST);
		panel.add(textPanel, BorderLayout.CENTER);
		
		JButton findFlight = new JButton ("Find Flights");
		findFlight.addActionListener(new findReservationsListener());
		panel.add(findFlight, BorderLayout.SOUTH);
		return panel;
	}
	
	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// get index of selected reservation
			int idx = reservationsList.getSelectedIndex();
			if (idx != -1) {
				selectedReservation = foundReservations.get(idx);
				// read info
				String reservationCode = selectedReservation.getCode();
				String flightCode = selectedReservation.getFlightCode();
				String airline = selectedReservation.getAirline();
				double cost = selectedReservation.getCost();
				String clientName = selectedReservation.getName();
				String citizenship = selectedReservation.getCitizenship();
				boolean isActive = selectedReservation.isActive();
				// show info in text fields
				codeText.setText(reservationCode);
				flightText.setText(flightCode);
				airlineText.setText(airline);
				costText.setText(String.format("%.2f",cost));
				nameText.setText(clientName);
				citizenshipText.setText(citizenship);
				
				if (isActive) {
					statusBox.setSelectedIndex(0);
				}
				else {
					statusBox.setSelectedIndex(1);
				}
			}
		}	
	}

	/**
	 * 
	 * Listener for find reservations
	 *
	 */
	private class findReservationsListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			// clear previously found reservations
			foundReservations = new ArrayList<>();
			reservationModel.removeAllElements();
			
			// read info from user input
			String find_ReservationCode = code_text.getText();
			String find_Airline = airline_text.getText();
			String find_ClientName = name_text.getText();
			
			// search and find reservations
			foundReservations = reservationManager.findReservation(find_ReservationCode, find_Airline, find_ClientName);
			
			// display found reservation 
			for (Reservation r: foundReservations) {
				if (r.isActive()) {
					reservationModel.addElement(r);
				}
			}
			if (reservationModel.size() == 0) {
				JOptionPane.showMessageDialog(null, "Sorry, there's no matching reservation");
			}
		}
	}
	
	private class updateListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			
			// read info from user input
			String clientName = nameText.getText();
			String citizenShip = citizenshipText.getText();
			String code = codeText.getText();
			int status = statusBox.getSelectedIndex();
			
			boolean isActive = false;
			isActive = status == 0;
				
			// update reservation according to user input
			try {
				reservationManager.updateReservation(selectedReservation, clientName, citizenShip, isActive);
				reservationModel.clear();
				// updated reservation should be displayed 
				foundReservations = reservationManager.findReservation(code, "", "");
				for(Reservation r : foundReservations) {
					if (r.isActive()) {
						reservationModel.addElement(r);
					}		
				}		
				JOptionPane.showMessageDialog(null, code + " Has been updated");
			}
			catch (NullClientNameException nce) {
				JOptionPane.showMessageDialog(null, "Client name cannot be empty");
			}
			catch (NullCitizenshipException cite) {
				JOptionPane.showMessageDialog(null, "Citizenship cannot be empty");
			}
		}
	}
}
