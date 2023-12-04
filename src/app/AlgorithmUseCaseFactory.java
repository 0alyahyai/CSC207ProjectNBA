package app;

import data_access.APIinterface;
import use_case.algorithm.AlgorithmDataAccessInterface;
import use_case.algorithm.AlgorithmInputBoundary;
import use_case.algorithm.AlgorithmInteractor;
import use_case.algorithm.AlgorithmOutputBoundary;
import use_case.algorithm.interface_adapter.AlgorithmController;
import use_case.algorithm.interface_adapter.AlgorithmPresenter;
import use_case.algorithm.interface_adapter.AlgorithmViewModel;
import use_case.algorithm.viewAlgorithm.AlgorithmView;
import use_case.compareTeam.interface_adapter.CompareViewModel;
import use_case.compareTeam.viewCompareTeam.CompareViewOptions;
import view.ViewManagerModel;

public class AlgorithmUseCaseFactory {

    private static AlgorithmController createAlgorithmController(
            AlgorithmViewModel algoViewModel,
            ViewManagerModel viewManagerModel,
            APIinterface apiDAO,
            AlgorithmDataAccessInterface dataAccessObject


    ) {
        AlgorithmOutputBoundary algorithmPresenter = new AlgorithmPresenter(viewManagerModel, algoViewModel);
        AlgorithmInputBoundary algorithmUCI = new AlgorithmInteractor(algorithmPresenter, dataAccessObject, apiDAO);
        return new AlgorithmController(algorithmUCI);
    }

    public static AlgorithmView createAlgorithmView(
            AlgorithmViewModel algoViewModel,
            ViewManagerModel viewManagerModel
    ) {
        return new AlgorithmView(algoViewModel, viewManagerModel);
    }

    public static CompareViewOptions createFirstView (
            AlgorithmViewModel algoViewModel,
            ViewManagerModel viewManagerModel,
            APIinterface apiDAO,
            AlgorithmDataAccessInterface dataAccessObject,
            CompareViewModel compareViewModel

    ) {
        AlgorithmController algorithmController = createAlgorithmController(algoViewModel, viewManagerModel,
                apiDAO, dataAccessObject);
        return new CompareViewOptions(algorithmController, compareViewModel, viewManagerModel);
    }


}
