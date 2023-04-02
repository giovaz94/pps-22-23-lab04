package u04lab.polyglot.e2.cell;

import u04lab.polyglot.e2.Pair;
import u04lab.polyglot.e2.cell.type.CellType;

import java.util.List;
import java.util.stream.Stream;

public class CellImpl implements Cell {

    final Pair<Integer, Integer> position;

    final CellType cellType;

    boolean click;

    boolean isFlagged;

    public CellImpl(Pair<Integer, Integer> position, CellType type) {
        this.position = position;
        this.cellType = type;
        this.click = false;
        this.isFlagged = false;
    }

    @Override
    public void click() {
        this.click = true;
    }

    @Override
    public void flag() {
        this.isFlagged = !this.isFlagged();
    }

    @Override
    public List<Pair<Integer, Integer>> getAdjacentPositions() {
        return Stream.of(new Pair<>(-1, -1),
                new Pair<>(-1, 0),
                new Pair<>(-1, 1),
                new Pair<>(0, -1),
                new Pair<>(0, 0),
                new Pair<>(0, 1),
                new Pair<>(1, -1),
                new Pair<>(1, 0),
                new Pair<>(1, 1))
        .map(p -> new Pair<>(this.getPosition().getX() + p.getX(), this.getPosition().getY() + p.getY()))
        .toList();
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return new Pair<>(this.position.getX(), this.position.getY());
    }

    @Override
    public CellType getType() {
        return this.cellType;
    }

    @Override
    public boolean isClicked() {
        return this.click;
    }

    @Override
    public boolean isFlagged() {
        return this.isFlagged;
    }
}
