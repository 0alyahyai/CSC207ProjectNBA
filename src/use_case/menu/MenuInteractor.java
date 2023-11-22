package use_case.menu;

import entity.User;
import entity.UserFactory;
import use_case.signup.SignupInputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import use_case.signup.SignupUserDataAccessInterface;

public class MenuInteractor implements MenuInputBoundary{


    final MenuOutputBoundary menuPresenter;

    public MenuInteractor(MenuOutputBoundary MenuPresenter) {
        this.menuPresenter = MenuPresenter;
    }

    //For the following code, I create an if case for each button, again could we do this more efficiently?
    @Override
    public void execute(MenuInputData menuInputData) {
        String data = menuInputData.getButtonName();
        if (data.equals("signup")) {
            menuPresenter.prepareSignupView();
        } else if (data.equals("leaderboard")) {
            menuPresenter.prepareLeaderboardView();
        } else if (data.equals("logIn")) {
            menuPresenter.prepareLoginView();
        } else {
            menuPresenter.prepareFailView("Invalid input.");
        }
    }
}
