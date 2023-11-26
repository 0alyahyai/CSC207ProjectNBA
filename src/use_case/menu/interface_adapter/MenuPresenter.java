package use_case.menu.interface_adapter;

import view.ViewManagerModel;
import use_case.leaderboard.interface_adapter.LeaderboardViewModel;

import use_case.signup.interface_adapter.SignupViewModel;
import use_case.menu.MenuOutputBoundary;

import use_case.login.interface_adapter.LoginViewModel;

public class MenuPresenter implements MenuOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LeaderboardViewModel leaderboardViewModel;

    private final LoginViewModel loginViewModel;

    private ViewManagerModel viewManagerModel;

    public MenuPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                         LeaderboardViewModel leaderboardViewModel,
                         LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSignupView() {
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLeaderboardView() {
        viewManagerModel.setActiveView(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLoginView() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        //ToDo: implement this
    }
}
