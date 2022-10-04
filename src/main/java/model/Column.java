package model;

import java.util.*;

public class Column implements Comparable<Column>{

    private  String name;
    private int idColumn;
    public ColumnDAO columnDAO;
    private int idBoard;
    private static int generateIdColumn=0;
    private final List<Card> putedCards = new ArrayList<>();



    //Methodes package

    public Column(String name){
        this.name =name;
        ++generateIdColumn;
        this.idColumn=generateIdColumn();
        this.cardDAO = new CardDAO(idColumn);
        this.idBoard=0;
        this.columnDAO= new ColumnDAO(idBoard);

    }
    private CardDAO cardDAO;
    public static int generateIdColumn(){return generateIdColumn;}
    public void initgenerateIdColumn(){generateIdColumn = getId();}
    public boolean initAdd(Card card){
        return putedCards.add(card);
    }
    public boolean addCard(Card card){
        cardDAO.add(card);
        return putedCards.add(card);}
    public boolean newCard(Card card){
        cardDAO.create(card);
        return putedCards.add(card); }
    public void restoreCard(Card card1,int index){
        cardDAO.restore(card1,index);
        putedCards.add(index,card1);}
    public boolean removeCard(Card card){
        cardDAO.delete(card);
        return putedCards.remove(card);}
    public void swapCard(Card c1,Card c2){
        cardDAO.updatePosition(c1,c2);
        Collections.swap(putedCards,putedCards.indexOf(c1),putedCards.indexOf(c2));
    }



    //Interface publique

    public List<Card> getCards() { return Collections.unmodifiableList(putedCards); }

    public String getName() {return name;}
    public void setName(String name) {
        columnDAO.updateName(idColumn,name);
        this.name = name;
    }

    public int getId() {return idColumn;}
    public void setId(int id){this.idColumn=id;}
    public void setIdBoard(int id){this.idBoard=id;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;

        Column column = (Column) o;

        return name != null ? name.equals(column.name) : column.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(Column o) { return this.name.compareToIgnoreCase(o.name); }

    @Override
    public String toString(){return name;}




    }

