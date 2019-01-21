package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mrajaona.swingy.model.character.HeroModel;
import mrajaona.swingy.util.CommonCmd;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.view.helper.MainHelper;

public class MainGameController extends CommonCmd {

    @SuppressWarnings("unused")
    private MainGameController() {}

    private static void invalid() throws SQLException, IOException {
        MainHelper.waitForInput();
    }

    private static Map<String, Cmd> cmdMap = initMap();
    private static Map<String, Cmd> initMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        map.putAll(CommonCmd.getCommonCmdMap());

        // On the map
        map.put("move", new Cmd() {
                public void run()           throws SQLException, IOException
                { invalid(); }
                public void run(String arg) throws SQLException, IOException
                { HeroModel.move(arg); }
            });
        map.put("status", new Cmd() {
                public void run()           throws SQLException, IOException
                {
                    HeroModel.viewStats();
                    MainHelper.waitForInput();
                }
                public void run(String arg) throws SQLException, IOException
                { invalid(); }
            });

        map.put("save", new Cmd() {
                public void run()           throws SQLException, IOException
                {
                    SaveManager.getManager().save();
                    MainHelper.waitForInput();
                }
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
