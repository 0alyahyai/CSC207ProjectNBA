package entity.dummys;

import entity.Player;
import use_case.entity_utilities.Stats;
import use_case.entity_utilities.PlayerEvaluator;

import java.util.Random;

public class PlayerEvaluatorDummy implements PlayerEvaluator {
    @Override
    public float evaluatePlayer(Player player, Stats statsBoundary) {
        Random random = new Random();
        return random.nextFloat() * 100;
    }
}
