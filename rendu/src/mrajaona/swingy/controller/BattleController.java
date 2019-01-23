package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mrajaona.swingy.model.character.HeroModel;

public class BattleController {

    @SuppressWarnings("unused")
    private BattleController() {}

    public interface Cmd {
        public void run() throws SQLException, IOException;
    }

    private static void invalid() {
        // error
    }

    private static Map<String, Cmd> cmdMap = initMap();
    private static Map<String, Cmd> initMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        // enemy encounter
        map.put("run", new Cmd() {
                public void run() throws    SQLException, IOException
                                            { HeroModel.run(); }
            });
        map.put("fight", new Cmd() {
                public void run()           { HeroModel.fight(); }
            });

        return Collections.unmodifiableMap(map);
    }

    public static void run(String[] args) throws SQLException, IOException {
        if (args.length <= 0 || args.length > 1) {
            invalid();
            return;
        }

        Cmd cmd = cmdMap.get(args[0]);

        if (cmd != null && args.length == 1)
            cmd.run();
        else
            invalid();
    }

}
