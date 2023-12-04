package entity.dummys;

import entity.Player;
import entity.entity_helpers.PlayerEvaluator;

import java.util.Random;

public class PlayerEvaluatorDummy implements PlayerEvaluator {
    @Override
    public float evaluatePlayer(Player player) {
        Random random = new Random();
        return random.nextFloat() * 100;
    }
}
