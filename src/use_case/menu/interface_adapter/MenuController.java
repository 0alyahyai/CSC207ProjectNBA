package use_case.menu.interface_adapter;

import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInputData;

public class MenuController {

    final MenuInputBoundary MenuUseCaseInteractor;

    public MenuController(MenuInputBoundary MenuUseCaseInteractor) {
        this.MenuUseCaseInteractor = MenuUseCaseInteractor;
    }

    public void execute(String buttonName) {
        MenuInputData MenuInputData = new MenuInputData(buttonName);

        MenuUseCaseInteractor.execute(MenuInputData);
    }
}
