package mrajaona.swingy.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LootController {

    @SuppressWarnings("unused")
    private LootController() {}

    public interface Cmd {
        public void run();
    }

    private static void invalid() {
        // error
    }

    private static Map<String, Cmd> cmdMap = initMap();
    private static Map<String, Cmd> initMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        // loot
        map.put("equip", new Cmd() {
                public void run()           { ; }
            });
        map.put("leave", new Cmd() {
                public void run()           { ; }
            });

        return Collections.unmodifiableMap(map);
    }

    public static void run(String[] args) {
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
