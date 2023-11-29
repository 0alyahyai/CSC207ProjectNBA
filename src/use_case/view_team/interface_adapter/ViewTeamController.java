package use_case.view_team.interface_adapter;

import use_case.view_team.ViewTeamInputBoundary;
import use_case.view_team.ViewTeamInputData;
import use_case.view_team.ViewTeamOutputBoundary;

public class ViewTeamController {

    //
    final ViewTeamInputBoundary viewTeamInteractor;

    public ViewTeamController(ViewTeamInputBoundary viewTeamInteractor) {
        this.viewTeamInteractor = viewTeamInteractor;
    }

    public void execute() {
        viewTeamInteractor.execute();
    }
}
