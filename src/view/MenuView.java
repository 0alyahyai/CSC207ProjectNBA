package view;

import interface_adapter.Menu.MenuController;
import interface_adapter.Menu.MenuViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "menu";

//    private final LeaderboardViewModel leaderboardViewModel;

    private final MenuViewModel menuViewModel;

//    private final LeaderboardController leaderboardController;

    private final MenuController menuController;

    private final JButton Leaderboard;
    private final JButton signUp;
    private final JButton cancel;
//    private final JButton clear;


    public MenuView(MenuViewModel menuViewModel ,MenuController menuController) {
//        this.leaderboardController = leaderboardController;
//        this.leaderboardViewModel = leaderboardViewModel;
        this.menuViewModel = menuViewModel;
        this.menuController = menuController;
        menuViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        Leaderboard = new JButton(LeaderboardViewModel.LEADERBOARD_BUTTON_LABEL);
        buttons.add(Leaderboard);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        //Current methodology is to create an actionlistener for each button, and pass in the name of the button
        //to the menuController.execute function. Could we do this more efficiently, by using some property of the button?
        //Or is this the best way to do it?

        Leaderboard.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Leaderboard)) {
                            menuController.execute("leaderboard");
                        }
                    }
                }
        );

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            menuController.execute("signup");
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        add(title);
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
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}
