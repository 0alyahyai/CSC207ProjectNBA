package use_case.compareTeam.viewCompareTeam;

import use_case.algorithm.interface_adapter.AlgorithmController;
import use_case.compareTeam.interface_adapter.CompareViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class CompareViewOptions extends JPanel implements ActionListener, PropertyChangeListener {

    private final AlgorithmController algorithmController;
    public final String viewName = "CompareOptions";
    private final CompareViewModel compareViewModel;
    private final ViewManagerModel viewManagerModel;
    private final String LABEL_NO_TEAMS = "NO TEAMS TO COMPARE";
    private final JComboBox<String> comboBox1;
    private final JComboBox<String> comboBox2;
    private JPanel buttonPanel;
    private JButton backButton;
    private JButton compareButton;
    private JPanel comboBoxPanel;
    public CompareViewOptions(AlgorithmController algorithmController, CompareViewModel compareViewModel, ViewManagerModel viewManagerModel) {
        this.algorithmController = algorithmController;
        this.compareViewModel = compareViewModel;
        this.viewManagerModel = viewManagerModel;
        this.compareViewModel.addPropertyChangeListener(this);

        String[] items2 = {"Markov prediction", "Logarithmic comparison"};

        comboBoxPanel = new JPanel();

        comboBox1 = new JComboBox<>();

        comboBox2 = new JComboBox<>(items2);

        buttonPanel = new JPanel();

        // Create the 'Back' button
        backButton = new JButton("Back");

        // Create the 'Compare' button
        compareButton = new JButton("Compare");

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {

                            viewManagerModel.setActiveView("logged in");
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );
        buttonPanel.add(backButton);

        if (compareViewModel.getState().getPossible()) {
            String[] items1 = new String[compareViewModel.getState().getTeams().size()];

            // Create the first combo box with items
            for (int i = 0; i < compareViewModel.getState().getTeams().size(); i++) {

                //what happens if there is no team for a given username?
                if (compareViewModel.getState().getTeams().get(i) != null){
                    items1[i] = compareViewModel.getState().getTeams().get(i).getTeamName();
                }

            }

            setLayout(new BorderLayout());
            comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.Y_AXIS));
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            comboBoxPanel.add(comboBox1); // Add first combo box to the top of the panel
            comboBoxPanel.add(comboBox2);
            add(comboBoxPanel, BorderLayout.NORTH);
            buttonPanel.add(compareButton);

            // Add button panel to the bottom of the main panel
            add(buttonPanel, BorderLayout.SOUTH);
            comboBox1.removeAllItems();
            for (String item: items1) {
                comboBox1.addItem(item);
            }

//            // Optional: Add action listeners to buttons
//            backButton.addActionListener(
//                    new ActionListener() {
//                        public void actionPerformed(ActionEvent evt) {
//                            if (evt.getSource().equals(backButton)) {
//
//                                viewManagerModel.setActiveView("logged in");
//                                viewManagerModel.firePropertyChanged();
//
//                            }
//                        }
//                    }
//            );

            //Here VARP will code the button for Compare Teams (The add Action Listener). VARP is below
            compareButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource().equals(compareButton)) {
                                algorithmController.execute(
                                        (String) comboBox1.getSelectedItem(),
                                        (String) comboBox2.getSelectedItem());
                            }

                        }
                    }

            );
            //VARP is above

        } else {


            add(buttonPanel, BorderLayout.SOUTH);

            viewManagerModel.setActiveView("logged in");
            buttonPanel.remove(compareButton);
            this.remove(comboBoxPanel);
            JOptionPane.showMessageDialog(null, "No teams!");

        }



    }

}
