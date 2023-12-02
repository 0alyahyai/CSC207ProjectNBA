package entity.dummys;

import entity.Player;
import entity.Stats;

import java.util.Random;

public class PlayerEvaluatorDummy implements entity.PlayerEvaluator{
    @Override
    public float evaluatePlayer(Player player, Stats statsBoundary) {
        Random random = new Random();
        return random.nextFloat() * 100;
    }
}
