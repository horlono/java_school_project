package command;

import javafx.collections.ObservableList;
import model.Board;
import model.Column;
import mvvm.BoardViewModel;

public class NewColumn implements Command{
    private Board board;
    private Column column;
    private BoardViewModel boardViewModel;
    private ObservableList<Column> colonnes;
    public NewColumn(Board board, Column column, ObservableList<Column> colonnes){
        this.board = board;
        this.column=column;
        this.colonnes=colonnes;
    }
    @Override
    public void execute() {
        board.newColumn(column);
        colonnes.setAll(board.getColumn());
    }

    @Override
    public void undo() {
       board.removeColumn(column);
        colonnes.setAll(board.getColumn());

    }

    @Override
    public void redo() {
        execute();

    }

    @Override
    public String getName() {
        return "Créer une nouvelle colonne ";
    }
}
