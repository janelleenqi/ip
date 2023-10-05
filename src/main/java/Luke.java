
import luke.actions.*;
import luke.user.LukeTimeError;
import luke.user.Ui;
import luke.tasks.*;
import luke.files.*;

import java.util.ArrayList;

public class Luke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Luke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LukeTimeError e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    private static final String BYE_COMMAND = "bye";

    private static ArrayList<Task> taskList = new ArrayList<>();

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit(); //for bye command
            } catch (LukeTimeError e) { //from c.execute i think
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }

    public void run() {
        //Task[] taskList = new Task[100];

        int taskNumber;

    }

    public static void main(String[] args) {
        new Luke("data/tasks.txt").run();
        //that was the only line
    }
}

