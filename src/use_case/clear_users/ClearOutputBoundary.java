package use_case.clear_users;


public interface ClearOutputBoundary {
    void prepareSuccessView(ClearOutputData clearOutputData);

    void prepareFailView(String error);
}
