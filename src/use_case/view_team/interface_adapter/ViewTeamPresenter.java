package use_case.view_team.interface_adapter;

import use_case.view_team.ViewTeamOutputBoundary;
import use_case.view_team.ViewTeamOutputData;
import view.LoggedInViewModel;
import view.ViewManagerModel;

public class ViewTeamPresenter implements ViewTeamOutputBoundary {

    private final ViewTeamViewModel viewTeamViewModel;

    private final ViewManagerModel viewManagerModel;

    private final LoggedInViewModel loggedInViewModel;

    public ViewTeamPresenter(ViewTeamViewModel viewTeamViewModel,
                             ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewTeamViewModel = viewTeamViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;

    }

    @Override
    public void prepareSuccessView(ViewTeamOutputData outputData) {
        ViewTeamState viewTeamState = viewTeamViewModel.getState();

        for (int i = 1; i <= 5; i++) {
            viewTeamState.setPlayerNStats(i, outputData.getPlayerNStats(i));
        }
        viewTeamViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(viewTeamViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    //TODO: implement
    @Override
    public void prepareFailview(ViewTeamOutputData outputData) {

    }
}
