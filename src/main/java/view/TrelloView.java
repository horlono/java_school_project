package view;



import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import mvvm.BoardViewModel;


public class TrelloView extends VBox{
    public TrelloView(Stage primaryStage, BoardViewModel boardViewModel, Board board){
        this.boardViewModel = boardViewModel;
        this.commanManager= CommanManager.getInstance();
        this.labelView = new EditLabelView(boardViewModel,board);
        componentHierarchy();
        configAction();
        lvColumn.setOrientation(Orientation.HORIZONTAL);
        changeLvColuminVBox();
        configDataBindings();
        configVMBinding();
        Scene scene = new Scene(vbMainBox);
        primaryStage.setTitle("Trello");
        primaryStage.setScene(scene);
    }
    public EditLabelView labelView;
    VBox vbMainBox = new VBox();
    public ListView<Column> lvColumn = new ListView<>();

    Label colMain = new Label();

    MenuBar mBar = new MenuBar();

    Menu menuFichier = new Menu("Fichier"),
            menuEdition = new Menu("Edition");
    MenuItem mAnnuler = new MenuItem(),
            mRefaire = new MenuItem(),
            mNewColumn = new MenuItem("Nouvelle colonne"),
            mQuitter = new MenuItem("Quitter");


    private final BoardViewModel boardViewModel;
    private final CommanManager commanManager;
    public void componentHierarchy(){
        configMenubar();
        vbMainBox.getChildren().addAll(mBar,labelView,lvColumn);

    }
    public void configDataBindings(){
        lvColumn.itemsProperty().bind(boardViewModel.ColumnProperty());

        colMain.textProperty().bind(boardViewModel.boardNameProperty());
    }
    public void changeLvColuminVBox() {
        lvColumn.setCellFactory(view -> new ListCell<>() {
            @Override
            public void updateItem(Column column, boolean b) {
                super.updateItem(column, b);
                ColumnView columnView = null;
                if (column != null) {
                    columnView = new ColumnView(column,boardViewModel);
                }
                setGraphic(columnView);
            }
        });

    }
    public void configAction() {
        shortCut();
        mAnnuler.setOnAction(e -> commanManager.undo());
        mRefaire.setOnAction(e -> commanManager.redo());
        mNewColumn.setOnAction(e -> boardViewModel.newColumn());


    }
    public void configVMBinding(){
        mAnnuler.textProperty().bind(commanManager.undoName);
        mAnnuler.disableProperty().bind(commanManager.undoAvailable);
        mRefaire.textProperty().bind(commanManager.redoName);
        mRefaire.disableProperty().bind(commanManager.redoAvailable);
    }
    public void configMenubar(){
        mBar.getMenus().addAll(menuFichier,menuEdition);
        menuEdition.getItems().addAll(mAnnuler,mRefaire);
        menuFichier.getItems().addAll(mNewColumn,mQuitter);
    }
    public void shortCut(){
       mAnnuler.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
       mRefaire.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
       mQuitter.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));


    }

}
