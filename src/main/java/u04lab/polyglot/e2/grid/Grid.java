package u04lab.polyglot.e2.grid;

import u04lab.polyglot.e2.Pair;
import u04lab.polyglot.e2.cell.Cell;

import java.util.List;

public interface Grid {
    void click(Pair<Integer, Integer> position);

    Cell getCell(Pair<Integer, Integer> position);

    List<Pair<Integer, Integer>> getMines();

    boolean hasMine(Pair<Integer, Integer> position);

    boolean isClicked(Pair<Integer, Integer> position);

    int numberOfAdjacentMines(Pair<Integer, Integer> position);

    boolean flag(Pair<Integer, Integer> position);

    int getSize();
}
