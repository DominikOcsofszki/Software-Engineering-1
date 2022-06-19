package parkhouse.commands;

import java.util.ArrayList;
import java.util.List;

public class Commander {

    private final List<ICommand> cmd = new ArrayList<>();
    private int cursor = 0;

    public void queue(ICommand cmd) {
        this.cmd.add(cmd);
    }

    public void activate() {
        if (cursor < cmd.size()) {
            cmd.get(cursor++).execute();
        }
    }

    public void undo() {
        if(cursor == 0) throw new IllegalArgumentException();
        cmd.get(--cursor).undo();
        cmd.remove(cursor);
    }

}
