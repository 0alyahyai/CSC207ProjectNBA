package interface_adapter.leaderboard;

import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import use_case.leaderboard.LeaderboardOutputBoundary;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {

    private final MenuViewModel menuViewModel;
    private ViewManagerModel viewManagerModel;

    public LeaderboardPresenter(ViewManagerModel viewManagerModel,
                                MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
    }

    @Override
    public void toMenu() {
        viewManagerModel.setActiveView(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
