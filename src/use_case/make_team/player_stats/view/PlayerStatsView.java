package use_case.make_team.player_stats.view;

import use_case.make_team.create_team.CreateTeamView;
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
import java.util.Arrays;
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

//        add(backButton);
    }

    private JFreeChart createChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();



        PlayerStatsState state = viewModel.getState();

        ArrayList<ArrayList<Double>> stats = state.getPlayerStats();


        for (int seriesIndex = 0; seriesIndex < stats.size(); seriesIndex++) {
            List<Double> statList = stats.get(seriesIndex);
            String seriesName = "Series " + (seriesIndex + 1); // Replace with actual series names as needed

            if (statList.size() == 1) {
                // If there's only one data point, add it twice to create a visible dot or a tiny line
                dataset.addValue(statList.get(0), seriesName, "1");
                dataset.addValue(statList.get(0), seriesName, "Only One Game Played");
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

    public static void main(String[] args) {

        PlayerStatsViewModel playerStatsViewModel = new PlayerStatsViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();


        fff(new PlayerStatsViewModel(), new ViewManagerModel());
    }
    public static void fff(PlayerStatsViewModel viewModel, ViewManagerModel viewManagerModel) {

        JFrame frame = new JFrame("Player Statistics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PlayerStatsView playerStatsView = new PlayerStatsView(viewModel, viewManagerModel);
        frame.add(playerStatsView);

        frame.setSize(400, 300); // Set a specific size
        frame.validate(); // Layout components

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        removeAll();
        chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600)); // Adjust size as needed

        // Replace the custom graphPanel with the JFreeChart panel
        graphPanel = chartPanel;



        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(800, 200));

        add(graphPanel);
        add(backButton);
    }
}
