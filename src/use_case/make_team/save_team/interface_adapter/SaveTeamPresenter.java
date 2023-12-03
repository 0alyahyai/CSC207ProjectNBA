package use_case.make_team.save_team.interface_adapter;

import use_case.make_team.save_team.SaveTeamOutputBoundary;
import use_case.make_team.save_team.SaveTeamOutputData;
import view.LoggedInViewModel;
import view.ViewManagerModel;

public class SaveTeamPresenter implements SaveTeamOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public SaveTeamPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SaveTeamOutputData outputData) {
        viewManagerModel.setActiveView(LoggedInViewModel.TITLE_LABEL);
        viewManagerModel.firePropertyChanged();
        System.out.println("PREPARE SUCCESS VIEW INVOKED!");

        // TODO: Code for showing pop-up of success with outputData.<...>
    }

    @Override
    public void prepareFailView(String problem) {
        viewManagerModel.setActiveView(LoggedInViewModel.TITLE_LABEL);
        viewManagerModel.firePropertyChanged();

        // TODO: Code for showing pop-up of success with outputData.<...>
    }
}
