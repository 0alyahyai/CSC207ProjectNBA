package use_case.leaderboard;

import app.*;
import data_access.*;
import entity.*;
import entity.dummys.PlayerEvaluatorDummy;
import entity.dummys.TeamComparatorDummy;
import entity.dummys.TeamEvaluatorDummy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import use_case.leaderboard.view.LeaderboardView;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.login.view.LoginView;
import use_case.menu.interface_adapter.MenuViewModel;
import use_case.menu.view.MenuView;
import use_case.signup.interface_adapter.SignupViewModel;
import use_case.signup.view.SignupView;
import use_case.view_team.interface_adapter.ViewTeamViewModel;
import use_case.view_team.view.ViewTeamView;
import view.LoggedInView;
import view.LoggedInViewModel;
import view.ViewManager;
import view.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

//This is a test class for Leaderboard
public class LeaderboardTest {

    private ViewManagerModel viewManagerModel;
    private LeaderboardViewModel leaderboardViewModel;

    LeaderboardTest() throws IOException {
    }

//    The following clears the csv file after each test
    @AfterEach
    public void tearDown() throws IOException {
        String filePath = "./users.csv";

        try {
            // Open the FileWriter with append mode set to false (clearing the file)
            FileWriter fileWriter = new FileWriter(filePath, false);

            // Close the FileWriter to save changes
            fileWriter.close();

            System.out.println("CSV file cleared successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testMain() throws IOException {

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        CardLayout cardLayout = new CardLayout();

        // ToDo: Changed Menu View to grid so options appear down a column?
//        int columns = 3;
//        int rows = 0; // You can adjust the number of rows based on your needs
//        GridLayout gridLayout = new GridLayout(rows, columns);

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views, BorderLayout.CENTER);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        this.viewManagerModel = viewManagerModel;
        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        MenuViewModel menuViewModel = new MenuViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        this.leaderboardViewModel = leaderboardViewModel;
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ViewTeamViewModel viewTeamViewModel = new ViewTeamViewModel();

        APIinterface apiDAO = new MockAPIDAO();

        FileUserDataAccessObject userDataAccessObject;
        try {
            File csvfile = new File("./users.csv");
            assert csvfile.exists();
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory(),
                    apiDAO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        APIDataAccessObject apiDataAccessObject;
        apiDataAccessObject = new APIDataAccessObject();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInViewFactory.create(loggedInViewModel, viewManagerModel, viewTeamViewModel,
                userDataAccessObject, apiDataAccessObject);
        views.add(loggedInView, loggedInView.viewName);

        PlayerEvaluator playerEvaluator = new PlayerEvaluatorDummy();
        TeamEvaluator teamEvaluator = new TeamEvaluatorDummy(playerEvaluator);
        TeamComparator teamComparator = new TeamComparatorDummy(teamEvaluator);

        MenuView menuView = MenuUseCaseFactory.create(menuViewModel, viewManagerModel, signupViewModel, leaderboardViewModel, loginViewModel);
        views.add(menuView, menuView.viewName);

        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(loggedInViewModel, menuView.viewName, loggedInView.viewName, viewManagerModel, leaderboardViewModel, userDataAccessObject, teamComparator);
        views.add(leaderboardView, leaderboardView.viewName);

        ViewTeamView viewTeamView = ViewTeamUseCaseFactory.create(viewTeamViewModel, viewManagerModel);
        views.add(viewTeamView, viewTeamView.viewName);


        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    public void addTwoUsers() {
        UserFactory uf = new CommonUserFactory();
        User[] users = new User[5];
        users[0] = uf.create("123", "Peter", "password");
        users[1] = uf.create("321", "Omar", "password");
        APIinterface apiDAO = new APIDataAccessObject();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("./users.csv", uf, apiDAO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fudao.save(users[0]);
        fudao.save(users[1]);
    }

    public User[] addTwoUsersWithTeams() {
        TeamFactory tf = new CommonTeamFactory();
        UserFactory uf = new CommonUserFactory();
        User[] users = new User[5];

        User user1 = uf.create("123", "Peter", "password");
        User user2 = uf.create("321", "Omar", "password");
        Team team1 = tf.createMockTeam();
        Team team2 = tf.createMockTeam();
        user1.setTeam(team1);
        user2.setTeam(team2);

        users[0] = user1;
        users[1] = user2;
        APIinterface apiDAO = new APIDataAccessObject();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("./users.csv", uf, apiDAO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fudao.save(users[0]);
        fudao.save(users[1]);
        return users;
    }

    //This returns the Leaderboard from the leaderboard view
    public JPanel getLeaderboard() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LeaderboardView sv = (LeaderboardView) jp2.getComponent(4);

        return (JPanel) sv.getComponent(1); // this should be the LeaderBoard
    }

    //This returns the back button from the leaderboard view

    public JButton getBackButtonLeaderboard() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LeaderboardView sv = (LeaderboardView) jp2.getComponent(4);

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(0); // this should be the LeaderBoard Back Button
    }

    public JButton getLeaderboardButtonMenu() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        MenuView sv = (MenuView) jp2.getComponent(3);

        JPanel buttons = (JPanel) sv.getComponent(1);

        return (JButton) buttons.getComponent(0); // this should be the LeaderBoard button in the SignupView
    }

    public JButton getLeaderboardButtonLoggedIn() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoggedInView sv = (LoggedInView) jp2.getComponent(2);

        JPanel buttons = (JPanel) sv.getComponent(3);

        return (JButton) buttons.getComponent(0); // this should be the LeaderBoard button in the LoggedInView
    }

    //This is a test method to check if the leaderboard button is present in the SignupView
    @Test
    public void menuTestLeaderboardButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getLeaderboardButtonMenu();
        assert (button.getText().equals("Leaderboard"));
    }

    //This is a test method to check if the leaderboard button is present in the LoggedInView
    @Test
    public void loggedInTestLeaderboardButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getLeaderboardButtonLoggedIn();
        assert (button.getText().equals("Leaderboard"));
    }

    //This is a test method to check if the back button is present in the leaderboard view
    @Test
    public void LeaderboardBackButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getBackButtonLeaderboard();
        assert (button.getText().equals("Back"));
    }

    //This is a test to check if the leaderboard button works in the signupView
    @Test
    public void LeaderboardBackButtonNotLoggedIn() throws IOException {
        testMain();
        JButton button = getLeaderboardButtonMenu();
        JButton back = getBackButtonLeaderboard();
        button.doClick();
        back.doClick();
        assert (viewManagerModel.getActiveView().equals("menu"));
    }

    //This is a test to check if the back button works in the leaderboard view when not logged in
    @Test
    public void LeaderboardBackButtonLoggedIn() throws IOException {
        testMain();
        leaderboardViewModel.getState().setActiveUserID("123");
        JButton toLeaderboard = getLeaderboardButtonMenu();
        JButton button = getBackButtonLeaderboard();
        toLeaderboard.doClick();
        button.doClick();
        assert (viewManagerModel.getActiveView().equals("logged in"));
    }

    //This is a test method for load when there are no users
    @Test
    public void loadTestNoUsers() throws IOException {
        testMain();
        JButton toLeaderboard = getLeaderboardButtonMenu();
        toLeaderboard.doClick();
        JPanel leaderboard = getLeaderboard();
        JLabel userLabel = (JLabel) leaderboard.getComponent(1);
        assert (userLabel.getText().equals("No users with teams yet!"));
    }

    //this is a test method for load when no users have teams

    //ToDo: The following test is currently faulty because the order of names on the leaderboard is random, This will be fixed once I can work with a Non Dummy Team Comparator.
    @Test
    public void loadTestUsersWithTeams() throws IOException {
        User[] users = addTwoUsersWithTeams();
        testMain();
        JButton toLeaderboard = getLeaderboardButtonMenu();
        toLeaderboard.doClick();
        JPanel leaderboard = getLeaderboard();
        JLabel userLabel1 = (JLabel) leaderboard.getComponent(0 + 2*2);
        JLabel userLabel2 = (JLabel) leaderboard.getComponent(1 + 2*3);
        assert (userLabel1.getText().equals(users[0].getUserName()));
        assert (userLabel2.getText().equals(users[1].getUserName()));
    }

    @Test
    public void leaderboardLoggedInWithTeam() throws IOException {
        User[] users = addTwoUsersWithTeams();
        testMain();
        leaderboardViewModel.getState().setActiveUserID(users[1].getUserID());
        JButton toLeaderboard = getLeaderboardButtonMenu();
        toLeaderboard.doClick();
        JPanel leaderboard = getLeaderboard();
        JLabel userLabel1 = (JLabel) leaderboard.getComponent(0 + 2*2);
        JLabel userLabel2 = (JLabel) leaderboard.getComponent(1 + 2*3);
        assert (userLabel1.getText().equals(users[0].getUserName()));
        assert (userLabel2.getText().equals(users[1].getUserName()));
    }


}
