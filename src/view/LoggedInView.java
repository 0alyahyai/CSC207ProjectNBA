package view;

import use_case.compareTeam.interface_adapter.CompareController;
import entity.User;
import use_case.make_team.create_team.CreateTeamViewModel;
import use_case.view_team.interface_adapter.ViewTeamController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
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

        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        // This is removed from the specification
//        buttons.add(getPlayerStats);
        buttons.add(viewTeam);
        buttons.add(logOut);

        //Styling:
        int leftMargin = 30;
        int rightMargin = 30;
        int topMargin = 10;
        int bottomMargin = 10;
        Border marginBorder = new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin);
        buttons.setBorder(marginBorder);
        buttons.setBorder(marginBorder);
        title.setBorder(marginBorder);

        Font font = new Font("Arial", Font.BOLD, 16);
        title.setFont(font);

        // Logs out the user when the button is clicked.
        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            viewManagerModel.setActiveView("menu");
                            viewManagerModel.firePropertyChanged();
                            LoggedInState currState = loggedInViewModel.getState();
                            currState.setLoggedIn(false);
                            loggedInViewModel.firePropertyChanged();
                        }
                    }
                }
        );

        leaderboard.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(leaderboard)) {
                            viewManagerModel.setActiveView("leaderboard");
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
//                            viewManagerModel.setActiveView("view team");
//                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        createTeam.addActionListener(
                new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         if (e.getSource().equals(createTeam)) {
                             viewManagerModel.setActiveView(CreateTeamViewModel.VIEW_NAME);
                             viewManagerModel.firePropertyChanged();
                         }

                     }
                }

        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
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
        String userText = "<html>" + String.format("Logged in as: %s", state.getUsername()) + "</html>";
        username.setText(userText);
        username.setHorizontalAlignment(SwingConstants.CENTER);
    }



}