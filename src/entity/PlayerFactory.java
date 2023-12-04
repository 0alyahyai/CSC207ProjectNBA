package entity;

import entity.Player;

import java.util.List;

public interface PlayerFactory {
    Player create(String name, int id);

    Player createMockPlayer();

    /**
     *
     * @return a List of five players
     */
    List<Player> generateMockTeam();
}
