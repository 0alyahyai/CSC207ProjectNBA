package use_case.make_team.create_team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NBAView {

    // Main window frame
    private JFrame frame;
    private JTextField teamNameInput;
    private JTextField searchPlayersInput;
    private JTable playersTable;
    private JButton seeStatsButton;
    private JButton addPlayerButton;
    private JButton submitButton;
    private JLabel[] playerLabels;

    public NBAView() {
        // Initialize components
        frame = new JFrame("NBA Players View");
        teamNameInput = new JTextField(10);
        searchPlayersInput = new JTextField(10);
        seeStatsButton = new JButton("See Stats");
        addPlayerButton = new JButton("Add Player");
        submitButton = new JButton("Submit");
        playerLabels = new JLabel[5];

        // Set up layout and components
        setupComponents();

        // Display the window
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setupComponents() {
        // Top panel with text inputs
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Team Name:"));
        topPanel.add(teamNameInput);
        topPanel.add(new JLabel("Search Players:"));
        topPanel.add(searchPlayersInput);

        // Center panel with a scrollable table
        String[] columnNames = {"Player", "Position", "Team"}; // Example columns
        Object[][] data = {}; // Placeholder for data
        playersTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(playersTable);

        // Right panel with player labels and the submit button
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new JLabel("P" + (i + 1));
            rightPanel.add(playerLabels[i]);
        }
        rightPanel.add(submitButton);

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(seeStatsButton);
        bottomPanel.add(addPlayerButton);

        // Main frame layout
        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    // Methods to add action listeners
    public void addSeeStatsButtonListener(ActionListener listener) {
        seeStatsButton.addActionListener(listener);
    }

    public void addAddPlayerButtonListener(ActionListener listener) {
        addPlayerButton.addActionListener(listener);
    }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    // Main method to run the UI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(NBAView::new);
    }
}
