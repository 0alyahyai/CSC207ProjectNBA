package app;

import data_access.APIinterface;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import use_case.algorithm.AlgorithmDataAccessInterface;
import use_case.algorithm.interface_adapter.AlgorithmViewModel;
import use_case.algorithm.viewAlgorithm.AlgorithmView;
import use_case.compareTeam.interface_adapter.CompareViewModel;
import use_case.compareTeam.viewCompareTeam.CompareViewOptions;
import use_case.entity_helpers.*;
import use_case.entity_helpers.dummys.LeaderboardTeamComparator;
import use_case.entity_helpers.dummys.PlayerEvaluatorDummy;
import use_case.entity_helpers.dummys.TeamComparatorDummy;
import use_case.entity_helpers.dummys.TeamEvaluatorDummy;
import use_case.leaderboard.view.LeaderboardView;
import use_case.login.view.LoginView;
import use_case.make_team.create_team.CreateTeamView;
import use_case.make_team.create_team.CreateTeamViewModel;
import use_case.make_team.player_stats.interface_adapater.PlayerStatsViewModel;
import use_case.make_team.player_stats.view.PlayerStatsView;
import use_case.menu.interface_adapter.MenuViewModel;
import use_case.menu.view.MenuView;
import use_case.signup.view.SignupView;
import use_case.view_team.interface_adapter.ViewTeamViewModel;
import use_case.view_team.view.ViewTeamView;
import view.ViewManagerModel;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import view.LoggedInViewModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.signup.interface_adapter.SignupViewModel;
import view.*;
import data_access.APIDataAccessObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.



        // The main application window.
        JFrame application = new JFrame("NBA Fantasy League");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        CardLayout cardLayout = new CardLayout();

        //Setting Frame Logo
        try {
            ImageIcon logo = new ImageIcon(new File("").getAbsolutePath()
                    + "/src/assests/icons/bball.png");
            //resize the icon
            Image image = logo.getImage();
            Image frameLogo = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            application.setIconImage(frameLogo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views, BorderLayout.CENTER);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        MenuViewModel menuViewModel = new MenuViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
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



}