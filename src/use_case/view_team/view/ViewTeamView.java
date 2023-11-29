package use_case.view_team.view;

import use_case.view_team.interface_adapter.ViewTeamState;
import use_case.view_team.interface_adapter.ViewTeamViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewTeamView extends JPanel implements ActionListener, PropertyChangeListener {
    // Java Swing class that displays the team's information

    public final String viewName = "view team";

    private final ViewTeamViewModel viewTeamViewModel;

    private final ViewManagerModel viewManagerModel;

    private final JButton back;

    private final JTable teamTable;

    private final DefaultTableModel tableModel;

    private final JScrollPane scrollPane;

    public ViewTeamView(ViewTeamViewModel viewTeamViewModel, ViewManagerModel viewManagerModel) {
        this.viewTeamViewModel = viewTeamViewModel;
        this.viewManagerModel = viewManagerModel;
        viewTeamViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("View Team");
        title.setAlignmentX(CENTER_ALIGNMENT);

        //Make layout so that table headers are always visible
        setLayout(new BorderLayout());


        //creates a table with the corresponding column names ensuring that the table is not editable and
        //the column names are always visible
        // TODO: fix bug: table headers are cut off.
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Player's Team Name", "Player name", "Points Per Game",
                "Assists Per Game", "Rebounds Per Game", "Steals Per Game", "Blocks Per Game"});
        tableModel.setRowCount(0);
        teamTable = new JTable(tableModel);

        //auto resizes the columns to fit the data

        //makes the table uneditable
        teamTable.setEnabled(false);

        teamTable.setFillsViewportHeight(true);

        // Creating a scroll pane for the table
        // TODO: fix bug: scroll pane effecting the size of other components
        // to fix this, we may need to use frames instead of panels.
        JScrollPane scrollPane = new JScrollPane(teamTable);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        this.scrollPane = scrollPane;


        //creates a button to go back to the main menu
        JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);


        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            viewManagerModel.setActiveView("logged in");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewTeamState state = viewTeamViewModel.getState();
        //TODO: implment this when we have state fully implemented
        if (state.getViewTeamError() != null) {
            JOptionPane.showMessageDialog(this, state.getViewTeamError());
        }
        else {
            //clearing all the rows in the table
           tableModel.setRowCount(0);
            //adding the rows to the table
            for (int i = 0; i < 5; i++) {
               tableModel.addRow(new Object[]{state.getPlayerNStats(i + 1)[0], state.getPlayerNStats(i + 1)[1],
                        state.getPlayerNStats(i + 1)[2], state.getPlayerNStats(i + 1)[3],
                        state.getPlayerNStats(i + 1)[4], state.getPlayerNStats(i + 1)[5],
                        state.getPlayerNStats(i + 1)[6]});
            }
            scrollPane.setPreferredSize(new Dimension(200, 300));
            scrollPane.revalidate();
            scrollPane.repaint();

        }
    }
}
