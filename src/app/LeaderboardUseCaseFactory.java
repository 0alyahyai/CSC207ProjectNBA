package app;


import use_case.entity_helpers.TeamComparator;
import view.LoggedInViewModel;
import view.ViewManagerModel;
import use_case.leaderboard.interface_adapter.LeaderboardController;
import use_case.leaderboard.interface_adapter.LeaderboardPresenter;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import use_case.leaderboard.LeaderboardFileUserDataAccessInterface;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.view.LeaderboardView;

import javax.swing.*;
import java.io.IOException;

public class LeaderboardUseCaseFactory {

    public LeaderboardUseCaseFactory() {}

    public static LeaderboardView create(LoggedInViewModel loggedInViewModel, String menuViewName,String loggedInViewName, ViewManagerModel viewManagerModel,
                                  LeaderboardViewModel leaderboardViewModel, LeaderboardFileUserDataAccessInterface userDataAccessObject, TeamComparator teamComparator) {

        try {
            LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, menuViewName, loggedInViewName, userDataAccessObject, teamComparator);
            return new LeaderboardView(loggedInViewModel, leaderboardViewModel, leaderboardController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    public static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel,
                                                                 String menuViewName, String loggedInViewName, LeaderboardFileUserDataAccessInterface userDataAccessObject, TeamComparator teamComparator) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LeaderboardOutputBoundary leaderboardOutputBoundary = new LeaderboardPresenter(leaderboardViewModel, viewManagerModel, menuViewName, loggedInViewName);

        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(leaderboardOutputBoundary, userDataAccessObject, teamComparator);

        return new LeaderboardController(leaderboardInteractor);
    }

}
