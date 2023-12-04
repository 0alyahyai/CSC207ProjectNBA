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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "menu";

    private final MenuViewModel menuViewModel;

    private final MenuController menuController;

    private final JButton Leaderboard;
    private final JButton signUp;

    //unused
//    private final JButton cancel;

    private final JButton logIn;

    //unused
//    private final JButton clear;


    public MenuView(MenuViewModel menuViewModel, MenuController menuController
                    ){
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

        //IF WE DON'T LIKE THE LOOK, REMOVE THE FOLLOWING FOR LOOP
        for (Component button : buttons.getComponents()) {
            button.addMouseListener(new HoverMouseListener((JButton) button));
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);
        }

        //Styling:
        int leftMargin = 30;
        int rightMargin = 30;
        int topMargin = 10;
        int bottomMargin = 10;
        Border marginBorder = new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin);
        buttons.setBorder(marginBorder);
        title.setBorder(marginBorder);

        Font font = new Font("Arial", Font.BOLD, 16);
        title.setFont(font);

        //unused
//        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
//        buttons.add(cancel);

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


//        cancel.addActionListener(this);

        add(title);
        add(buttons);
    }

    public static class HoverMouseListener extends MouseAdapter {
        private final JButton button;

        public HoverMouseListener(JButton button) {
            this.button = button;
        }
        //We can change the MouseOver highlights here
        @Override
        public void mouseEntered(MouseEvent e) {
            // Change appearance on hover (e.g., change background color)
            button.setBackground(Color.WHITE);
            button.setForeground(Color.ORANGE);
            button.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Reset appearance when the mouse exits
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        }
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
