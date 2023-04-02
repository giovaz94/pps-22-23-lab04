package u04lab.polyglot.e2.logic;


import u04lab.polyglot.e2.Pair;
import u04lab.polyglot.e2.logic.state.StateEnum;

import java.util.List;

public interface Logics {
    void click(int x, int y);

    StateEnum getStatus();

    List<Pair<Integer,Integer>> getMines();

    boolean isClicked(Pair<Integer, Integer> position);

    boolean placeFlag(Pair<Integer, Integer> position);

    int numberOfAdjacentMines(Pair<Integer, Integer> position);
}
