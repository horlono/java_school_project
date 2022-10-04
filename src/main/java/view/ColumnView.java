package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Column;
import mvvm.BoardViewModel;
import mvvm.ColumnViewModel;

public class ColumnView extends VBox {

    BorderPane col = new BorderPane();
    Label L = new Label("⬅"),
            R= new Label("➡");
    Button addCard = new Button("Add Card");
    Label columnLabel = new Label();

    public ColumnViewModel columnViewModel;
    public EditLabelView labelView;


    BoardViewModel boardViewModel;
    Column column;
    ColumnView(Column column, BoardViewModel boardViewModel) {
        this(new ColumnViewModel(column,boardViewModel));
        this.boardViewModel=boardViewModel;
        this.column = column;
    }
    public ListView<Card> lvCard = new ListView<>();




    public ColumnView(ColumnViewModel viewModel) {
        this.columnViewModel = viewModel;
        this.labelView = new EditLabelView(columnViewModel ,column);
        lvCard.itemsProperty().bind(viewModel.CardProperty());
        lvCard.setMaxHeight(300);
        changeLvCardBpane();
        configVMBinding();
        labelColumn();
        this.getChildren().addAll(col,lvCard,addCard);
        configActions();
    }

    public void changeLvCardBpane() {
        lvCard.setCellFactory(view -> new ListCell<>() {
            @Override
            public void updateItem(Card card, boolean b) {
                super.updateItem(card, b);
                CardView cardview = null;
                if (card != null) {
                    cardview = new CardView(card , columnViewModel);
                }
                setGraphic(cardview);
            }
        });
    }
    public void configVMBinding(){columnLabel.textProperty().bind(columnViewModel.columnNameProperty()); }
    public void configActions(){
        R.setOnMouseClicked(e-> boardViewModel.moveRight(column));
        L.setOnMouseClicked(e-> boardViewModel.moveLeft(column));
        addCard.setOnAction(e -> columnViewModel.newCard());
        labelView.setOnMouseClicked(e->{if(e.getButton().equals(MouseButton.SECONDARY)){deleteColumn();}});

    }
    public void deleteColumn(){
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Supprimer");
        contextMenu.getItems().addAll(item1);
        labelView.setOnContextMenuRequested(event -> contextMenu.show(labelView, event.getScreenX(), event.getSceneY()));
        delete(item1);
    }
    public void delete(MenuItem item1){
        item1.setOnAction(event -> {
            Label secondLabel = new Label("Voulez-vous vraiment supprimer?");
            Button oui = new Button("oui"),
                    non = new Button("non");
            BorderPane bpConfirm = new BorderPane();
            bpConfirm.setLeft(oui);
            bpConfirm.setRight(non);
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().addAll(secondLabel, bpConfirm);
            Scene secondScene = new Scene(secondaryLayout, 250, 100);
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            non.setOnAction(event1 -> newWindow.close());
            oui.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    newWindow.close();
                    boardViewModel.removeColonne(column);
                }
            });
            newWindow.show();
        });
    }
    public void labelColumn(){

        col.setCenter(labelView);
        col.setLeft(L);
        col.setRight(R);
        R.setDisable(columnViewModel.setLastArrowProperty());
        L.setDisable(columnViewModel.setFirstArrowProperty());
        labelView.setAlignment(Pos.CENTER);
        col.setAlignment(col, Pos.CENTER);
    }






}
