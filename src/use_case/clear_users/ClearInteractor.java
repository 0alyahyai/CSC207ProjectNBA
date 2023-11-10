package use_case.clear_users;


public class ClearInteractor implements ClearInputBoundary {

    final ClearUserDataAccessInterface userDataAccessObject;
    final ClearOutputBoundary clearPresenter;

    public ClearInteractor(ClearUserDataAccessInterface userDataAccessInterface,
                           ClearOutputBoundary clearOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.clearPresenter = clearOutputBoundary;
    }

    @Override
    public void execute() {
        String[] usersCleared = userDataAccessObject.clearUsers();
        if (usersCleared.length == 0) {
            clearPresenter.prepareFailView("Failed to clear users as there were none to clear.");
        } else {
            ClearOutputData clearOutputData = new ClearOutputData(usersCleared, false);
            clearPresenter.prepareSuccessView(clearOutputData);
        }
    }
}

