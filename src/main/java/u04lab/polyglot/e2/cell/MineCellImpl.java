package u04lab.polyglot.e2.cell;

import u04lab.polyglot.e2.Pair;
import u04lab.polyglot.e2.cell.type.CellType;

public class MineCellImpl extends CellImpl {

    public MineCellImpl(Pair<Integer, Integer> position) {
        super(position, CellType.MINE_CELL_TYPE);
    }

    @Override
    public boolean isClicked() {
        return false;
    }
}
