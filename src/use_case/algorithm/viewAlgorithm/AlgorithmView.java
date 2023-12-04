package use_case.algorithm.viewAlgorithm;

import use_case.algorithm.interface_adapter.AlgorithmViewModel;
import use_case.compareTeam.interface_adapter.CompareViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AlgorithmView extends JPanel implements ActionListener, PropertyChangeListener {



    public final String viewName = "algorithm-view";
    private final AlgorithmViewModel algorithmViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JPanel scorePanel;
    private final Font nameFont;

    private final Font scoreFont;

    private final Font vsFont;

    private final JLabel team1Name;

    private final JLabel team1Score;

    private final JLabel username1;

    private final JLabel team2Name;

    private final JLabel team2Score;

    private final JLabel username2;

    private final JLabel vsLabel;

    private final JPanel panel11;

    private final JPanel panel22;

    private final JButton backButton;



    public AlgorithmView(AlgorithmViewModel algorithmViewModel, ViewManagerModel viewManagerModel) {
        setLayout(new BorderLayout());
        this.algorithmViewModel = algorithmViewModel;
        this.viewManagerModel = viewManagerModel;
        this.algorithmViewModel.addPropertyChangeListener(this);

        // Score display panel
        scorePanel = new JPanel(new GridLayout(1, 3, 10, 0));

        // Font customizations
        nameFont = new Font("Arial", Font.BOLD, 20);
        scoreFont = new Font("Arial", Font.BOLD, 50);
        vsFont = new Font("Arial", Font.BOLD, 60);

        // Team 1 components
        team1Name = new JLabel("PLACEHOLDER", SwingConstants.CENTER);
        team1Name.setFont(nameFont);

        team1Score = new JLabel("PLACEHOLDER", SwingConstants.CENTER);
        team1Score.setFont(scoreFont);
        team1Score.setForeground(Color.decode("#77DD77")); // Pastel green
        username1 = new JLabel("PLACEHOLDER", SwingConstants.CENTER);
        username1.setFont(nameFont);






        // Team 2 components
        team2Name = new JLabel("PLACEHOLDER", SwingConstants.CENTER);
        team2Name.setFont(nameFont);
        team2Score = new JLabel("PLACEHOLDER", SwingConstants.CENTER);
        team2Score.setFont(scoreFont);
        team2Score.setForeground(Color.decode("#FF6961")); // Pastel red
        username2 = new JLabel("PLACEHOLDER", SwingConstants.CENTER);
        username2.setFont(nameFont);

        // VS Label
        vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(vsFont);

        panel11 = new JPanel(new BorderLayout());
        panel11.add(team1Name, BorderLayout.NORTH);
        panel11.add(team1Score, BorderLayout.CENTER);
        panel11.add(username1, BorderLayout.SOUTH);



        panel22 = new JPanel(new BorderLayout());
        panel22.add(team2Name, BorderLayout.NORTH);
        panel22.add(team2Score, BorderLayout.CENTER);
        panel22.add(username2, BorderLayout.SOUTH);

        // Add components to scorePanel
        scorePanel.add(panel11);
        scorePanel.add(vsLabel);
        scorePanel.add(panel22);

        // Back button at the bottom
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 50));

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewManagerModel.setActiveView("CompareOptions");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        // Add components to the frame
        add(scorePanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        float scoreActivex10 = algorithmViewModel.getState().getScoreActiveTeam()*10;
        team1Name.setText(algorithmViewModel.getState().activeTeamName);
        team1Score.setText(String.valueOf(scoreActivex10));
        username1.setText(algorithmViewModel.getState().getActivePlayerName());

        float scoreOtherx10 = algorithmViewModel.getState().getScoreOther()*10;
        team2Name.setText(algorithmViewModel.getState().otherTeamName);
        team2Score.setText(String.valueOf(scoreOtherx10));
        username2.setText(algorithmViewModel.getState().getOtherPlayerName());

        if (algorithmViewModel.getState().getScoreActiveTeam().equals(algorithmViewModel.getState().getScoreOther())){
            team1Score.setForeground(Color.decode("#000000"));
            team2Score.setForeground(Color.decode("#000000"));

        } else if (algorithmViewModel.getState().getScoreActiveTeam() > algorithmViewModel.getState().getScoreOther()) {
            team1Score.setForeground(Color.decode("#77DD77"));
            team2Score.setForeground(Color.decode("#FF6961"));

        } else {

            team1Score.setForeground(Color.decode("#FF6961"));
            team2Score.setForeground(Color.decode("#77DD77"));

        }


    }
}
