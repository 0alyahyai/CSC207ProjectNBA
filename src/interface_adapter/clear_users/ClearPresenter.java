package interface_adapter.clear_users;

import interface_adapter.ViewManagerModel;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearOutputData;


public class ClearPresenter implements ClearOutputBoundary {

    private final ClearViewModel clearViewModel;

    private ViewManagerModel viewManagerModel;

    public ClearPresenter(ViewManagerModel viewManagerModel,
                          ClearViewModel clearViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.clearViewModel = clearViewModel;
    }

    @Override
    public void prepareSuccessView(ClearOutputData response) {
        ClearState clearState = clearViewModel.getState();
        clearState.setUsersCleared(response.getUsersCleared());
    }

    @Override
    public void prepareFailView(String error) {
        ClearState clearState = clearViewModel.getState();
        clearState.setClearError(error);
        clearViewModel.firePropertyChanged();
    }
}

