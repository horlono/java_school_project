package command;

import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import model.Card;
import model.Column;
import mvvm.ColumnViewModel;

public class MoveUpCard implements Command{
    private ColumnViewModel columnViewModel;
    private Card card1;
    private Column column;
    private Card card2;
    private ObservableList<Card> cards;
    public MoveUpCard(Column column, Card card1,Card card2,ObservableList<Card> cards){
        this.column=column;
        this.card1 = card1;
        this.card2=card2;
        this.cards=cards;
    }
    @Override
    public void execute() {
        column.swapCard(card1,card2);
        cards.setAll(column.getCards());
    }

    @Override
    public void undo() {
        column.swapCard(card1,card2);
        cards.setAll(column.getCards());
    }

    @Override
    public void redo() {
        execute();

    }

    @Override
    public String getName() {
        return "Deplacement de la carte "+card1.getName()+" vers le haut";
    }
}
