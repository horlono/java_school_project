package command;

import javafx.collections.ObservableList;
import model.Card;
import model.Column;
import mvvm.BoardViewModel;
import mvvm.ColumnViewModel;

public class EditNameCard implements Command{
    private String oldName;
    private String newName;
    private Column column;
    private Card card;
    private ObservableList<Card> cards;
    public EditNameCard(Column column, Card card, String oldName, String newName,ObservableList<Card> cards){
        this.column=column;
        this.oldName = oldName;
        this.newName = newName;
        this.card = card;
        this.cards=cards;
    }
    @Override
    public void execute() {
        card.setName(newName);
        cards.setAll(column.getCards());
    }

    @Override
    public void undo() {
        card.setName(oldName);
        cards.setAll(column.getCards());

    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String getName() {
        return "Changer le nom de la carte "+oldName+" en "+newName;
    }
}
