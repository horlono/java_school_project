package command;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;
import model.DAO;
import mvvm.BoardViewModel;
import mvvm.ColumnViewModel;


public class MoveRightCard implements Command {
    private ObservableList<Column> colonnes;
    private Card card1;
    private StringProperty columnName;
    private Column next;
    private Board board;
    private Card card2;
    private int index;
    private Column column;
    private Column column2;
    private int indexCol;

    public MoveRightCard(Board board ,Column column, Card card1, ObservableList<Column> colonnes){
        this.card1 = card1;
        this.column = column;
        int indexc2= board.getColumn().indexOf(column);
        this.column2 = board.getColumn().get(indexc2+1);
        this.board = board;
        this.index = column.getCards().indexOf(card1);
        this.colonnes = colonnes;



    }
    @Override
    public void execute() {
        column.removeCard(card1);
        column2.addCard(card1);
        colonnes.setAll(board.getColumn());

    }

    @Override
    public void undo() {
        column2.removeCard(card1);
        column.restoreCard(card1,index);
        colonnes.setAll(board.getColumn());

    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String getName() {


        return "Deplacement de la carte "+card1.getName()+" de la colonne "+column.getName()+" vers la colonne "+column2.getName();
    }
}
