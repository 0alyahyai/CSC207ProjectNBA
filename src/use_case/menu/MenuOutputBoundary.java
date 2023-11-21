package use_case.menu;

import use_case.signup.SignupOutputData;

public interface MenuOutputBoundary {
    void prepareSignupView();

    void prepareLeaderboardView();

    void prepareFailView(String error);
}
