package app;

import use_case.make_team.player_stats.interface_adapater.PlayerStatsViewModel;
import use_case.make_team.player_stats.view.PlayerStatsView;
import view.ViewManagerModel;

public class PlayerStatsUseCaseFactory {

    public static PlayerStatsView create(PlayerStatsViewModel playerStatsViewModel, ViewManagerModel viewManagerModel) {
        return new PlayerStatsView(playerStatsViewModel, viewManagerModel);
    }
}
