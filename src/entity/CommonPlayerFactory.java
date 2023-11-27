package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonPlayerFactory implements PlayerFactory{
    @Override
    public Player create(String name, int id) {
        return new CommonPlayer(name, id);
    }

    @Override
    public Player createMockPlayer() {
        int randomID = (int)(Math.random() * (100000));
        return create("Barrack Obama", randomID);
    }

    @Override
    public List<Player> generateMockTeam() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            players.add(createMockPlayer());
        }
        return players;
    }
}
