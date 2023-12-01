package view;

import use_case.compareTeam.interface_adapter.CompareController;
import use_case.view_team.interface_adapter.ViewTeamController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// This is a general view connecting all other main views via simple buttons.
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    private final ViewTeamController viewTeamController;

    private final CompareController compareController;

    JLabel username;

    final JButton logOut;

    final JButton leaderboard;
    final JButton createTeam;
    final JButton compareTeams;
    final JButton getPlayerStats;

    final JButton viewTeam;
    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
                        ViewTeamController viewTeamController, CompareController compareController) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.viewTeamController = viewTeamController;
        this.compareController = compareController;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel(String.format("Logged in as: %s", loggedInViewModel.getState().getUsername()));
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        leaderboard = new JButton("Leaderboard");
        createTeam = new JButton("Create Team");
        compareTeams = new JButton("Compare Teams");
        getPlayerStats = new JButton("Get Player Stats");
        viewTeam = new JButton("View Team");
        buttons.add(leaderboard);
        buttons.add(createTeam);
        buttons.add(compareTeams);
        buttons.add(getPlayerStats);
        buttons.add(viewTeam);
        buttons.add(logOut);
        // Logs out the user when the button is clicked.
        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            viewManagerModel.setActiveView("menu");
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );

        //Here VARP will code the button for Compare Teams (The add Action Listener). VARP is below
        compareTeams.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(compareTeams)) {
                            compareController.execute();
                        }

                    }
                }

        );
        //VARP is above

        viewTeam.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(viewTeam)) {
                            viewTeamController.execute();
                            viewManagerModel.setActiveView("view team");
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);


    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
    }



}