package app;


import interface_adapter.Menu.MenuController;
import interface_adapter.Menu.MenuPresenter;
import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import view.MenuView;

import javax.swing.*;
import java.io.IOException;

public class MenuUseCaseFactory {

    /** Prevent instantiation. */
    private MenuUseCaseFactory() {}

    public static MenuView create(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel,
            SignupViewModel signupViewModel, LeaderboardViewModel leaderboardViewModel) {

        try {
            MenuController menuController = createMenuUseCase(viewManagerModel, signupViewModel, leaderboardViewModel);
            return new MenuView(menuViewModel, menuController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static MenuController createMenuUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel,
                                                    LeaderboardViewModel leaderboardViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        MenuOutputBoundary menuOutputBoundary = new MenuPresenter(viewManagerModel, signupViewModel, leaderboardViewModel);

        MenuInputBoundary menuInteractor = new MenuInteractor(menuOutputBoundary);

        return new MenuController(menuInteractor);
    }
}
