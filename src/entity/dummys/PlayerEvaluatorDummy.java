package entity.dummys;

import entity.Player;
import use_case.entity_helpers.Stats;
import use_case.entity_helpers.PlayerEvaluator;

import java.util.Random;

public class PlayerEvaluatorDummy implements PlayerEvaluator {
    @Override
    public float evaluatePlayer(Player player) {
        Random random = new Random();
        return random.nextFloat() * 100;
    }
}
