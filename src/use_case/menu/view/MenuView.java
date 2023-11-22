package use_case.menu.view;

import use_case.login.interface_adapter.LoginViewModel;
import use_case.login.view.LoginView;
import use_case.menu.interface_adapter.MenuController;
import use_case.menu.interface_adapter.MenuViewModel;
import use_case.leaderboard.interface_adapter.LeaderboardController;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import use_case.signup.interface_adapter.SignupState;
import use_case.signup.interface_adapter.SignupViewModel;
import view.LoggedInViewModel;

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

    private final LeaderboardController leaderboardController;

    private final MenuController menuController;

    private final JButton Leaderboard;
    private final JButton signUp;
    private final JButton cancel;

    private final JButton logIn;
//    private final JButton clear;


    public MenuView(MenuViewModel menuViewModel, MenuController menuController,
                    LeaderboardController leaderboardController){
        this.leaderboardController = leaderboardController;
//        this.leaderboardViewModel = leaderboardViewModel;
        this.menuViewModel = menuViewModel;
        this.menuController = menuController;
        menuViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel(new GridLayout(0, 1, 10, 10));
        Leaderboard = new JButton(LeaderboardViewModel.LEADERBOARD_BUTTON_LABEL);
        buttons.add(Leaderboard);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        logIn = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
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
//                            leaderboardViewModel
                            leaderboardController.load();
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

        logIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logIn)) {
                            menuController.execute("logIn");
                        }
                    }
                }
        );

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
