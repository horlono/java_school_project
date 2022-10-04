package command;

import javafx.collections.ObservableList;
import model.Board;
import model.Column;
import mvvm.BoardViewModel;

public class RemoveColumn implements Command{

    // Enregistre notre colonne et ses cartes
    private Column c1;
    private Board board;
    //Index initiale de cette colonne
    int index;
    private ObservableList<Column> colonnes;

    public RemoveColumn(Board board, Column c1, int index, ObservableList<Column> colonnes){
        this.board= board;
        this.c1 = c1;
        this.index = index;
        this.colonnes = colonnes;

    }
    @Override
    public void execute() {
        board.removeColumn(c1);
        colonnes.setAll(board.getColumn());


    }

    @Override
    public void undo() {
        board.restoreColumn(c1,index);
        colonnes.setAll(board.getColumn());



    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String getName() {
        return "Supprimer la colonne "+c1.getName();
    }
}
