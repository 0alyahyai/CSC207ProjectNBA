package entity;

import java.util.List;

public interface TeamFactory {
    Team createTeam(String teamName, List<Player> players);

    Team createMockTeam();
}
