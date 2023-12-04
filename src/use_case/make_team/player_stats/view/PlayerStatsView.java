package use_case.make_team.player_stats.view;

import use_case.make_team.create_team.CreateTeamViewModel;
import use_case.make_team.player_stats.interface_adapater.PlayerStatsState;
import use_case.make_team.player_stats.interface_adapater.PlayerStatsViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;



public class PlayerStatsView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Player Statistics";

    private PlayerStatsViewModel viewModel;

    private final ViewManagerModel viewManagerModel;

    private final JButton backButton;
    private JPanel graphPanel;

    private JFreeChart chart;


    public PlayerStatsView(PlayerStatsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.viewManagerModel = viewManagerModel;
        JPanel Graph = new JPanel();

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(179, 26, 26)); // Light gray background
        backButton.setMargin(new Insets(5, 15, 5, 15)); // Padding around the button text


        //switch to the menu view when the back button is pressed
        backButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            viewManagerModel.setActiveView(CreateTeamViewModel.VIEW_NAME);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //change the size
        setPreferredSize(new Dimension(800, 200));


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(800, 200));

    }

    private JFreeChart createChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();



        PlayerStatsState state = viewModel.getState();

        ArrayList<ArrayList<Double>> stats = state.getPlayerStats();

        String[] seriesNames = {"Points", "Assists", "Rebounds"}; //since the stats list will always be of length 3



        for (int seriesIndex = 0; seriesIndex < stats.size(); seriesIndex++) {
            List<Double> statList = stats.get(seriesIndex);
            String seriesName = seriesNames[seriesIndex]; //I can use the seriesNames to set the name of the legend;

            if (statList.size() == 1) {
                dataset.addValue(statList.get(0), seriesName, "1");
                dataset.addValue(statList.get(0), seriesName, "Only One Game Played");
                // If there's only one data point, add it twice to create a visible dot or a tiny line

            } else {
                // Multiple data points - proceed as usual
                for (int gameIndex = 0; gameIndex < statList.size(); gameIndex++) {
                    dataset.addValue(statList.get(gameIndex), seriesName, String.valueOf(gameIndex + 1));
                }
            }
        }

        return ChartFactory.createLineChart(
                "Player Statistics", // chart title
                "Game", // domain axis label
                "Score", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true,
                false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        removeAll();
        ArrayList<ArrayList<Double>> stats = viewModel.getState().getPlayerStats();
        if (stats.get(0).get(0) == -1.0){
            // Create the label and set a message
            JTextArea messageLabel = new JTextArea("Player has not played any games this season.");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            messageLabel.setEditable(false); // Make the JTextArea non-editable

            // Add the label to the view
            add(messageLabel);
            add(backButton);

        }
        else {
            chart = createChart();
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 600)); // Adjust size as needed

            graphPanel = chartPanel;


            add(graphPanel);
            add(backButton);


        }

    }
}
