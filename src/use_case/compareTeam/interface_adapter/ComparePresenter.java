package use_case.compareTeam.interface_adapter;

import entity.Team;
import use_case.compareTeam.CompareOutputBoundary;
import use_case.compareTeam.CompareOutputData;
import view.ViewManagerModel;

import java.util.ArrayList;

public class ComparePresenter implements CompareOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private  final CompareViewModel compareViewModel;

    public ComparePresenter(ViewManagerModel viewManagerModel, CompareViewModel compareViewModel){
        this.viewManagerModel = viewManagerModel;
        this.compareViewModel = compareViewModel;

    }
    @Override
    public void prepareSuccessView(CompareOutputData outputData) {
        CompareState state = new CompareState(outputData.getTeams());
        compareViewModel.setState(state);
        compareViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("CompareOptions");
        viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String problem) {
        CompareState state = new CompareState(new ArrayList<Team>() {
        });
        compareViewModel.setState(state);
        compareViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("CompareOptions");
        viewManagerModel.firePropertyChanged();

    }


}
