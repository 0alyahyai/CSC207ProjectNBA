package use_case.leaderboard.view;

import use_case.leaderboard.interface_adapter.LeaderboardController;
import use_case.leaderboard.interface_adapter.LeaderboardState;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import view.LoggedInState;
import view.LoggedInViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class LeaderboardView extends JPanel implements ActionListener, PropertyChangeListener {

    private final LeaderboardViewModel leaderboardViewModel;
    private final LeaderboardController leaderboardController;
    public final String viewName = "leaderboard";
    private final JLabel title = new JLabel("Leaderboard");
    private final JPanel leaderboard;
    private final JButton back;

    public LeaderboardView(LoggedInViewModel loggedInViewModel, LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardController = leaderboardController;
        leaderboardViewModel.addPropertyChangeListener(this);
        loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        leaderboard = new JPanel(new GridLayout(0, 3, 10, 10));

        // Create an EmptyBorder with left and right margins (10 pixels each)
        int leftMargin = 15;
        int rightMargin = 15;
        int topMargin = 10;
        int bottomMargin = 10;
        Border marginBorder = new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin);

        // Set the border of the leaderboard panel to the EmptyBorder
        leaderboard.setBorder(marginBorder);


        if (leaderboardViewModel.getState().getLeaderboardUsers() != null) {
            for (String user : leaderboardViewModel.getState().getLeaderboardUsers()) {
                JLabel userLabel = new JLabel(user);
                Font font = new Font("Arial", Font.PLAIN, 16);
                userLabel.setFont(font);
                leaderboard.add(userLabel);
            }
        } else {
//            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            JLabel leftSpacer = new JLabel();
            JLabel userLabel = new JLabel("No users with teams yet!");
            JLabel rightSpacer = new JLabel();

//            leftSpacer.setBorder(border);
//            userLabel.setBorder(border);
//            rightSpacer.setBorder(border);

            try {
                ImageIcon icon = new ImageIcon(new File("").getAbsolutePath()
                        + "/src/assests/icons/error.png");
                //resize the icon
                Image image = icon.getImage();
                Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon errorIcon = new ImageIcon(newimg);
                userLabel.setIcon(errorIcon);

            } catch (Exception ignored) {

            }

            userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            Font font = new Font("Arial", Font.PLAIN, 16);
            userLabel.setFont(font);
            leaderboard.add(leftSpacer);
            leaderboard.add(userLabel);
            leaderboard.add(rightSpacer);
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
                                leaderboardController.toLoggedIn();
                            } else
                                leaderboardController.toMenu();
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


    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof LoggedInState) {
            LoggedInState state = (LoggedInState) evt.getNewValue();
            if (state.isLoggedIn()) {
                leaderboardController.setActiveUser();
                return;
            }
           LeaderboardState leaderboardState = leaderboardViewModel.getState();
            leaderboardState.setActiveUserID(null);
           return;
        }

        LeaderboardState state = (LeaderboardState) evt.getNewValue();
        if (state.getLeaderboardError() != null) {
            JOptionPane.showMessageDialog(this, state.getLeaderboardError());
        } else {
            //Remove everything from leaderboard
            leaderboard.removeAll();

            if (state.getLeaderboardUsers() == null) {
//                Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
                JLabel leftSpacer = new JLabel();
                JLabel userLabel = new JLabel("No users with teams yet!");
                JLabel rightSpacer = new JLabel();

//                leftSpacer.setBorder(border);
//                userLabel.setBorder(border);
//                rightSpacer.setBorder(border);

                // Set the icon for the label
                try {
                    ImageIcon icon = new ImageIcon(new File("").getAbsolutePath()
                            + "/src/assests/icons/error.png");
                    //resize the icon
                    Image image = icon.getImage();
                    Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                    ImageIcon errorIcon = new ImageIcon(newimg);
                    userLabel.setIcon(errorIcon);

                } catch (Exception ignored) {

                }

                userLabel.setHorizontalAlignment(SwingConstants.CENTER);

                userLabel.setFont(new Font("Open Sans", Font.BOLD, 14));

                leaderboard.add(leftSpacer);
                leaderboard.add(userLabel);
                leaderboard.add(rightSpacer);

                return;
            }

            //Add new labels for the leaderboard
            JLabel placeLabel = new JLabel("Rank");
            JLabel userLabel = new JLabel("User");
            JLabel ptsLabel = new JLabel("Score");
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
            String currUserId = state.getActiveUserID();
            int j = -1;  // Initialize to -1 to indicate not found

            // Iterate through the array to find the index of currUserId
            if (currUserId != null) {
                for (int k = 0; k < UserIds.length; k++) {
                    if (UserIds[k].equals(currUserId)) {
                        j = k;
                        break;  // Exit the loop once found
                    }
                }
            }

            int i = 0;
            Float score = 0.0F;
            for (String user : state.getLeaderboardUsers()) {
                JLabel place = new JLabel((i + 1) + ".");
                JLabel userName = new JLabel(user);
                score = state.getLeaderboardScores()[i];
                JLabel pts = new JLabel(Float.toString(score));

                userName.putClientProperty("id", state.getLeaderboardUserIDs()[i]);

                if (i == 0){
                    place.setForeground(Color.GREEN);
                    userName.setForeground(Color.GREEN);
                    pts.setForeground(Color.GREEN);
                    userName.setFont(new Font("Arial", Font.BOLD, 16));
                    place.setFont(new Font("Arial", Font.BOLD, 16));
                    pts.setFont(new Font("Arial", Font.BOLD, 16));

                    try {
                        ImageIcon icon = new ImageIcon(new File("").getAbsolutePath()
                                + "/src/assests/icons/crown.png");
                        //resize the icon
                        Image image = icon.getImage();
                        Image newimg = image.getScaledInstance(50, 30,  java.awt.Image.SCALE_SMOOTH);
                        ImageIcon errorIcon = new ImageIcon(newimg);
                        userName.setIcon(errorIcon);

                    } catch (Exception ignored) {

                    }
                }
                if (i == j){
                    place.setForeground(Color.BLUE);
                    userName.setForeground(Color.BLUE);
                    pts.setForeground(Color.BLUE);

                    try {
                        ImageIcon icon = new ImageIcon(new File("").getAbsolutePath()
                                + "/src/assests/icons/dot-filled.png");
                        //resize the icon
                        Image image = icon.getImage();
                        Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
                        ImageIcon errorIcon = new ImageIcon(newimg);
                        userName.setIcon(errorIcon);

                    } catch (Exception ignored) {

                    }
                }

                Font font1 = new Font("Arial", Font.PLAIN, 16);
                userName.setFont(font1);
                leaderboard.add(place);
                leaderboard.add(userName);
                leaderboard.add(pts);

                i++;
            }
            leaderboard.revalidate();
            leaderboard.repaint();
        }
    }
}
