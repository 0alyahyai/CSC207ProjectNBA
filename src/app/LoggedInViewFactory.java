package app;

import data_access.APIinterface;
import use_case.view_team.ViewTeamInputBoundary;
import use_case.view_team.ViewTeamInteractor;
import use_case.view_team.ViewTeamOutputBoundary;
import use_case.view_team.ViewTeamUserDataAccessInterface;
import use_case.view_team.interface_adapter.ViewTeamController;
import use_case.view_team.interface_adapter.ViewTeamPresenter;
import use_case.view_team.interface_adapter.ViewTeamViewModel;
import view.LoggedInView;
import view.LoggedInViewModel;
import view.ViewManagerModel;

public class LoggedInViewFactory {

    private LoggedInViewFactory() {}

    public static LoggedInView create(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
                                      ViewTeamViewModel viewTeamViewModel,
                                      ViewTeamUserDataAccessInterface userDataAccessObject,
                                      APIinterface apiDataAccessObject) {
        ViewTeamController viewTeamController = createViewTeamUseCase(viewManagerModel, viewTeamViewModel,
                loggedInViewModel, userDataAccessObject, apiDataAccessObject);


        return new LoggedInView(loggedInViewModel, viewManagerModel, viewTeamController);
    }

    //viewTeamController is a dependency of LoggedInView
    private static ViewTeamController createViewTeamUseCase(ViewManagerModel viewManagerModel,
            ViewTeamViewModel viewTeamViewModel, LoggedInViewModel loggedInViewModel,
            ViewTeamUserDataAccessInterface userDataAccessObject, APIinterface apiDataAccessObject){
        ViewTeamOutputBoundary viewTeamOutputBoundary = new ViewTeamPresenter(viewTeamViewModel, viewManagerModel,
                loggedInViewModel);
        ViewTeamInputBoundary viewTeamInteractor = new ViewTeamInteractor(viewTeamOutputBoundary, userDataAccessObject,
                apiDataAccessObject);
        return new ViewTeamController(viewTeamInteractor);
    }
}
