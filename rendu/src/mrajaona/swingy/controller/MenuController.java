package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.Util.ViewTypes;

public class MenuController {

    public interface Cmd {
        public void run()           throws SQLException, IOException;
        public void run(String arg) throws SQLException, IOException;
    }

    private static void invalid() {
        // error
    }

    @Getter private static Map<String, Cmd> commonCmdMap = initCommonMap();
    private static Map<String, Cmd> initCommonMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        map.put("exit", new Cmd() { // TODO
                public void run()           { ; }
                public void run(String arg) { invalid(); }
            });
        map.put("help", new Cmd() { // TODO
                public void run()           { ; }
                public void run(String arg) { invalid(); }
            });
        map.put("gui", new Cmd() { // TODO
                public void run()           throws SQLException, IOException
                                            { GameModel.changeViewType(ViewTypes.GUI); }
                public void run(String arg) { invalid(); }
            });
        map.put("console", new Cmd() { // TODO
                public void run()           throws SQLException, IOException
                                            { GameModel.changeViewType(ViewTypes.CONSOLE); }
                public void run(String arg) { invalid(); }
            });
        map.put("language", new Cmd() { // TODO
                public void run()           { invalid(); }
                public void run(String arg) { GameModel.setLocale(arg); }
            });
        return Collections.unmodifiableMap(map);
    }

    public static void run(String[] args) throws SQLException, IOException {
        if (args.length <= 0 || args.length > 2) {
            invalid();
            return;
        }

        Cmd cmd = commonCmdMap.get(args[0]);

        if (cmd != null && args.length == 1)
            cmd.run();
        else if (cmd != null && args.length == 2)
            cmd.run(args[1]);
        else
            invalid();
    }

}
