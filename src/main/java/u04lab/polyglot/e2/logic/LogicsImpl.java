package u04lab.polyglot.e2.logic;


import u04lab.polyglot.e2.Pair;
import u04lab.polyglot.e2.cell.Cell;
import u04lab.polyglot.e2.grid.Grid;
import u04lab.polyglot.e2.grid.GridImpl;
import u04lab.polyglot.e2.logic.state.StateEnum;

import java.util.List;

import static u04lab.polyglot.e2.logic.state.StateEnum.*;

public class LogicsImpl implements Logics {

    private StateEnum gameStatus;

    private final Grid grid;

    public LogicsImpl(int size, int minesNumber) {
        this.gameStatus = IN_GAME;
        this.grid = new GridImpl(size, minesNumber);
        System.out.println(this.grid.getMines());
    }

    @Override
    public void click(int x, int y) {
        final Pair<Integer,Integer> clickedPosition = new Pair<>(x,y);
        this.grid.click(clickedPosition);
        if (this.grid.hasMine(clickedPosition)) {
            this.gameStatus = GAME_OVER;
        }
        this.checkVictory();
    }

    @Override
    public StateEnum getStatus() {
        return this.gameStatus;
    }

    @Override
    public List<Pair<Integer, Integer>> getMines() {
        return this.grid.getMines();
    }

    @Override
    public boolean isClicked(Pair<Integer, Integer> position) {
        return this.grid.isClicked(position);
    }

    @Override
    public boolean placeFlag(Pair<Integer, Integer> position) {
        boolean flagPlaced = this.grid.flag(position);
        this.checkVictory();
        System.out.println(this.getStatus());
        return flagPlaced;
    }

    @Override
    public int numberOfAdjacentMines(Pair<Integer, Integer> position) {
        return this.grid.numberOfAdjacentMines(position);
    }

    private void checkVictory() {
        if(this.getStatus().equals(GAME_OVER)) {
            return;
        }

        boolean areAllFlagged = this.grid.getMines().stream()
                .map(this.grid::getCell)
                .allMatch(Cell::isFlagged);

        boolean areAllClicked = true;
        List<Pair<Integer, Integer>> minesPosition = this.grid.getMines();
        for (int i = 0; i < this.grid.getSize(); i++) {
            for (int j = 0; j < this.grid.getSize(); j++) {
                Pair<Integer, Integer> position = new Pair<>(i, j);
                if(!this.isClicked(position) && !minesPosition.contains(position)) {
                    areAllClicked = false;
                }
            }
        }

        this.gameStatus = (areAllClicked || areAllFlagged) ? WIN : IN_GAME;
    }
}
