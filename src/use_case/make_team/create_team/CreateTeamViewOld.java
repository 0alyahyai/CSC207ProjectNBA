package use_case.make_team.create_team;

import entity.CommonPlayerFactory;
import use_case.make_team.create_team.addPlayer.AddPlayerController;
import use_case.make_team.create_team.search_player.SearchPlayerController;
import use_case.make_team.save_team.interface_adapter.SaveTeamController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateTeamViewOld extends JPanel implements ActionListener, PropertyChangeListener {

    // Swing-specific instance variables
    private final JTextField teamNameInput;
    private final JTextField playerSearchInput;
    private final JTable playersTable;
    private Object[][] playersModel = new Object[10][2];
    private final JButton searchPlayerButton;
    private final JButton seeStatsButton;
    private final JButton addPlayerButton;
    private final JButton submitTeamButton;
    private final JLabel[] playerLabels;


    // CA instance variables
    private final SearchPlayerController searchPlayerController;
    private final AddPlayerController addPlayerController;
    private final CreateTeamViewModel createTeamViewModel;
    private final SaveTeamController saveTeamController;


    public CreateTeamViewOld(
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
        playersTable = new JTable(new DefaultTableModel(playersModel, new Object[]{"Name", "Sport"}));
        playerLabels = new JLabel[5]; // Assuming 5 players

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
                int selectedRow = playersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Note that getValueAt returns an Object, you might need to cast it to the appropriate type
                    String playerId = (String) playersTable.getValueAt(selectedRow, 0);
                    String playerName = (String) playersTable.getValueAt(selectedRow, 0);
                    System.out.println("Player Data: " + playerId + " " + playerName + ".");


                    addPlayerController.execute(
                            new CommonPlayerFactory().create(playerName, Integer.parseInt(playerId)),
                                    createTeamViewModel.getState().getTeamSoFar()
                    );
                } else {
                    System.out.println("No row is selected.");
                }
            }
        });
        submitTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeamController.execute(
                        null, // TODO: get the user (probably should make the interactor get it automatically)
                        createTeamViewModel.getState().getTeamName(),
                        createTeamViewModel.getState().getTeamSoFar()
                );
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
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("create-team-state")) {
            System.out.println("LOG:: Doing something with property change.....");
            CreateTeamState state = (CreateTeamState) evt.getNewValue();
            playersTable.removeAll();
            Object[][] x = {{1,2}, {1,2}, {1,2}, {1,2}, {1,2}, {1,2}, {1,2}, {1,2}, {1,2}, {1,2}};
            playersTable.setModel(new DefaultTableModel(x, new Object[]{"a", "b"}));
//            for (Player p : state.getMatchingPlayers()) {
//
//            }
        }
    }


}
