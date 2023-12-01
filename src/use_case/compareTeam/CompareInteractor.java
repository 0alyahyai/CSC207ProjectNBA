package use_case.compareTeam;

import entity.Team;

import java.util.List;

public class CompareInteractor implements CompareInputBoundary{

    public CompareOutputBoundary compareOutputBoundary;
    public  CompareDataAccessInterface compareDataAccessInterface;




    public CompareInteractor(CompareOutputBoundary compareOutputBoundary,
                             CompareDataAccessInterface compareDataAccessInterface){
        this.compareOutputBoundary = compareOutputBoundary;
        this.compareDataAccessInterface = compareDataAccessInterface;
    }


    @Override
    public void execute() {
        //Here we need to go to the csv file to get of all of the information of all the teams (EXEECPT OURS)
        List<Team> teams = compareDataAccessInterface.geteams();
        CompareOutputData compareOutputData = new CompareOutputData(teams );




        //nOW THAT WE HAVE THE DATA, WE RETURN IT TO THE GREEN PART of the egine.
        // In this case, we return it to the presenter. To do that, the use case interactor must have an attribute of
        // presenter... but, that can not happen by SOLID. So it has an attribute of type output boundary that is an
        // abstraction of presenter.
        if (!teams.isEmpty()) {
            compareOutputBoundary.prepareSuccessView(compareOutputData);
        } else{

            String problemString = "There are no teams other than yours." ;
            compareOutputBoundary.prepareFailView(problemString);
        }


    }
}
