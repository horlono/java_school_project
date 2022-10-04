package model;

import javafx.beans.property.IntegerProperty;

import java.util.*;



public class Board {

    private String name;

    private int idBoard;
    private static int generateIdBoard =0;
    public Board(String name){
        this.name = name;
        ++generateIdBoard;
        this.idBoard= generateIdBoard();
        this.dbColumn= new ColumnDAO(idBoard);
        this.boardDAO = new BoardDAO();


    }
    private ColumnDAO dbColumn;
    private BoardDAO boardDAO;

    private final Map<Column,Card> boardMap = new HashMap<>();
    private final List<Card> cards = new ArrayList<>();
    private final List<Column> columns = new ArrayList<>();
    public static  int generateIdBoard(){return generateIdBoard; }


    public List<Card> getCards() { return Collections.unmodifiableList(cards);}

    public List<Column> getColumn() { return Collections.unmodifiableList(columns);}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        boardDAO.updateName(getId(),name);
        this.name = name;
    }

    public int getId() {
        return idBoard;
    }


    public boolean addColumn(Column column){

        return columns.add(column);}
    public boolean removeCardFromColumn(Card card, Column column){return column.removeCard(card);}
    public boolean removeColumn(Column column){
        dbColumn.delete(column);
        return columns.remove(column);}

    public void restoreColumn(Column c1 , int index){
        dbColumn.restore(c1,index);
        columns.add(index,c1);}
    public void moveRight(Column c1){
        int index = columns.indexOf(c1);
        Column c2 = columns.get(index+1);
        dbColumn.updatePosition(c1,c2);
        Collections.swap(columns,columns.indexOf(c1),columns.indexOf(c2));}
    public void moveLeft(Column c1){
            int index = columns.indexOf(c1);

            Column c2 = columns.get(index-1);

            dbColumn.updatePosition(c1,c2);

        Collections.swap(columns,columns.indexOf(c1),columns.indexOf(c2));}
    public void newColumn(Column c1){
        dbColumn.create(c1);
        columns.add(c1);}









}
