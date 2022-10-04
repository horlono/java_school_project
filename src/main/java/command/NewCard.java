package command;

import javafx.collections.ObservableList;
import model.Card;
import model.Column;
import mvvm.BoardViewModel;
import mvvm.ColumnViewModel;


public class NewCard implements Command{
    public ObservableList<Card> cards;
    private Column column;
    private Card card;


    public NewCard(Card card,Column column, ObservableList<Card> cards){

        this.column = column;
        this.card=card;
        this.cards = cards;




    }
    @Override
    public void execute() {
        column.newCard(card);
        cards.setAll(column.getCards());



    }

    @Override
    public void undo() {

        column.removeCard(card);
        cards.setAll(column.getCards());
    }

    @Override
    public void redo() {
        execute();

    }

    @Override
    public String getName() {
        return "Créer une nouvelle carte dans "+column.getName();
    }
}
