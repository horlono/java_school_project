package mvvm;
import model.Card;
import javafx.beans.property.*;
import model.Column;

public class CardViewModel {
    public Card card;
    public StringProperty cardName;
    public ColumnViewModel columnViewModel;
    public int index;
    public CardViewModel(Card card,ColumnViewModel columnViewModel){
        this.card = card;
        cardName = new SimpleStringProperty(card.getName());
        this.columnViewModel = columnViewModel;
        this.index = columnViewModel.getIndexCard(card);

    }

    public StringProperty cardNameProperty(){
        return cardName;
    }
    public void setCardName(String oldName,String newName){ columnViewModel.changeNameCard(card,oldName,newName);}
    public boolean setFirstArrowCardProperty(){
        if(card ==columnViewModel.getFirstCard())
            return true;
        return false;
    }
    public boolean setLastArrowCardProperty(){
        if(card==columnViewModel.getLastCard())
            return true;
        return false;
    }
    public boolean leftArrowProperty(){
        return columnViewModel.setFirstArrowProperty();
    }
    public boolean rightArrowProperty(){
        return columnViewModel.setLastArrowProperty();
    }


}
