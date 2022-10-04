package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Card;
import mvvm.CardViewModel;
import mvvm.ColumnViewModel;

public class CardView extends BorderPane {
    ColumnViewModel columnViewModel;
    Card card;
    Label cardName = new Label();
    EditLabelView labelView;
    CardView(Card card , ColumnViewModel columnViewModel){
        this(new CardViewModel(card,columnViewModel));
        this.columnViewModel =columnViewModel;

        this.card = card;

    }

    Text up = new Text ("⬆"),
            down = new Text("⬇"),
            left = new Text("⬅"),
            right = new Text("➡");

    public CardViewModel viewModel;


   public CardView(CardViewModel cardViewModel){
       this.viewModel = cardViewModel ;
       this.labelView = new EditLabelView(viewModel,card);
       cardName.textProperty().bind(viewModel.cardNameProperty());
       this.setCenter(labelView);
       this.setBottom(down);
       this.setTop(up);
       this.setLeft(left);
       this.setRight(right);
       labelView.setAlignment(Pos.CENTER);
       this.setAlignment(labelView, Pos.TOP_CENTER);
       this.setAlignment(up,Pos.BOTTOM_CENTER);
       this.setAlignment(down,Pos.BOTTOM_CENTER);
       configActions();
       setDisable();
   }
   public void configActions(){
       down.setOnMouseClicked(e-> columnViewModel.moveDown(card));
       up.setOnMouseClicked(e -> columnViewModel.moveUp(card));
       right.setOnMouseClicked(e -> columnViewModel.setCardnextColumn(card));
       left.setOnMouseClicked( e -> columnViewModel.setCardPreColumn(card));
   }
   public void setDisable(){
       down.setDisable(viewModel.setLastArrowCardProperty());
       up.setDisable(viewModel.setFirstArrowCardProperty());
       left.setDisable(viewModel.leftArrowProperty());
       right.setDisable(viewModel.rightArrowProperty());
       labelView.setOnMouseClicked(e->{if(e.getButton().equals(MouseButton.SECONDARY)){deleteCard();}});


   }
    public void deleteCard(){
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Supprimer");
        contextMenu.getItems().addAll(item1);
        labelView.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(labelView,event.getScreenX(), event.getSceneY());
            }
        });
        delete(item1);
    }
    public void delete(MenuItem item1){
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
                non.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        newWindow.close();
                    }
                });
                oui.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        newWindow.close();
                        columnViewModel.removeCard(card);
                    }
                });
                newWindow.show();
            }
        });
    }


}
