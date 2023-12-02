package use_case.leaderboard;

import app.*;
import data_access.*;
import entity.*;
import entity.dummys.PlayerEvaluatorDummy;
import entity.dummys.TeamComparatorDummy;
import entity.dummys.TeamEvaluatorDummy;
import org.junit.jupiter.api.Test;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import use_case.leaderboard.view.LeaderboardView;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.login.view.LoginView;
import use_case.make_team.MakeTeamDAI;
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
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

//This is a test class for Leaderboard
public class LeaderboardTest {

    private ViewManagerModel viewManagerModel;
    private LeaderboardViewModel leaderboardViewModel;

    LeaderboardTest() throws IOException {
    }

    void testMain() throws IOException {
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views, BorderLayout.CENTER);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        this.viewManagerModel = viewManagerModel;

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

        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(menuView.viewName, loggedInView.viewName, viewManagerModel, leaderboardViewModel, userDataAccessObject, teamComparator);
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
        APIinterface apiDAO = new APIDataAccessObject();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("./users.csv", uf, apiDAO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fudao.save(uf.create("123", "Peter", "password"));
        fudao.save(uf.create("321", "Peter", "password"));
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
//        testMain();
//        JButton toLeaderboard = getLeaderboardButtonMenu();
//        JButton button = getBackButtonLeaderboard();
//        toLeaderboard.doClick();
//        button.doClick();
//        assert (viewManagerModel.getActiveView().equals("Logged In"));
    }

    //this is a test method for load
    @Test
    public void loadTestNoUsersWithTeams() throws IOException {
        addTwoUsers();
        testMain();
    }


}
