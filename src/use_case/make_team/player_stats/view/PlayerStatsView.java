package use_case.make_team.player_stats.view;

import use_case.make_team.player_stats.interface_adapater.PlayerStatsState;
import use_case.make_team.player_stats.interface_adapater.PlayerStatsViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerStatsView extends JPanel {

    public final String viewName = "Player Statistics";

    private PlayerStatsViewModel viewModel;

    private final ViewManagerModel viewManagerModel;

    private final JButton back;

    public PlayerStatsView(PlayerStatsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
        drawAdditionalText(g);
    }

    private void drawGraph(Graphics g) {
        // You can customize these colors
        g.setColor(Color.BLUE);

        // Example: Drawing axes
        g.drawLine(10, getHeight() - 50, getWidth() - 10, getHeight() - 50); // X-axis
        g.drawLine(10, getHeight() - 50, 10, 10); // Y-axis

        // Example: Drawing the graph (Replace with actual logic)
        // Fetch data from viewModel and draw the graph here

        PlayerStatsState state = viewModel.getState();

        ArrayList<ArrayList<Integer>> stats = state.getPlayerStats();

        for (List<Integer> statlist : stats) {
            int prevY = 1;
            int prevX = 0;
            for (Integer stat : statlist) {
                int x = stat;
                int y = prevY + 1;

                g.drawLine(prevX, prevY, x, y);
                g.fillOval(x - 2, y - 2, 4, 4);

                prevX = x;
                prevY = y;

            }

        }




    }

    private void drawAdditionalText(Graphics g) {
        // Example text, replace with actual data from viewModel
        String additionalText = "Replace with actual stats from viewModel";
        g.setColor(Color.BLACK);
        g.drawString(additionalText, 10, getHeight() - 30);
    }


//    public void updateView(PlayerStatsViewModel newViewModel) {
//        this.viewModel = newViewModel;
//        repaint(); // Redraw the panel with new data
//    }
}
