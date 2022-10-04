package command;

import javafx.collections.ObservableList;
import model.Board;
import model.Column;
import mvvm.BoardViewModel;

public class EditNameColumn implements Command{
    private String oldName;
    private String newName;
    private BoardViewModel boardViewModel;
    private Column column;
    private Board board;
    private ObservableList<Column> colonnes;
    public EditNameColumn(Board board, Column column, String oldName, String newName, ObservableList<Column> colonnes){
                this.column = column;
                this.board = board;
                this.oldName = oldName;

                this.newName = newName;

                this.colonnes = colonnes;
    }
    @Override
    public void execute() {
        column.setName(newName);
        colonnes.setAll(board.getColumn());

    }

    @Override
    public void undo() {
        column.setName(oldName);
        colonnes.setAll(board.getColumn());
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String getName() {
        return "Changer le nom de la colonne "+oldName+" en "+newName;
    }
}
