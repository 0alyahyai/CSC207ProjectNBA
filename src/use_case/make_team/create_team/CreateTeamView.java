package use_case.make_team.create_team;

import entity.Player;
import use_case.make_team.create_team.addPlayer.AddPlayerController;
import use_case.make_team.create_team.search_player.SearchPlayerController;
import use_case.make_team.save_team.interface_adapter.SaveTeamController;
import view.LoggedInViewModel;
import view.ViewManagerModel;

import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
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

    private final ViewManagerModel viewManagerModel;


    public CreateTeamView(
            SearchPlayerController searchPlayerController,
            AddPlayerController addPlayerController,
            CreateTeamViewModel createTeamViewModel,
            SaveTeamController saveTeamController, ViewManagerModel viewManagerModel) {

        // Assign CA dependencies
        this.searchPlayerController = searchPlayerController;
        this.addPlayerController = addPlayerController;
        this.createTeamViewModel = createTeamViewModel;
        this.saveTeamController = saveTeamController;
        this.viewManagerModel = viewManagerModel;

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
                if (playerDropdown.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "MUST SELECT A PLAYER!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else { // Sam

                }
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
                    createTeamViewModel.clearState();
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
        rightPanel.add(Box.createVerticalStrut(5)); // Adds a 5-pixel vertical space
        rightPanel.add(seeStatsButton);
        rightPanel.add(Box.createVerticalStrut(5)); // Adds a 5-pixel vertical space
        rightPanel.add(addPlayerButton);

        // Bottom panel with selected players and submit button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new JLabel("P" + (i + 1) + "...");
            bottomPanel.add(playerLabels[i]);
        }
        bottomPanel.add(submitTeamButton);

        backButton.addActionListener(
                new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     viewManagerModel.setActiveView(LoggedInViewModel.TITLE_LABEL);
                     viewManagerModel.firePropertyChanged();
                     clearComponents();
                 }
             }

        );
        bottomPanel.add(backButton);


        topPanel.setLayout(new GridLayout(2, 2, 5, 5)); // Grid layout for even distribution
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment with spacing

        customizeComponents();





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
        else if (evt.getPropertyName().equals("state-clearance")) {
            clearComponents();
        }
    }


    private void clearComponents() {
        teamNameInput.setText(createTeamViewModel.getState().getTeamName());
        playerSearchInput.setText(createTeamViewModel.getState().getTeamName());
        playerDropdown.removeAllItems();
        for (int i = 0; i < 5; i++) {
            playerLabels[i].setText("P" + (i+1) + ":");
        }
    }


    private void customizeComponents() {
        // Common Font
        Font commonFont = new Font("Helvetica", Font.BOLD, 14);

        // Buttons Style
        Color buttonBackgroundColor = new Color(200, 200, 200); // Light Gray
        Color buttonHoverColor = new Color(180, 180, 180); // Slightly darker for hover
        Border buttonBorder = BorderFactory.createLineBorder(new Color(150, 150, 150), 1); // Darker Gray Border

        customizeButton(searchPlayerButton, buttonBackgroundColor, buttonHoverColor, buttonBorder, commonFont);
        customizeButton(seeStatsButton, buttonBackgroundColor, buttonHoverColor, buttonBorder, commonFont);
        customizeButton(addPlayerButton, buttonBackgroundColor, buttonHoverColor, buttonBorder, commonFont);
        customizeButton(submitTeamButton, buttonBackgroundColor, buttonHoverColor, buttonBorder, commonFont);
        customizeButton(backButton, buttonBackgroundColor, buttonHoverColor, buttonBorder, commonFont);

        Color textFieldBackgroundColor = new Color(240, 240, 240); // Light Gray
        Color dropdownBackgroundColor = Color.WHITE;


        // Customize Text Fields
        customizeTextField(teamNameInput, textFieldBackgroundColor, commonFont);
        customizeTextField(playerSearchInput, textFieldBackgroundColor, commonFont);

        // Customize Dropdown
        customizeDropdown(playerDropdown, dropdownBackgroundColor, commonFont);
    }

    private void customizeButton(JButton button, Color backgroundColor, Color hoverColor, Border border, Font font) {
        button.setUI(new BasicButtonUI()); // Override default UI
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK); // Black text for contrast
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBorder(border);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
    }

    private void customizeTextField(JTextField textField, Color backgroundColor, Font font) {
        textField.setBackground(backgroundColor);
        textField.setFont(font);
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding
    }

    private void customizeDropdown(JComboBox<?> dropdown, Color backgroundColor, Font font) {
        dropdown.setUI(new BasicComboBoxUI()); // Override default UI
        dropdown.setBackground(backgroundColor);
        dropdown.setFont(font);
    }
}

