package view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Board;
import model.Card;
import model.Column;
import mvvm.BoardViewModel;
import mvvm.CardViewModel;
import mvvm.ColumnViewModel;

public class EditLabelView extends VBox {
    public BoardViewModel boardViewModel;
    private ColumnViewModel columnViewModel;
    private CardViewModel cardViewModel;
    private Board board;
    private Card card;
    private Column column;
    private Label nameColumn = new Label();
    private Label nameCard = new Label();
    private Label nameBoard = new Label();

     EditLabelView( ColumnViewModel columnViewModel, Column column){

        this.columnViewModel = columnViewModel;
        this.column=column;
        this.nameColumn.textProperty().bind(columnViewModel.columnNameProperty());
        this.getChildren().addAll(nameColumn);
        nameColumn.setOnMouseClicked(e->{if(e.getButton().equals(MouseButton.PRIMARY)){changeNameColumn();}});
    }
     EditLabelView(CardViewModel cardViewModel, Card card){
        this.cardViewModel=cardViewModel;
        this.card = card;
        this.nameCard.textProperty().bind(cardViewModel.cardNameProperty());
        this.getChildren().add(nameCard);
        nameCard.setOnMouseClicked(e->{if(e.getButton().equals(MouseButton.PRIMARY)){changeNameCard();}});
    }
    EditLabelView(BoardViewModel boardViewModel, Board board){
         this.boardViewModel=boardViewModel;
         this.board=board;
         this.nameBoard.textProperty().bind(boardViewModel.boardNameProperty());
         this.getChildren().addAll(nameBoard);
         changeNameBoard();
    }
    public void changeNameBoard(){

        nameBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String oldname = nameBoard.getText();
                TextField oldLabel = new TextField(nameBoard.getText());
                EditLabelView.super.getChildren().remove(0);
                EditLabelView.super.getChildren().add(oldLabel);
                oldLabel.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                            Label newname = new Label(oldLabel.getText());
                            EditLabelView.super.getChildren().remove(0);
                            boardViewModel.changeBoardName(oldname,newname.getText());

                        }
                    }
                });
            }
        });

    }
    public void changeNameCard(){

        nameCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String oldname = nameCard.getText();
                TextField oldLabel = new TextField(nameCard.getText());
                EditLabelView.super.getChildren().remove(0);
                EditLabelView.super.getChildren().add(oldLabel);
                oldLabel.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                            Label newname = new Label(oldLabel.getText());
                            EditLabelView.super.getChildren().remove(0);
                            cardViewModel.setCardName(oldname,newname.getText());
                        }
                    }
                });
            }
        });

    }
   public void changeNameColumn(){

        nameColumn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String oldname = nameColumn.getText();
                TextField oldLabel = new TextField(nameColumn.getText());
                EditLabelView.super.getChildren().remove(0);
                EditLabelView.super.getChildren().add(oldLabel);
                oldLabel.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                            Label newname = new Label(oldLabel.getText());
                            EditLabelView.super.getChildren().remove(0);
                            columnViewModel.setColumnName(oldname,newname.getText());
                        }
                    }
                });
            }
        });

   }
}
