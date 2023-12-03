package use_case.make_team.player_stats.view;

import use_case.make_team.player_stats.interface_adapater.PlayerStatsState;
import use_case.make_team.player_stats.interface_adapater.PlayerStatsViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerStatsView extends JPanel {

    public final String viewName = "Player Statistics";

    private PlayerStatsViewModel viewModel;

    private final ViewManagerModel viewManagerModel;

    private final JButton back;
    private final JPanel graphPanel;

    public PlayerStatsView(PlayerStatsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        JPanel Graph = new JPanel();

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setBackground(new Color(179, 26, 26)); // Light gray background
        back.setMargin(new Insets(5, 15, 5, 15)); // Padding around the button text


        //switch to the menu view when the back button is pressed
        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            viewManagerModel.setActiveView("make team");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //change the size
        setPreferredSize(new Dimension(800, 200));
        //Add a title

        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Your custom drawing code for the graph
                drawGraph(g);
            }
        };

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(800, 200));

        add(graphPanel);
        add(back);
    }

//    private void drawGraph(Graphics g) {
//        // Set up colors, styles, etc. as needed
//        g.setColor(Color.BLUE);
//
//        // Determine the width and height of the panel
//        int panelWidth = graphPanel.getWidth();
//        int panelHeight = graphPanel.getHeight();
//
//        // Determine the number of data points
//        int dataPoints = graphData.length;
//
//        // Determine the width of each bar in the graph
//        int barWidth = panelWidth / dataPoints;
//
//        // Determine the maximum data value for scaling
//        int maxDataValue = getMaxValue(graphData);
//
//        // Draw bars for each data point
//        for (int i = 0; i < dataPoints; i++) {
//            int barHeight = (int) ((double) graphData[i] / maxDataValue * panelHeight);
//            int x = i * barWidth;
//            int y = panelHeight - barHeight;
//            g.fillRect(x, y, barWidth, barHeight);
//        }
//    }

    private int getMaxValue(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.RED);
//        g.drawRect(10, 10, 100, 100);
//
//        drawGraph(g);
////        drawAdditionalText(g);
//    }

    private void drawGraph(Graphics g) {
        // You can customize these colors
        g.setColor(Color.BLUE);

        // Example: Drawing axes
        g.drawLine(10, getHeight() - 50, getWidth() - 10, getHeight() - 50); // X-axis
        g.drawLine(10, getHeight() - 50, 10, 10); // Y-axis

//         Example: Drawing the graph (Replace with actual logic)
//         Fetch data from viewModel and draw the graph here

        PlayerStatsState state = viewModel.getState();

//        ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>();

        // Manually adding lists of integers
//        listOfLists.add(new ArrayList<>(Arrays.asList(1, 3, 5, 7)));
//        listOfLists.add(new ArrayList<>(Arrays.asList(2, 4, 6, 8)));
//        listOfLists.add(new ArrayList<>(Arrays.asList(10, 9, 8, 7)));
//        listOfLists.add(new ArrayList<>(Arrays.asList(3, 6, 9, 12)));
//
//        ArrayList<ArrayList<Integer>> stats = listOfLists;
        ArrayList<ArrayList<Integer>> stats = state.getPlayerStats();
////
        for (List<Integer> statlist : stats) {
            int prevY = 10 ;
            int prevX = 10 ;
            for (Integer stat : statlist) {
                int x = prevX + stat + 20;
                int y = prevY + 20;

//                g.drawLine(prevX, prevY, x, y);
                g.fillOval(x - 2, y - 2, 4, 4);

                prevX = x;
                prevY = y;

            }

        }

    }



//    private void drawAdditionalText(Graphics g) {
//        // Example text, replace with actual data from viewModel
//        String additionalText = "Replace with actual stats from viewModel";
//        g.setColor(Color.BLACK);
//        g.drawString(additionalText, 10, getHeight() - 30);
//    }

    public void updateView(PlayerStatsViewModel newViewModel) {
        this.viewModel = newViewModel;
        repaint(); // Redraw the panel with new data
    }



    public static void main(String[] args) {

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

        System.out.println("PlayerStatsView size: " + playerStatsView.getSize());
    }


//    public void updateView(PlayerStatsViewModel newViewModel) {
//        this.viewModel = newViewModel;
//        repaint(); // Redraw the panel with new data
//    }
}
