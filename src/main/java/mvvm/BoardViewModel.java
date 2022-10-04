package mvvm;
import command.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import view.CommanManager;

public class BoardViewModel {
    private final Board board;
    private final StringProperty boardname;
    public CommanManager commanManager;
    private static int generateNewone=0;
    public BoardViewModel(Board board) {
        this.board = board;
        this.boardname = new SimpleStringProperty(board.getName());
        this.commanManager=CommanManager.getInstance();
        configData();

    }
    public StringProperty boardNameProperty(){return boardname;}
    private final ObservableList<Card>
            cards = FXCollections.observableArrayList();
    private final ObservableList<Column> colonnes = FXCollections.observableArrayList();


    public void configData(){
        cards.setAll(board.getCards());
        colonnes.setAll(board.getColumn());
    }

    public void setBoardname(String newname){
        board.setName(newname);
        boardname.setValue(board.getName());
    }

    public static int generateNewone(){return generateNewone;}

    public SimpleListProperty<Column> ColumnProperty(){return new SimpleListProperty<>(colonnes);}
    public int getColumn(Column c1){
      return colonnes.indexOf(c1);
    }

    public void newColumn(){
        ++generateNewone;
        Column c1 = new Column("New Column "+generateNewone());
        System.out.println(c1.getId());
        commanManager.execute(new NewColumn(board,c1,colonnes));

    }
    public void changeColumnName(Column column,String oldname,String newName){
        commanManager.execute(new EditNameColumn(board,column,oldname,newName,colonnes));
    }


    public void setCardColumnNext(Column c1,Card card1){commanManager.execute(new MoveRightCard(board,c1,card1,colonnes));}

    public Board getBoard() {
        return board;
    }

    public void setCardColumnPre(Column c1, Card card1){commanManager.execute(new MoveLeftCard(board,c1,card1,colonnes));}

    public Column lastColumn(){
        return colonnes.get(colonnes.size()-1);
    }
    public Column firstColumn(){
        return colonnes.get(0);
    }
    public void changeBoardName(String oldname, String newname){
        commanManager.execute(new EditNameBoard(board,newname,oldname));
    }


    public void removeColonne(Column c1){
        board.removeColumn(c1);
        commanManager.execute(new RemoveColumn(board,c1,getColumn(c1),colonnes));
    }
    public void moveRight(Column c1){
        commanManager.execute(new MoveRightColumn(board,c1,colonnes));
    }



    public void moveLeft(Column c1){
        commanManager.execute(new MoveLeftColumn(board,c1,colonnes));
    }

}



