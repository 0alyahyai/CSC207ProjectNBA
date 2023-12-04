package use_case.algorithm.interface_adapter;

import use_case.algorithm.AlgorithmOutputBoundary;
import use_case.algorithm.AlgorithmOutputData;
import use_case.compareTeam.CompareOutputBoundary;
import use_case.compareTeam.interface_adapter.CompareState;
import use_case.compareTeam.interface_adapter.CompareViewModel;
import view.ViewManagerModel;

public class AlgorithmPresenter implements AlgorithmOutputBoundary{

    private final ViewManagerModel viewManagerModel;
    private  final AlgorithmViewModel algorithmViewModel;

    public AlgorithmPresenter(ViewManagerModel viewManagerModel, AlgorithmViewModel algorithmViewModel){
        this.viewManagerModel = viewManagerModel;
        this.algorithmViewModel = algorithmViewModel;

    }

    @Override
    public void prepareSuccessView(AlgorithmOutputData outputData) {
        AlgorithmState state = new AlgorithmState(outputData.getActiveScore(), outputData.getOtherScore(), outputData.getAlgorithm(),
                outputData.getActiveTeamName(), outputData.getActivePlayerName(), outputData.getOtherTeamName(), outputData.getOtherPlayerName());
        algorithmViewModel.setState(state);
        algorithmViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("algorithm-view");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String problem) {

    }
}
