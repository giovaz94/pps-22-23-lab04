package u04lab.polyglot.e2.grid;

import u04lab.polyglot.e2.Pair;
import u04lab.polyglot.e2.cell.Cell;
import u04lab.polyglot.e2.cell.MineCellImpl;
import u04lab.polyglot.e2.cell.NormalCell;
import u04lab.polyglot.e2.cell.NormalCellImpl;
import u04lab.polyglot.e2.cell.type.CellType;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GridImpl implements Grid {

    private final HashMap<Pair<Integer, Integer>, Cell> grid= new HashMap<>();
    private final Random random;
    private final int gridSize;
    private final int minesNumber;


    public GridImpl(final int gridSize, final int minesNumber) {
        this.gridSize = gridSize;
        this.random = new Random();
        this.minesNumber = minesNumber;
        this.initGrid();
        for(int i = 0; i < this.minesNumber; i++) {
            Pair<Integer, Integer> position = this.generateRandomPositon();
            this.grid.replace(position, new MineCellImpl(position));
        }
    }

    private void initGrid() {
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                final Cell cell = new NormalCellImpl(new Pair<Integer, Integer>(i,j));
                this.grid.put(cell.getPosition(), cell);
            }
        }
    }

    @Override
    public void click(Pair<Integer, Integer> position) {
        if(this.isInGrid(position) && this.grid.get(position).getType().equals(CellType.NORMAL_CELL_TYPE)) {
            final NormalCell targetCell = (NormalCell) this.grid.get(position);
            if(!targetCell.isClicked()) {
                targetCell.click();
                if(targetCell.numberOfAdjacentMines(this.getMines()) == 0) {
                    targetCell.getAdjacentPositions().forEach(this::click);
                }
            }
        }
    }

    @Override
    public Cell getCell(Pair<Integer, Integer> position) {
        if(!this.isInGrid(position)) {
            throw new IndexOutOfBoundsException();
        }
        return this.grid.get(position);
    }

    @Override
    public List<Pair<Integer, Integer>> getMines() {
        return this.grid.values().stream()
                .filter(cell -> cell.getType().equals(CellType.MINE_CELL_TYPE))
                .map(Cell::getPosition)
                .toList();
    }

    @Override
    public boolean hasMine(Pair<Integer, Integer> position) {
        return this.getMines().contains(position);
    }

    @Override
    public boolean isClicked(Pair<Integer, Integer> position) {
        return this.grid.get(position).isClicked();
    }

    @Override
    public int numberOfAdjacentMines(Pair<Integer, Integer> position) {
        if(this.grid.get(position).getType().equals(CellType.NORMAL_CELL_TYPE)) {
            NormalCell cell = (NormalCell) this.grid.get(position);
            return cell.numberOfAdjacentMines(this.getMines());
        }
        return 0;
    }

    @Override
    public boolean flag(Pair<Integer, Integer> position) {
        Cell cell = this.getCell(position);
        if(!cell.isClicked() && this.checkFlagNumber()) {
            cell.flag();
            return cell.isFlagged();
        }
        return false;
    }

    @Override
    public int getSize() {
        return this.gridSize;
    }

    private Pair<Integer,Integer> generateRandomPositon() {
        int x = this.random.nextInt(this.gridSize);
        int y = this.random.nextInt(this.gridSize);
        Pair<Integer, Integer> position = new Pair<>(x, y);
        return this.getMines().contains(position) ? this.generateRandomPositon() : position;
    }

    private boolean isInGrid(Pair<Integer, Integer> position) {
        return this.grid.containsKey(position);
    }

    private boolean checkFlagNumber() {
        return this.grid.values().stream().filter(Cell::isFlagged).count() < this.minesNumber;
    }
}
