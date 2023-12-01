package app;

import use_case.login.interface_adapter.LoginViewModel;
import use_case.menu.interface_adapter.MenuController;
import use_case.menu.interface_adapter.MenuPresenter;
import use_case.menu.interface_adapter.MenuViewModel;
import view.ViewManagerModel;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;
import use_case.signup.interface_adapter.SignupViewModel;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.menu.view.MenuView;

import javax.swing.*;
import java.io.IOException;

public class MenuUseCaseFactory {

    /** Prevent instantiation. */
    private MenuUseCaseFactory() {}

    public static MenuView create(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel,
                                  SignupViewModel signupViewModel, LeaderboardViewModel leaderboardViewModel,
                                  LoginViewModel loginViewModel)
            throws IOException {

        try {
            MenuController menuController = createMenuUseCase(viewManagerModel, signupViewModel, leaderboardViewModel,
                    loginViewModel);

            return new MenuView(menuViewModel, menuController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static MenuController createMenuUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel,
                                                    LeaderboardViewModel leaderboardViewModel,
                                                    LoginViewModel loginViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        MenuOutputBoundary menuOutputBoundary = new MenuPresenter(viewManagerModel, signupViewModel, leaderboardViewModel,
                loginViewModel);

        MenuInputBoundary menuInteractor = new MenuInteractor(menuOutputBoundary);

        return new MenuController(menuInteractor);
    }
}
