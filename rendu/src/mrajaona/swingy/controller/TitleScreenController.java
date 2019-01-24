package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.Util.GameScreen;
import mrajaona.swingy.view.helper.TitleHelper;

public class TitleScreenController extends MenuController {

    @SuppressWarnings("unused")
    private TitleScreenController() {}

    private static void invalid() throws SQLException, IOException {
        TitleHelper.waitForInput();
    }

    private static Map<String, Cmd> cmdMap = initMap();
    private static Map<String, Cmd> initMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        map.putAll(MenuController.getCommonCmdMap());

        // Title screen
        map.put("new", new Cmd() {
                public void run()           throws SQLException, IOException
                { GameModel.changeScreen(GameScreen.NEW); }
                public void run(String arg) throws SQLException, IOException
                                            { invalid(); }
            });
        map.put("load", new Cmd() {
                public void run()           throws SQLException, IOException
                { invalid(); }
                public void run(String arg) throws SQLException, IOException
                { GameModel.loadFile(Long.parseLong(arg)); }
            });
        map.put("delete", new Cmd() {
                public void run()           throws SQLException, IOException
                { invalid(); }
                public void run(String arg) throws SQLException, IOException
                {
                    GameModel.deleteHero(Long.parseLong(arg));
                    TitleHelper.waitForInput();
                }
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
