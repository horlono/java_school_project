package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardDAO implements DAO<Card> {
    int idColumn;
    public CardDAO(int idColumn){
        this.idColumn=idColumn;
    }
    @Override
    public Card find(int id) {
            return null;
    }

    @Override
    public void create(Card obj) {
        try {

            //la postion adapté
            String sqlcount= "SELECT COUNT(*) FROM cards WHERE idColumn="+idColumn+";";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sqlcount);
            int position = result.getInt("COUNT(*)");
            String sql = "INSERT INTO cards(idCard, idColumn, cardName, position) VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1,obj.getId());
            preparedStatement.setInt(2,idColumn);
            preparedStatement.setString(3, obj.getName());
            preparedStatement.setInt(4,position);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void add(Card obj) {
        try {
            //mettre en derniere position
            String sqlcount= "SELECT COUNT(*) FROM cards WHERE idColumn="+idColumn+";";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sqlcount);
            int position = result.getInt("COUNT(*)");
            String sql = "INSERT INTO cards(idCard, idColumn, cardName, position) VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1,obj.getId());
            preparedStatement.setInt(2,idColumn);
            preparedStatement.setString(3, obj.getName());
            preparedStatement.setInt(4,position);
            preparedStatement.execute();
        }catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public void updateName(int id,String string) {
        try{
            connect.createStatement().execute("UPDATE cards SET cardName= \""+string+"\" WHERE idCard = "+id);
        }catch (SQLException e){e.printStackTrace();}

    }



    @Override
    public void delete(Card obj) {

        try{
            String sqlpos ="SELECT position from cards WHERE idCard = "+obj.getId()+";";
            Statement statepos = connect.createStatement();
            ResultSet resultpos = statepos.executeQuery(sqlpos);
            int pos = resultpos.getInt("position");

            String sql = "SELECT * from cards WHERE position > "+pos+" AND idColumn = "+idColumn;
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                int newPos = result.getInt("position")-1;
                int idCard = result.getInt("idCard");

                connect.createStatement().execute("UPDATE cards SET position="+newPos+" WHERE  idCard ="+idCard+";");

            }

            connect.createStatement().execute("delete from cards where idCard= "+obj.getId()+";");

        }catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public void updatePosition(Card obj, Card obj2) {
        try {
            String card1 = "SELECT position from cards WHERE idCard=" + obj.getId() + ";";
            String card2 = "SELECT position from cards WHERE idCard=" + obj2.getId() + ";";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(card1);
            int pos1=result.getInt("position");
            Statement statement2 = connect.createStatement();
            ResultSet result2 = statement2.executeQuery(card2);
            int pos2=result2.getInt("position");
            connect.createStatement().execute("UPDATE cards SET position="+pos2+" WHERE idCard= "+obj.getId()+";");
            connect.createStatement().execute("UPDATE cards SET position="+pos1+"  WHERE idCard= "+obj2.getId()+";");


        }catch (SQLException e){e.printStackTrace();}

    }




    @Override
    public void restore(Card obj, int pos) {
        try{

            String sql = "SELECT * from cards WHERE position >= "+pos+";";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                int newPos = result.getInt("position")+1;
                int idCard = result.getInt("idCard");
                connect.createStatement().execute("UPDATE cards SET position="+newPos+" WHERE idCard = "+idCard+" " +
                        "AND  idColumn= "+ idColumn +";");
            }
            String sqlinsert = "INSERT INTO " +
                    "cards (idCard, idColumn, cardName, position) " +
                    "VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sqlinsert);
            preparedStatement.setInt(1,obj.getId());
            preparedStatement.setInt(2,idColumn);
            preparedStatement.setString(3, obj.getName());
            preparedStatement.setInt(4,pos);
            preparedStatement.execute();

        }catch (SQLException e){e.printStackTrace();}

    }
}
