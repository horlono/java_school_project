package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDAO implements DAO<Board> {
private IntegerProperty idcolumn(){return new SimpleIntegerProperty();}
private Board board;
    @Override
    public Board find(int id) {
        try{
            String sql = "SELECT boardName FROM boards WHERE idBoard = "+id+";";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);
            this.board = new Board(result.getString("boardName"));
            String sqlcol = "SELECT * FROM columns WHERE idBoard="+board.getId()+" ORDER  BY position ASC;";
            Statement statementcol = connect.createStatement();
            ResultSet resultcol = statementcol.executeQuery(sqlcol);

            while (resultcol.next()){
                int idColumn = resultcol.getInt("idColumn");
                String nameColumn = resultcol.getString("columnName");
                int position = resultcol.getInt("position");
                Column column = new Column(nameColumn);
                column.setId(idColumn);
                column.setIdBoard(board.getId());
                column.initgenerateIdColumn();

                String sqlcard = "SELECT * FROM cards WHERE idColumn = "+column.getId()+" ORDER  BY position ASC;";
                Statement statementcard = connect.createStatement();
                ResultSet resultcard = statementcard.executeQuery(sqlcard);


                while (resultcard.next()){
                    int idCard = resultcard.getInt("idCard");
                    String nameCard = resultcard.getString("cardName");
                    Card card = new Card(nameCard);
                    card.setId(idCard);
                    card.setIdColumn(column.getId());
                    column.initAdd(card);
                    card.initgenerateIdCard();


                }

                this.board.addColumn(column);}

        }catch (SQLException e){e.printStackTrace();}


        return board;
    }

    @Override
    public void create(Board obj) {}

    @Override
    public void add(Board obj) {

    }

    @Override
    public void updateName(int id,String string) {
        try{
            connect.createStatement().execute("UPDATE boards SET boardName= \""+string+"\"  WHERE idBoard = "+id);
        }catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public void delete(Board obj) {}

    @Override
    public void updatePosition(Board obj, Board obj2) {}



    @Override
    public void restore(Board obj, int pos) {}
}
