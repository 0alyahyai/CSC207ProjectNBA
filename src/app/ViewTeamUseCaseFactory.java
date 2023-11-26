package app;

import use_case.view_team.interface_adapter.ViewTeamViewModel;
import use_case.view_team.view.ViewTeamView;
import view.ViewManagerModel;

public class ViewTeamUseCaseFactory {

    public static ViewTeamView create(ViewTeamViewModel viewTeamViewModel, ViewManagerModel viewManagerModel) {
        return new ViewTeamView(viewTeamViewModel, viewManagerModel);
    }
}
