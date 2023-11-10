package view;

import interface_adapter.Menu.MenuViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

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
        leaderboard = new JPanel();
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
                            leaderboardController.back();
                        }
                    }
                }
        );


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
        }
    }
}
