package u04lab.polyglot.e2.cell;

import u04lab.polyglot.e2.Pair;
import u04lab.polyglot.e2.cell.type.CellType;

import java.util.List;

public interface Cell {

    void click();

    void flag();

    List<Pair<Integer,Integer>> getAdjacentPositions();

    Pair<Integer, Integer> getPosition();

    CellType getType();

    boolean isClicked();

    boolean isFlagged();
}
