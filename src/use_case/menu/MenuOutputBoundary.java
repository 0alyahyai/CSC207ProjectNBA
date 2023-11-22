package use_case.menu;

import use_case.signup.SignupOutputData;

public interface MenuOutputBoundary {
    void prepareSignupView();

    void prepareLeaderboardView();

    void prepareLoginView();

    void prepareFailView(String error);
}
