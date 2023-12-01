package use_case.make_team.create_team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateTeamView extends JFrame {

    // Components
    private JTextField teamNameInput;
    private JTextField searchPlayersInput;
    private JTable playerTable;
    private JButton seeStatsButton;
    private JButton addPlayerButton;
    private JButton submitButton;
    private JLabel[] playerLabels;

    public CreateTeamView() {
        // Initialize components
        teamNameInput = new JTextField(20);
        searchPlayersInput = new JTextField(20);
        seeStatsButton = new JButton("See Stats");
        addPlayerButton = new JButton("Add Player");
        submitButton = new JButton("Submit");
        playerLabels = new JLabel[5];

        // Set up the table with a scroll pane
        String[] columns = {"Player", "Info"}; // Example columns
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        playerTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(playerTable);

        // Set up the layout
        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Team Name:"));
        topPanel.add(teamNameInput);
        topPanel.add(new JLabel("Search Players:"));
        topPanel.add(searchPlayersInput);

        // Center Panel with the table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel with buttons and player labels
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(seeStatsButton);
        buttonPanel.add(addPlayerButton);

        JPanel playerLabelPanel = new JPanel(new GridLayout(1, 5));
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new JLabel("P" + (i + 1));
            playerLabelPanel.add(playerLabels[i]);
        }

        bottomPanel.add(buttonPanel);
        bottomPanel.add(playerLabelPanel);

        // Submit button at the bottom
        JPanel submitPanel = new JPanel();
        submitPanel.add(submitButton);

        // Add panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(submitPanel, BorderLayout.SOUTH);

        // Prepare the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Methods to add action listeners
    public void addSeeStatsActionListener(ActionListener listener) {
        seeStatsButton.addActionListener(listener);
    }

    public void addAddPlayerActionListener(ActionListener listener) {
        addPlayerButton.addActionListener(listener);
    }

    public void addSubmitActionListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateTeamView());
    }

}
