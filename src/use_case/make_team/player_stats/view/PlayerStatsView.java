package use_case.make_team.player_stats.view;

import use_case.make_team.player_stats.interface_adapater.PlayerStatsViewModel;

import javax.swing.*;
import java.awt.*;

public class PlayerStatsView extends JPanel {

    private PlayerStatsViewModel viewModel;

    public PlayerStatsView(PlayerStatsViewModel viewModel) {
        this.viewModel = viewModel;
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
    }

    private void drawAdditionalText(Graphics g) {
        // Example text, replace with actual data from viewModel
        String additionalText = "Replace with actual stats from viewModel";
        g.setColor(Color.BLACK);
        g.drawString(additionalText, 10, getHeight() - 30);
    }

    public void updateView(PlayerStatsViewModel newViewModel) {
        this.viewModel = newViewModel;
        repaint(); // Redraw the panel with new data
    }
}
