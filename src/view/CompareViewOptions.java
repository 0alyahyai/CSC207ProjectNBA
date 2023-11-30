package view;

import use_case.compareTeam.interface_adapter.CompareViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CompareViewOptions extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Compare";

    final JButton cancelButton;
    final JButton algorithmone;
    final JButton algorithmtwo;

    final JButton algorithmthree;

    @Override
    public void actionPerformed(ActionEvent e) {

    } //CHECK todo

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    } //CHECK todo

    public CompareViewOptions(ViewManagerModel viewManagerModel) {


        JLabel title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        cancelButton = new JButton(CompareViewModel.CANCEL_BUTTON_LABEL);
        algorithmone = new JButton(CompareViewModel.ALGORITHMONE_BUTTON_LABEL);
        algorithmtwo = new JButton(CompareViewModel.ALGORITHMTWO_BUTTON_LABEL);
        algorithmthree = new JButton(CompareViewModel.ALGORITHMTHREE_BUTTON_LABEL);

        cancelButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancelButton)) {
                            viewManagerModel.setActiveView("logged in");
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }


        );


        buttons.add(cancelButton);
        buttons.add(algorithmone);
        buttons.add(algorithmtwo);
        buttons.add(algorithmthree);
        this.add(buttons);




    }
}
