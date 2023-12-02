package use_case.leaderboard;

import app.LeaderboardUseCaseFactory;
import data_access.MockDAO;
import entity.*;
import entity.dummys.TeamComparatorDummyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import use_case.leaderboard.view.LeaderboardView;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.make_team.MakeTeamDAI;
import use_case.menu.interface_adapter.MenuViewModel;
import use_case.signup.interface_adapter.SignupViewModel;
import view.LoggedInViewModel;
import view.ViewManagerModel;

import java.io.IOException;

//This is a test class for Leaderboard
public class LeaderboardTest {

    private MenuViewModel menuViewModel;
    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private LeaderboardViewModel leaderboardViewModel;
    private LoggedInViewModel loggedInViewModel;

    private TeamComparatorFactory teamComparatorFactory;

    private UserFactory userFactory;
    private PlayerFactory playerFactory;
    private LeaderboardUseCaseFactory leaderboardUseCaseFactory;

    private TeamFactory teamFactory;

    private MakeTeamDAI dao;

    LeaderboardTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        menuViewModel = new MenuViewModel();
        viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        leaderboardViewModel = new LeaderboardViewModel();
        loggedInViewModel = new LoggedInViewModel();
        teamComparatorFactory = new TeamComparatorDummyFactory();

        userFactory = new CommonUserFactory();
        playerFactory = new CommonPlayerFactory();
        teamFactory = new CommonTeamFactory();
        dao = new MockDAO();
        leaderboardUseCaseFactory = new LeaderboardUseCaseFactory();
    }

    //This is a test method for back
    @Test
    public void backTest() {
        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(menuViewModel.getViewName(), loggedInViewModel.getViewName(), viewManagerModel,
                leaderboardViewModel, (LeaderboardFileUserDataAccessInterface) dao, teamComparatorFactory.create());

//        leaderboardView.back();
    }

    //this is a test method for load
    @Test
    public void loadTest() {
        LeaderboardView leaderboardView = LeaderboardUseCaseFactory.create(menuViewModel.getViewName(), loggedInViewModel.getViewName(), viewManagerModel,
                leaderboardViewModel, (LeaderboardFileUserDataAccessInterface) dao, teamComparatorFactory.create());

//        leaderboardView.load();

    }


}
