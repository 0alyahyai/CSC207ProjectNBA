package entity.dummys;

import entity.entity_helpers.TeamComparatorFactory;

public class TeamComparatorDummyFactory implements TeamComparatorFactory {
    public TeamComparatorDummy create() {
        return new TeamComparatorDummy(new TeamEvaluatorDummy(new PlayerEvaluatorDummy()));
    }
}
