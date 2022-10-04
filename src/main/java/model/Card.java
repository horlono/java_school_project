package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Card implements Comparable<Card> {

    private String name;
    private int idCard;
    private static int generateIdCard=0;
    private int idCoulmn;
    private CardDAO cardDAO;


    public Card(String name) {
        this.name = name;
        ++generateIdCard;
        this.idCard=generateIdCard();
        this.idCoulmn=0;
        this.cardDAO= new CardDAO(idCoulmn);



    }

    public String getName() {
        return name;
    }
    public static int generateIdCard(){return generateIdCard;}
    public void initgenerateIdCard(){ generateIdCard = getId();}

    public void setName(String name) {
        cardDAO.updateName(idCard,name);
        this.name = name;
    }
    public int getId(){return idCard;}
    public void setId(int id){this.idCard=id;}
    public void setIdColumn(int id){this.idCoulmn=id;}

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Card o) {
        return this.name.compareToIgnoreCase(o.name);
    }

}
