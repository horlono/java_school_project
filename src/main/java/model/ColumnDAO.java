package model;

import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColumnDAO implements DAO<Column>{
    int idBoard;
    List<Card> cards;
    public ColumnDAO(int idBoard){
        this.idBoard =idBoard;

    }


    @Override
    public Column find(int id) {
        return null;
    }

    @Override
    public void create(Column obj) {
        try {

            String sqlcount= "SELECT COUNT(*) FROM columns WHERE idBoard="+idBoard+";";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sqlcount);
            int position = result.getInt("COUNT(*)");
            String sql = "INSERT INTO columns(idColumn, idBoard, columnName, position) VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1,obj.getId());
            preparedStatement.setInt(2,idBoard);
            preparedStatement.setString(3, obj.getName());
            preparedStatement.setInt(4,position);
            preparedStatement.execute();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void add(Column obj) {

    }

    @Override
    public void updateName(int id,String string) {
        try{

            connect.createStatement().execute("UPDATE columns SET columnName= \""+string+"\""+" WHERE idColumn = "+id+";");

        }catch (SQLException e){e.printStackTrace();}


    }

    @Override
    public void delete(Column obj) {
        try{

            connect.createStatement().execute("DELETE FROM cards WHERE idColumn = "+obj.getId());
            String sqlpos ="SELECT position from columns WHERE idColumn= "+obj.getId()+";";
            Statement stateposcol = connect.createStatement();
            ResultSet resultposcol = stateposcol.executeQuery(sqlpos);
            int pos = resultposcol.getInt("position");
            String sql = "SELECT * from columns WHERE position > "+pos+" AND idBoard = "+idBoard;
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                int newPos = result.getInt("position")-1;
                int idColumn = result.getInt("idColumn");


                connect.createStatement().execute("UPDATE columns SET position="+newPos+" WHERE  idColumn="+idColumn+";");

            }
            String delete = "delete from columns where idColumn= "+obj.getId()+";";
            PreparedStatement deletecol = connect.prepareStatement(delete);
            deletecol.execute();




        }catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public void updatePosition(Column obj, Column obj2) {
        try {

            String col1 = "SELECT position from columns WHERE idColumn=" + obj.getId() + ";";
            String col2 = "SELECT position from columns WHERE idColumn=" + obj2.getId() + ";";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(col1);
            int pos1=result.getInt("position");

            Statement statement2 = connect.createStatement();
            ResultSet result2 = statement2.executeQuery(col2);
            int pos2=result2.getInt("position");
            String update1 ="UPDATE columns SET position="+pos2+" WHERE idColumn= "+obj.getId()+";";
            String update2="UPDATE columns SET position="+pos1+"  WHERE idColumn= "+obj2.getId()+";";
            PreparedStatement preparedStatement1 = connect.prepareStatement(update1);
            preparedStatement1.execute();
            PreparedStatement preparedStatement2 = connect.prepareStatement(update2);
            preparedStatement2.execute();




        }catch (SQLException e){e.printStackTrace();}


    }




    @Override
    public void restore(Column obj, int pos) {
        try{
            this.cards = obj.getCards();
            this.cards.forEach(card -> {
                try {
                    int poscard = cards.indexOf(card);
                    String sqlinsert = "INSERT INTO " +
                            "cards (idCard, idColumn, cardName, position) " +
                            "VALUES(?,?,?,?);";
                    PreparedStatement preparedStatement = connect.prepareStatement(sqlinsert);
                    preparedStatement.setInt(1, card.getId());
                    preparedStatement.setInt(2, obj.getId());
                    preparedStatement.setString(3, card.getName());
                    preparedStatement.setInt(4, poscard);
                    preparedStatement.execute();
                }catch (SQLException e){e.printStackTrace();}
            });
            String sql = "SELECT * from columns WHERE position >= "+pos+";";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                int idColumn = result.getInt("idColumn");
                int oldPos = result.getInt("position");
                connect.createStatement().execute("UPDATE columns " +
                        "SET position="+(++oldPos)+" " +
                        "WHERE idColumn = "+idColumn+";");

            }
            String sqlinsert = "INSERT INTO " +
                "columns(idColumn, idBoard, columnName, position) " +
                "VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sqlinsert);
            preparedStatement.setInt(1,obj.getId());
            preparedStatement.setInt(2,idBoard);
            preparedStatement.setString(3, obj.getName());
            preparedStatement.setInt(4,pos);
            preparedStatement.execute();


        }catch (SQLException e){e.printStackTrace();}



    }

}
