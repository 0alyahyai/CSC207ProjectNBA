package use_case.make_team.create_team;

import entity.CommonPlayerFactory;
import entity.Player;
import use_case.make_team.create_team.addPlayer.AddPlayerController;
import use_case.make_team.create_team.search_player.SearchPlayerController;
import use_case.make_team.save_team.interface_adapter.SaveTeamController;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateTeamView extends JPanel implements ActionListener, PropertyChangeListener {
    private final JTextField teamNameInput;
    private final JTextField playerSearchInput;
    private final JComboBox<Player> playerDropdown;
    private final JButton searchPlayerButton;
    private final JButton seeStatsButton;
    private final JButton addPlayerButton;
    private final JButton submitTeamButton;
    private final JLabel[] playerLabels;
    private final JButton backButton;


    // CA instance variables
    private final SearchPlayerController searchPlayerController;
    private final AddPlayerController addPlayerController;
    private final CreateTeamViewModel createTeamViewModel;
    private final SaveTeamController saveTeamController;


    public CreateTeamView(
            SearchPlayerController searchPlayerController,
            AddPlayerController addPlayerController,
            CreateTeamViewModel createTeamViewModel,
            SaveTeamController saveTeamController) {

        // Assign CA dependencies
        this.searchPlayerController = searchPlayerController;
        this.addPlayerController = addPlayerController;
        this.createTeamViewModel = createTeamViewModel;
        this.saveTeamController = saveTeamController;

        // Adding 'this' as an observer of createTeamViewModel
        createTeamViewModel.addPropertyChangeListener(this);

        // Initialize components
        teamNameInput = new JTextField(10);
        playerSearchInput = new JTextField(10);
        searchPlayerButton = new JButton(CreateTeamViewModel.SEARCH_BUTTON_LABEL);
        seeStatsButton = new JButton(CreateTeamViewModel.STATS_BUTTON_LABEL);
        addPlayerButton = new JButton(CreateTeamViewModel.ADD_PLAYER_BUTTON_LABEL);
        submitTeamButton = new JButton(CreateTeamViewModel.SUBMIT_BUTTON_LABEL);
        playerDropdown = new JComboBox<>();
        playerLabels = new JLabel[5]; // Assuming 5 players
        backButton = new JButton("Back");

        // Set up layout and components
        setupComponents();
    }

    private void setupComponents() {
        // Adding ActionListener's
        searchPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPlayerController.execute(playerSearchInput.getText());
            }
        });
        seeStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: SAM!
            }
        });
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player playerToAdd = (Player) playerDropdown.getSelectedItem();
                addPlayerController.execute(playerToAdd, createTeamViewModel.getState().getTeamSoFar());
            }
        });
        submitTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createTeamViewModel.getState().getTeamSoFar().size() != 5) {
                    JOptionPane.showMessageDialog(null, "MUST HAVE FIVE PLAYERS!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    saveTeamController.execute(
                            null, // TODO: get the user (probably should make the interactor get it automatically)
                            teamNameInput.getText(),
                            createTeamViewModel.getState().getTeamSoFar()
                    );
                }


            }
        });

        // Top panel with team name and player search
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel(CreateTeamViewModel.TEAM_NAME_LABEL));
        topPanel.add(teamNameInput);
        topPanel.add(new JLabel(CreateTeamViewModel.PLAYER_SEARCH_LABEL));
        topPanel.add(playerSearchInput);

        // Center panel with a table for matching players
        String[] columnNames = {"Player Name", "Position", "Team"}; // Example column names
        Object[][] data = {}; // Placeholder data
//        JScrollPane scrollPane = new JScrollPane(playersTable);

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
        this.add(topPanel, BorderLayout.NORTH);
        this.add(playerDropdown, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("updated-team")) {
            List<Player> updatedTeam = (List<Player>) evt.getNewValue();
            for (int i = 0; i < updatedTeam.size(); i++) {
                playerLabels[i].setText("P1: " + updatedTeam.get(i));
            }

        }
        else if (evt.getPropertyName().equals("player-matches")) {
            playerDropdown.removeAllItems();
            List<Player> playerMatches = (List<Player>) evt.getNewValue();
            for (Player p : playerMatches) {
                playerDropdown.addItem(p);
            }
        }
    }
}

