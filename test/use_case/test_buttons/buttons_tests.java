package use_case.test_buttons;

import app.*;
import data_access.APIDataAccessObject;
import data_access.APIinterface;
import data_access.FileUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.algorithm.interface_adapter.AlgorithmViewModel;
import use_case.algorithm.viewAlgorithm.AlgorithmView;
import use_case.compareTeam.interface_adapter.CompareViewModel;
import use_case.compareTeam.viewCompareTeam.CompareViewOptions;
import use_case.entity_helpers.PlayerEvaluatorFactory;
import use_case.entity_helpers.TeamComparator;
import use_case.entity_helpers.TeamEvaluator;
import use_case.entity_helpers.TeamEvaluatorFactory;
import use_case.entity_helpers.dummys.LeaderboardTeamComparator;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import use_case.leaderboard.view.LeaderboardView;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.login.view.LoginView;
import use_case.make_team.create_team.CreateTeamView;
import use_case.make_team.create_team.CreateTeamViewModel;
import use_case.make_team.player_stats.interface_adapater.PlayerStatsViewModel;
import use_case.make_team.player_stats.view.PlayerStatsView;
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
import static org.junit.Assert.assertThrows;

public class buttons_tests {

    private ViewManagerModel viewManagerModel;
    private LeaderboardViewModel leaderboardViewModel;
    private SignupView signupview;

    buttons_tests() throws IOException {
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

        APIinterface apiDAO = new APIDataAccessObject();

        //Here VARP starts coding
        CompareViewModel compareViewModel = new CompareViewModel();
        //Here VARP ends coding

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
        this.signupview = signupView;

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInViewFactory.create(loggedInViewModel, viewManagerModel, viewTeamViewModel,
                userDataAccessObject, apiDataAccessObject, compareViewModel);
        views.add(loggedInView, loggedInView.viewName);

//        PlayerEvaluator playerEvaluator = new PlayerEvaluatorDummy();
//        TeamEvaluator teamEvaluator = new TeamEvaluatorDummy(playerEvaluator);
//        TeamComparator teamComparator = new TeamComparatorDummy(teamEvaluator);

        PlayerEvaluatorFactory playerEvaluatorFactory = new PlayerEvaluatorFactory();
        TeamEvaluatorFactory teamEvaluatorFactory = new TeamEvaluatorFactory(playerEvaluatorFactory);
        TeamEvaluator teamEvaluator = teamEvaluatorFactory.getLogarithmTeamEvaluator(apiDAO);
        TeamComparator teamComparator = new LeaderboardTeamComparator(teamEvaluator);

        MenuView menuView = MenuUseCaseFactory.create(menuViewModel, viewManagerModel, signupViewModel, leaderboardViewModel, loginViewModel);
        views.add(menuView, menuView.viewName);

        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(loggedInViewModel, menuView.viewName, loggedInView.viewName, viewManagerModel, leaderboardViewModel, userDataAccessObject, teamComparator);
        views.add(leaderboardView, leaderboardView.viewName);

        ViewTeamView viewTeamView = ViewTeamUseCaseFactory.create(viewTeamViewModel, viewManagerModel);
        views.add(viewTeamView, viewTeamView.viewName);


        // Player Stats use case
        PlayerStatsViewModel playerStatsViewModel = new PlayerStatsViewModel();
        PlayerStatsView playerStatsView = new PlayerStatsView(playerStatsViewModel, viewManagerModel);
        views.add(playerStatsView, playerStatsView.viewName);


        // Make-Team Usecase
        CreateTeamViewModel createTeamViewModel = new CreateTeamViewModel();
        CreateTeamView createTeamView =
                MakeTeamUseCaseFactory.createCreateTeamView(
                        createTeamViewModel,
                        apiDAO,
                        userDataAccessObject,
                        viewManagerModel,
                        playerStatsViewModel

                );
        views.add(createTeamView, CreateTeamViewModel.VIEW_NAME);





        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        //Here VARP starts coding

//        CompareViewOptions compareViewOptions = LoggedInViewFactory.create(viewManagerModel, compareViewModel);
//        views.add(compareViewOptions, compareViewOptions.viewName);

        AlgorithmViewModel algorithmViewModel = new AlgorithmViewModel();
//        CompareViewModel compareViewModel = new CompareViewModel();
        CompareViewOptions compareViewOptions = AlgorithmUseCaseFactory.createFirstView(
                algorithmViewModel, viewManagerModel, apiDAO, userDataAccessObject, compareViewModel
        );
        views.add(compareViewOptions, compareViewOptions.viewName);

        AlgorithmView algorithmView = AlgorithmUseCaseFactory.createAlgorithmView(algorithmViewModel, viewManagerModel);
        views.add(algorithmView, algorithmView.viewName);



        //Here VARP ends coding

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

        users[1] = user1;
        users[0] = user2;
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

    public JButton cancelFromSignUp() {
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

        SignupView sv = (SignupView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(4);

        return (JButton) buttons.getComponent(1); // this should be the cancel button in the SignupView
    }

    public JButton getSignupButton() {
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

        return (JButton) buttons.getComponent(1); // this should be the LeaderBoard button in the SignupView
    }
    public JButton getLoginButton() {
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

        return (JButton) buttons.getComponent(2); // this should be the LeaderBoard button in the SignupView
    }

    public LoggedInView getLoggedInView(){
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

        return sv;
    }

    public JButton getCompareTeamButton() {

        LoggedInView sv = getLoggedInView();

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(2); // this should be the CompareTeam

    }

    public JButton getCreateTeamButton() {

        LoggedInView sv = getLoggedInView();

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(1); // this should be the CompareTeam

    }

//    getCancelButtonLogin() {
//        return Main.getCancelButtonLogin();
//    }
//
//    getCancelButtonSignup() {
//        return Main.getCancelButtonRegister();
//    }
    @Test
    public void MenuButtonsPresent() throws IOException {
        Main.main(null);
        JButton signup = getSignupButton();
        JButton login = getLoginButton();
        assert (signup.getText().equals("Sign up"));
        assert (login.getText().equals("Log in"));
    }
    @Test
    public void SignUpMenuTest() throws IOException {
        testMain();
        JButton signup = getSignupButton();
        signup.doClick();
        assert (viewManagerModel.getActiveView().equals("sign up"));
    }
    @Test
    public void LogInMenuTest() throws IOException {
        testMain();
        JButton signup = getLoginButton();
        signup.doClick();
        assert (viewManagerModel.getActiveView().equals("log in"));
    }
    @Test
    public void SignUpCancelTest() throws IOException {
        testMain();
        JButton signup = getSignupButton();
        signup.doClick();
        JButton cancel = cancelFromSignUp();
        cancel.doClick();
        assert (viewManagerModel.getActiveView().equals("menu"));
    }

    @Test void CompareTeamTest() throws IOException {
        testMain();
        viewManagerModel.setActiveView("logged in");
        JButton toLeaderboard = getCompareTeamButton();
        leaderboardViewModel.getState().setActiveUserID("123");
        assertThrows(NullPointerException.class, toLeaderboard::doClick);
    }

    @Test void CreateTeamTest() throws IOException {
        testMain();
        viewManagerModel.setActiveView("logged in");
        JButton toCreateTeam = getCreateTeamButton();
        toCreateTeam.doClick();
        assert (viewManagerModel.getActiveView().equals("Create Team View"));
    }


}
