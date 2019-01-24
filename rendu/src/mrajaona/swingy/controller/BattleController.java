package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mrajaona.swingy.controller.MenuController.Cmd;
import mrajaona.swingy.model.character.HeroModel;

public class BattleController extends MenuController {

    @SuppressWarnings("unused")
    private BattleController() {}

    private static void invalid() {
        // error
    }

    private static Map<String, Cmd> cmdMap = initMap();
    private static Map<String, Cmd> initMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        map.putAll(MenuController.getCommonCmdMap());

        // enemy encounter
        map.put("run", new Cmd() {
                public void run() throws    SQLException, IOException
                                            { HeroModel.run(); }
                public void run(String arg) throws SQLException, IOException
                                            { invalid(); }
                });
        map.put("fight", new Cmd() {
                public void run() throws    SQLException, IOException
                                            { HeroModel.fight(); }
                public void run(String arg) throws SQLException, IOException
                                            { invalid(); }
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
