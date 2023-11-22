package app;


import use_case.menu.interface_adapter.MenuViewModel;
import view.ViewManagerModel;
import use_case.leaderboard.interface_adapter.LeaderboardController;
import use_case.leaderboard.interface_adapter.LeaderboardPresenter;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.view.LeaderboardView;

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
