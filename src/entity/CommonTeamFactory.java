package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonTeamFactory implements TeamFactory{
    @Override
    public Team createTeam(String teamName, List<Player> players) {
        return new CommonTeam(teamName, 7, players);
    }

    @Override
    public Team createMockTeam() {
        Player p1 = new CommonPlayer("John", 1);
        Player p2 = new CommonPlayer("Alex", 2);
        Player p3 = new CommonPlayer("Bob", 3);
        Player p4 = new CommonPlayer("Sam", 4);
        Player p5 = new CommonPlayer("Jerry", 5);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);

        return new CommonTeam("Faith's Followers", 10, players);
    }
}
