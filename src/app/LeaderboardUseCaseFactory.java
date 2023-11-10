package app;


import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import view.LeaderboardView;

import javax.swing.*;
import java.io.IOException;

public class LeaderboardUseCaseFactory {

    private LeaderboardUseCaseFactory() {}

    public static LeaderboardView create(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel,
                                  LeaderboardViewModel leaderboardViewModel, LeaderboardDataAccessInterface userDataAccessObject) {

        try {
            LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, menuViewModel, userDataAccessObject);
            return new LeaderboardView(leaderboardViewModel, leaderboardController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    public static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel,
                                                                  MenuViewModel menuViewModel, LeaderboardDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LeaderboardOutputBoundary leaderboardOutputBoundary = new LeaderboardPresenter(leaderboardViewModel, viewManagerModel, menuViewModel);

        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(leaderboardOutputBoundary, userDataAccessObject);

        return new LeaderboardController(leaderboardInteractor);
    }

}
