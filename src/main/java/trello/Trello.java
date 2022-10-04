package trello;
import javafx.application.Application;
import javafx.stage.Stage;

import model.*;

//import java.awt.event.MouseEvent;

import view.TrelloView;

import mvvm.BoardViewModel;




public class Trello extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        DBTrello dbTrello= new DBTrello();
        BoardDAO boardDAO = new BoardDAO();
        Board board = boardDAO.find(1);
        BoardViewModel boardViewModel = new BoardViewModel(board);

        TrelloView trelloView = new TrelloView(stage,boardViewModel,board);
        stage.setWidth(800);
        stage.setHeight(500);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}
