package view;

import command.Command;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Stack;

public class CommanManager {
    // Stocké commande dans LIFO
    private static CommanManager instance=null;
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();
    public StringProperty undoName = new SimpleStringProperty(getUndoName());
    public BooleanProperty undoAvailable = new SimpleBooleanProperty(isUndoAvailable());
    public StringProperty redoName = new SimpleStringProperty(getRedoName());
    public BooleanProperty redoAvailable = new SimpleBooleanProperty(isRedoAvailable());
   public boolean isUndoAvailable(){if (undoStack.isEmpty())return true;else return false;}
    public boolean isRedoAvailable(){
        if (redoStack.isEmpty())return true;else return false;
    }
    public static CommanManager getInstance(){
       if(instance==null){
           instance = new CommanManager();
       }
       return instance;
    }
    public String getUndoName(){
       if(isUndoAvailable()==false){
           return "Annuler "+undoStack.peek().getName()+" CTRL+Z";
       }
       return "Annuler  CTRL+Z";
    }
    public String getRedoName(){
        if(isRedoAvailable()==false){
            return "Refaire "+redoStack.peek().getName()+" CTRL+Y";
        }
        return "Refaire  CTRL+Y";
    }
    public void execute(Command command){
       command.execute();
       undoStack.push(command);
       redoStack.clear();
       changeState();

    }
    public void undo(){
       if(!undoStack.isEmpty()){
           Command command = undoStack.pop();
           command.undo();
           redoStack.push(command);
           changeState();

       }
    }
    public void redo(){
       if(!redoStack.isEmpty()){
           Command command = redoStack.pop();
           command.redo();
           undoStack.push(command);
           changeState();

       }
    }
    public void changeState(){
       undoAvailable.setValue(isUndoAvailable());
       redoAvailable.setValue(isRedoAvailable());
       undoName.setValue(getUndoName());
       redoName.setValue(getRedoName());

    }
}
