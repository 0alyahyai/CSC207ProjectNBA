package use_case.view_team.view;

import use_case.view_team.interface_adapter.ViewTeamState;
import use_case.view_team.interface_adapter.ViewTeamViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class ViewTeamView extends JPanel implements ActionListener, PropertyChangeListener {
    // Java Swing class that displays the team's information

    public final String viewName = "view team";

    private final ViewTeamViewModel viewTeamViewModel;

    private final ViewManagerModel viewManagerModel;

    private final JButton back;




    public ViewTeamView(ViewTeamViewModel viewTeamViewModel, ViewManagerModel viewManagerModel) {
        this.viewTeamViewModel = viewTeamViewModel;
        this.viewManagerModel = viewManagerModel;
        viewTeamViewModel.addPropertyChangeListener(this);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setBackground(new Color(200, 200, 200)); // Light gray background
        back.setMargin(new Insets(5, 15, 5, 15)); // Padding around the button text


        //switch to the menu view when the back button is pressed
        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            viewManagerModel.setActiveView("logged in");
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
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewTeamState state = viewTeamViewModel.getState();
        //TODO: implment this when we have state fully implemented
        if (state.getViewTeamError() != null) {

            //create an icon safely
            try {
                ImageIcon icon = new ImageIcon(new File("").getAbsolutePath()
                        + "/src/assests/icons/error.png");
                //resize the icon
                Image image = icon.getImage();
                Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);

                JOptionPane.showMessageDialog(null, state.getViewTeamError(),
                        "Error", JOptionPane.INFORMATION_MESSAGE, icon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, state.getViewTeamError(),
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }


            //reset the error message
            state.setViewTeamError(null);
            viewTeamViewModel.setState(state);

        }
        else {
            //remove everything from the view
            removeAll();

            //Borders
            Border roundedBorder = new LineBorder(Color.GRAY, 2, true); // Rounded line border
            Border shadowBorder = new CompoundBorder(
                    new LineBorder(Color.GRAY, 1),
                    new EmptyBorder(5, 5, 5, 5)); // Shadow-like effect
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            Border titledBorder = BorderFactory.createTitledBorder(
                    new LineBorder(new Color(200, 40, 12, 255), 2),
                    "Player Stats",
                    TitledBorder.LEFT,
                    TitledBorder.TOP);


            //Begins construction of the view

            //create a title
            JLabel title = new JLabel("View Team");
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            //visual adjustments to title
            Font titleFont = new Font("Arial", Font.BOLD, 24);
            title.setFont(titleFont);
            title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            //add the title to the view
            add(title);

            JPanel firstRow = new JPanel(new GridLayout(1, 5));
            //change the height of the row
            firstRow.setPreferredSize(new Dimension(400, 100));


            //add the ascii art to the first row
            //Make font very small
            Font font = new Font("Monospaced", Font.BOLD, 1);

            // Add the ASCII art to the first row in separate JLabels
            for (int i = 0; i < 5; i++) {
                //creates random ascii art
                String htmlAscii = "<html><pre>" + getRandomAsciiString() + "</pre></html>";

                JLabel asciiLabel = new JLabel(htmlAscii);
                asciiLabel.setBackground(Color.WHITE);
                asciiLabel.setForeground(Color.BLACK);
                //center the ascii art
                asciiLabel.setHorizontalAlignment(JLabel.CENTER);
                asciiLabel.setFont(font);
                asciiLabel.setBorder(etchedBorder);
                firstRow.add(asciiLabel);
            }

            add(firstRow);

            Font playerFont = new Font("Arial", Font.PLAIN, 16);
            //create a new row with 5 columns
            JPanel newRow = new JPanel(new GridLayout(1, 5));
            //add a player stats string to each column
            for (int i = 0; i < 5; i++) {
                //create the formatted string
                String playerStats = String.format("""
                                •Team Name: %s
                                •Name: %s
                                •PointsPG : %s
                                •AssistsPG : %s
                                •ReboundsPG: %s
                                •StealsPG: %s
                                •BlocksPG %s
                                """,
                        state.getPlayerNStats(i).get(0), state.getPlayerNStats(i).get(1),
                        state.getPlayerNStats(i).get(2), state.getPlayerNStats(i).get(3),
                        state.getPlayerNStats(i).get(4), state.getPlayerNStats(i).get(5),
                        state.getPlayerNStats(i).get(6));
                //create a label with the formatted string in html format

                JTextArea playerStatsTextArea = new JTextArea(playerStats);
                playerStatsTextArea.setEditable(false);
                playerStatsTextArea.setLineWrap(true);
                playerStatsTextArea.setWrapStyleWord(true);
                playerStatsTextArea.setFont(playerFont);
                playerStatsTextArea.setBorder(titledBorder);

                // If you need a scroll pane
                JScrollPane scrollPane = new JScrollPane(playerStatsTextArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                JPanel nestedPanel = new JPanel();
                nestedPanel.setLayout(new BorderLayout());

                JLabel playerTitle = new JLabel("Player " + (i + 1), SwingConstants.CENTER);
                nestedPanel.add(playerTitle, BorderLayout.NORTH);
                nestedPanel.add(scrollPane, BorderLayout.CENTER);

                newRow.add(nestedPanel);

            }
            //add the row to the view
            add(newRow);
            //add the back button to the view
            add(back);
        }
    }

    //helper method
    private static String getRandomAsciiString(){
        /**
         * This method returns a random ascii string from the assets folder
         * @return a random ascii string
         *
         */

        //gets a random integer between 1 and 10
        int randomInt = (int) (Math.random() * 5) + 1;
        //gets the file path of the random ascii string, uses the absolute path
        String filePath = new File("").getAbsolutePath() + "/src/assests/ascii/ascii-art" + randomInt + ".txt";
        //reads the file
        File file = new File(filePath);
        //reads the entire txt file into a string
        String asciiString = "";
        try {
            asciiString = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            return asciiString;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }

}
