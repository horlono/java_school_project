package mvvm;
import command.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Column;
import model.Card;
import model.Board;
import view.CommanManager;

public class ColumnViewModel {
    public CommanManager commanManager;
    public BoardViewModel boardViewModel;
    public Card card;
    public Board board;
    public Column column;
    public StringProperty columnName;
    public StringProperty cardName;
    public ObservableList<Card> cards = FXCollections.observableArrayList();
    private static int generateNewone=0;



    public ColumnViewModel(Column column,BoardViewModel boardViewModel){
        this.boardViewModel = boardViewModel;
        this.column = column;
        cards.setAll(column.getCards());
        columnName= new SimpleStringProperty(column.getName());
        this.commanManager= CommanManager.getInstance();




    }
    public StringProperty columnNameProperty(){

        return columnName;
    }
    public static int generateNewone(){return generateNewone;}
    public void changeNameCard(Card card,String oldName,String newName){commanManager.execute(new EditNameCard(column,card,oldName,newName,cards));}
    public void setColumnName(String oldname,String newname){
        boardViewModel.changeColumnName(column,oldname,newname);}
    public boolean setLastArrowProperty(){
        if(column==this.boardViewModel.lastColumn())
            return true;

        return false;
    }
    public boolean setFirstArrowProperty(){
        if(column==this.boardViewModel.firstColumn())
            return true;

        return false;
    }

    public SimpleListProperty<Card> CardProperty(){return new SimpleListProperty<>(cards);}

    public int getIndexCard(Card card1){
        return cards.indexOf(card1);
    }
    public Card getFirstCard(){return cards.get(0);}
    public IntegerProperty nbCard = new SimpleIntegerProperty();

    public Column getColumn() {
        return column;
    }



    public Card getCardNext(Card card1){
        int index = cards.indexOf(card1);
        if(index == cards.size()-1)
            return cards.get(index);
        return cards.get(index+1);
    }

    public Card getPreCard(Card card1){
        int index = cards.indexOf(card1);
        return cards.get(index-1);

    }
    public Card getLastCard(){
        return cards.get(cards.size()-1);
    }

    public void removeCard(Card card1){
        Card card2 = getCardNext(card1);
        commanManager.execute(new RemoveCard(column,card1,card2,cards));

    }
    public void newCard(){
        ++generateNewone;
        Card cd = new Card("New Card "+generateNewone());
        commanManager.execute(new NewCard(cd,column,cards));

    }


    public void moveDown(Card card1){
        Card card2 = getCardNext(card1);
       commanManager.execute(new MoveDownCard(column,card1,card2,cards));
    }
    public void moveUp(Card card1){
        Card card2 = getPreCard(card1);
        commanManager.execute(new MoveUpCard(column,card1,card2,cards));
    }

    public void setCardnextColumn(Card card1){
        boardViewModel.setCardColumnNext(column,card1);
    }
    public void setCardPreColumn(Card card1){
        boardViewModel.setCardColumnPre(column,card1);
    }









}

