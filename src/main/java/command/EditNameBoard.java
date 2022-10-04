package command;

import model.Board;
import mvvm.BoardViewModel;

public class EditNameBoard implements Command{
    private String newname;
    private String oldname;
    private Board board;
    public EditNameBoard(Board board, String newname, String oldname){
        this.board = board;
        this.newname = newname;
        this.oldname = oldname;
    }
    @Override
    public void execute() {
        board.setName(newname);


    }

    @Override
    public void undo() {
        board.setName(oldname);

    }

    @Override
    public void redo() {
        execute();

    }

    @Override
    public String getName() {
        return "Changer le nom du tableau "+oldname+" en "+newname;
    }
}
