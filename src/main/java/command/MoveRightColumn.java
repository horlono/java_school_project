package command;

import javafx.collections.ObservableList;
import model.Board;
import model.Column;
import mvvm.BoardViewModel;

public class MoveRightColumn implements Command {
    private BoardViewModel boardViewModel;
    private Column c1;
    private Board board;
    public ObservableList<Column> colonnes;
    public MoveRightColumn(Board board , Column c1, ObservableList<Column> colonnes){
        this.board = board;
        this.c1 = c1;
        this.colonnes=colonnes;
    }

    @Override
    public void execute() {
       board.moveRight(c1);
       colonnes.setAll(board.getColumn());

    }

    @Override
    public void undo() {
        board.moveLeft(c1);
        colonnes.setAll(board.getColumn());
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String getName() {
        return "Deplacement de la colonne "+c1.getName()+" vers la droite";
    }
}
