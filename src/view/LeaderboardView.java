package view;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;

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
        if (leaderboardViewModel.getState().getLeaderboard() != null) {
            for (String user : leaderboardViewModel.getState().getLeaderboard()) {
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


        back.addActionListener(
                // We might need to edit this method to take into account if user is loggen in or not
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            leaderboardController.back();
                        }
                    }
                }
        );

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(title);
        add(leaderboard);
        add(buttons);
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

            //ToDo: Replace user label with button, so that signed-in users can click them and directly compare teams.
            int i = 1;
            int score = 1000;
            for (String user : state.getLeaderboard()) {
                JLabel place = new JLabel(i + ".");
                JLabel userName = new JLabel(user);
                JLabel pts = new JLabel(Integer.toString(score));
                Font font1 = new Font("Arial", Font.PLAIN, 16);
                userName.setFont(font1);
                leaderboard.add(place);
                leaderboard.add(userName);
                leaderboard.add(pts);
            }
            leaderboard.revalidate();
            leaderboard.repaint();
        }
    }
}
