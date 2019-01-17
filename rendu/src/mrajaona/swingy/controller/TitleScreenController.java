package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.Util;

public class TitleScreenController {

    @SuppressWarnings("unused")
    private TitleScreenController() {}

    public interface Cmd {
        public void run()           throws SQLException, IOException;
        public void run(String arg) throws SQLException, IOException;
    }

    private static void invalid() {
        // error
    }

    private static Map<String, Cmd> cmdMap = initMap();
    private static Map<String, Cmd> initMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        // Title screen
        map.put("new", new Cmd() {
                public void run() throws SQLException, IOException
                                            { GameModel.changeScreen(Util.GameScreen.NEW); }
                public void run(String arg) { invalid(); }
            });
        map.put("load", new Cmd() {
                public void run()           { invalid(); }
                public void run(String arg) throws SQLException, IOException
                                            { GameModel.loadHero(Long.parseLong(arg)); }
            });
        map.put("delete", new Cmd() {
                public void run()           { invalid(); }
                public void run(String arg) throws SQLException, IOException
                                            { GameModel.deleteHero(Long.parseLong(arg)); }
            });
        return Collections.unmodifiableMap(map);
    }

    public static void run(String[] args) throws SQLException, IOException {
        if (args.length <= 0 || args.length > 2) {
            invalid();
            return;
        }

        Cmd cmd = cmdMap.get(args[0]);

        if (cmd != null && args.length == 1)
            cmd.run();
        else if (cmd != null && args.length == 2)
            cmd.run(args[1]);
        else
            invalid();
    }

}
