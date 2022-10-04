package command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Card;
import model.Column;
import mvvm.ColumnViewModel;

public class RemoveCard implements Command{
    private ColumnViewModel columnViewModel;
    private Card card1;
    private Card card2;
    private Column column;
    private int index;
    public ObservableList<Card> cards;

    public RemoveCard(Column column, Card card1,Card card2,ObservableList<Card> cards){
        this.column=column;
       this.card1 = card1;
       this.index = column.getCards().indexOf(card1);
       this.cards=cards;
    }
    @Override
    public void execute() {
        column.removeCard(card1);
        cards.setAll(column.getCards());
    }

    @Override
    public void undo() {

        column.restoreCard(card1,index);
        cards.setAll(column.getCards());

    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String getName() {
        return "Supprimer la carte "+card1.getName();
    }
}
