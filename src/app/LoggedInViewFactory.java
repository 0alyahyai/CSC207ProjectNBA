package app;

import data_access.APIinterface;
import use_case.compareTeam.CompareDataAccessInterface;
import use_case.compareTeam.CompareInputBoundary;
import use_case.compareTeam.CompareInteractor;
import use_case.compareTeam.CompareOutputBoundary;
import use_case.compareTeam.interface_adapter.CompareController;
import use_case.compareTeam.interface_adapter.ComparePresenter;
import use_case.compareTeam.interface_adapter.CompareViewModel;
import use_case.view_team.ViewTeamInputBoundary;
import use_case.view_team.ViewTeamInteractor;
import use_case.view_team.ViewTeamOutputBoundary;
import use_case.view_team.ViewTeamUserDataAccessInterface;
import use_case.view_team.interface_adapter.ViewTeamController;
import use_case.view_team.interface_adapter.ViewTeamPresenter;
import use_case.view_team.interface_adapter.ViewTeamViewModel;
import use_case.compareTeam.viewCompareTeam.CompareViewOptions;
import view.LoggedInView;
import view.LoggedInViewModel;
import view.ViewManagerModel;

public class LoggedInViewFactory {

    private LoggedInViewFactory() {}

    public static LoggedInView create(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
                                      ViewTeamViewModel viewTeamViewModel,
                                      ViewTeamUserDataAccessInterface userDataAccessObject,
                                      APIinterface apiDataAccessObject,
                                      CompareViewModel compareViewModel) {

        ViewTeamController viewTeamController = createViewTeamUseCase(viewManagerModel, viewTeamViewModel,
                loggedInViewModel, userDataAccessObject, apiDataAccessObject);

        CompareController compareController = createCompareTeamUseCase(
                viewManagerModel,
                compareViewModel,
                (CompareDataAccessInterface) userDataAccessObject
        );



        return new LoggedInView(loggedInViewModel, viewManagerModel, viewTeamController, compareController);
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

    public static CompareController createCompareTeamUseCase(ViewManagerModel viewManagerModel,
                                                              CompareViewModel compareViewModel,
                                                              CompareDataAccessInterface dao){

        CompareOutputBoundary comparePresenter = new ComparePresenter(viewManagerModel, compareViewModel);
        CompareInputBoundary compareUCI = new CompareInteractor(comparePresenter,
                dao);



        return new CompareController(compareUCI);
    }

//    public static CompareViewOptions create(ViewManagerModel viewManagerModel,
//                                            CompareViewModel compareViewModel) {
//
//
//
//        return new CompareViewOptions(algorithmController, compareViewModel, viewManagerModel);
//    }
}