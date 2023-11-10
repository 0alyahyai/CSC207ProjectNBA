package app;


import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import view.LeaderboardView;

import javax.swing.*;
import java.io.IOException;

public class LeaderboardUseCaseFactory {

    private LeaderboardUseCaseFactory() {}

    public static LeaderboardView create(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel,
                                  LeaderboardViewModel leaderboardViewModel) {

        try {
            LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, menuViewModel);
            return new LeaderboardView(leaderboardViewModel, leaderboardController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel,
                                                                  MenuViewModel menuViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LeaderboardOutputBoundary leaderboardOutputBoundary = new LeaderboardPresenter(viewManagerModel, menuViewModel);

        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(leaderboardOutputBoundary);

        return new LeaderboardController(leaderboardInteractor);
    }
}
