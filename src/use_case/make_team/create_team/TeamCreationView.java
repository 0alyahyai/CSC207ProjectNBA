package use_case.make_team.create_team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TeamCreationView {

    private JFrame frame;
    private JTextField teamNameInput;
    private JTextField playerSearchInput;
    private JTable playersTable;
    private JButton searchPlayerButton;
    private JButton seeStatsButton;
    private JButton addPlayerButton;
    private JButton submitTeamButton;
    private JLabel[] playerLabels;

    public TeamCreationView() {
        // Initialize components
        frame = new JFrame("Team Creation");
        teamNameInput = new JTextField(10);
        playerSearchInput = new JTextField(10);
        searchPlayerButton = new JButton("Search");
        seeStatsButton = new JButton("See Stats");
        addPlayerButton = new JButton("Add Player");
        submitTeamButton = new JButton("Submit");
        playerLabels = new JLabel[5]; // Assuming 5 players

        // Set up layout and components
        setupComponents();

        // Display the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private void setupComponents() {
        // Top panel with team name and player search
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Team Name:"));
        topPanel.add(teamNameInput);
        topPanel.add(new JLabel("Player Search:"));
        topPanel.add(playerSearchInput);

        // Center panel with a table for matching players
        String[] columnNames = {"Player Name", "Position", "Team"}; // Example column names
        Object[][] data = {}; // Placeholder data
        playersTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(playersTable);

        // Right panel with search and player actions
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(searchPlayerButton);
        rightPanel.add(seeStatsButton);
        rightPanel.add(addPlayerButton);

        // Bottom panel with selected players and submit button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new JLabel("P" + (i + 1) + "...");
            bottomPanel.add(playerLabels[i]);
        }
        bottomPanel.add(submitTeamButton);

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    // Methods to add action listeners to the buttons
    public void addSearchPlayerButtonListener(ActionListener listener) {
        searchPlayerButton.addActionListener(listener);
    }

    public void addSeeStatsButtonListener(ActionListener listener) {
        seeStatsButton.addActionListener(listener);
    }

    public void addAddPlayerButtonListener(ActionListener listener) {
        addPlayerButton.addActionListener(listener);
    }

    public void addSubmitTeamButtonListener(ActionListener listener) {
        submitTeamButton.addActionListener(listener);
    }

    // Main method to run the UI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TeamCreationView::new);
    }
}
