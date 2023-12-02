package use_case.leaderboard.view;

import use_case.leaderboard.interface_adapter.LeaderboardController;
import use_case.leaderboard.interface_adapter.LeaderboardState;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LeaderboardView extends JPanel implements ActionListener, PropertyChangeListener {

    private final LeaderboardViewModel leaderboardViewModel;
    private final LeaderboardController leaderboardController;
    public final String viewName = "leaderboard";
    private final JLabel title = new JLabel("Leaderboard");
    private final JPanel leaderboard;
    private final JButton back;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardController = leaderboardController;
        leaderboardViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        leaderboard = new JPanel(new GridLayout(0, 3, 10, 10));
        if (leaderboardViewModel.getState().getLeaderboardUsers() != null) {
            for (String user : leaderboardViewModel.getState().getLeaderboardUsers()) {
                JLabel userLabel = new JLabel(user);
                Font font = new Font("Arial", Font.PLAIN, 16);
                userLabel.setFont(font);
                leaderboard.add(userLabel);
            }
        } else {
            JLabel userLabel = new JLabel("No users yet!");
            Font font = new Font("Arial", Font.PLAIN, 16);
            userLabel.setFont(font);
            leaderboard.add(userLabel);
        }

        back = new JButton(LeaderboardViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        //Current methodology is to create an actionlistener for each button, and pass in the name of the button
        //to the menuController.execute function. Could we do this more efficiently, by using some property of the button?
        //Or is this the best way to do it?

//        Leaderboard.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(signUp)) {
//                            menuController.execute("leaderboard");
//                        }
//                    }
//                }
//        );

        back.addActionListener(
                // We might need to edit this method to take into account if user is loggen in or not
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            if (leaderboardViewModel.getState().isLoggedIn()) {
                                leaderboardController.toMenu();
                            } else
                                leaderboardController.toLoggedIn();
                        }
                    }
                }
        );

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(title);
        add(leaderboard);
        add(buttons);
        leaderboardController.load();
    }

    private void clearJOptionPane(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LeaderboardState state = (LeaderboardState) evt.getNewValue();
        if (state.getLeaderboardError() != null) {
            JOptionPane.showMessageDialog(this, state.getLeaderboardError());
        } else {
            //Remove everything from leaderboard
            leaderboard.removeAll();
            //Add new labels for the leaderboard
            JLabel placeLabel = new JLabel("Place");
            JLabel userLabel = new JLabel("User");
            JLabel ptsLabel = new JLabel("Points");
            //Set font for labels
            Font font = new Font("Arial", Font.BOLD, 16);
            placeLabel.setFont(font);
            userLabel.setFont(font);
            ptsLabel.setFont(font);
            //Add labels to leaderboard
            leaderboard.add(placeLabel);
            leaderboard.add(userLabel);
            leaderboard.add(ptsLabel);

            //The following forloop adds users to the leaderboard.
            //Note that the place and score values are dummy values. These will be replaced
            //formulaically when we have determined how to set places/scores.

            String[] UserIds = state.getLeaderboardUserIDs();
            String currUserId = state.getLoggedInUserID();
            int j = -1;  // Initialize to -1 to indicate not found

            // Iterate through the array to find the index of currUserId
            for (int k = 0; k < UserIds.length; k++) {
                if (UserIds[k].equals(currUserId)) {
                    j = k;
                    break;  // Exit the loop once found
                }
            }

            int i = 0;
            Float score = 0.0F;
            for (String user : state.getLeaderboardUsers()) {
                JLabel place = new JLabel((i + 1) + ".");
                JLabel userName = new JLabel(user);
                score = state.getLeaderboardScores()[i];

                userName.putClientProperty("id", state.getLeaderboardUserIDs()[i]);

                JLabel pts = new JLabel(Float.toString(score));
                Font font1 = new Font("Arial", Font.PLAIN, 16);
                userName.setFont(font1);
                leaderboard.add(place);
                leaderboard.add(userName);
                leaderboard.add(pts);
                i++;
                if (i == j){
                    userName.setForeground(Color.RED);
                    pts.setForeground(Color.RED);
                }
            }
            leaderboard.revalidate();
            leaderboard.repaint();
        }
    }
}
