package use_case.menu;

import use_case.signup.SignupInputData;

public interface MenuInputBoundary {
    void execute(MenuInputData menuInputData);
}
