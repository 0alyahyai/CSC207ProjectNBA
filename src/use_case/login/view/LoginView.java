package use_case.login.view;

import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginState;
import use_case.login.interface_adapter.LoginViewModel;
import view.LabelTextPanel;
import view.ViewManagerModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;
    private final LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller, ViewManagerModel viewManagerModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;


        JLabel title = new JLabel("Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

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

        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            usernameInputField.setText(""); //clear the username and password fields after logging in.
                            passwordInputField.setText("");

                            //Clearing the input fields above is done so that the fields are empty if a user logs out.
                            //If the fields are not cleared, then the fields will still contain the username and password.
                            //TODO: Currently this has the unintended side effect of clearing the fields if the user
                            // enters an invalid username or password. This is not ideal, but it is not a high priority.

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );
        // Switch to the menu view if the cancel button is pressed.
        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            //bug fix required: clear the username and password fields when returning to menu
                            LoginState currentState = loginViewModel.getState(); //get the current state
                            currentState.setUsername("");
                            currentState.setPassword("");
                            usernameInputField.setText(""); //clear the username and password fields
                            passwordInputField.setText("");
                            loginViewModel.setState(currentState);
                            viewManagerModel.setActiveView("menu");
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
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
        LoginState state = (LoginState) evt.getNewValue();
        // If there is an error, display it. Here, usernameError is a bit misleading, as it can be an error for
        // either the username or the password.
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
        state.setUsernameError(null);
        loginViewModel.setState(state);
        setFields(state);
    }

    private void setFields(LoginState state) {

        usernameInputField.setText(state.getUsername());;
    }

}