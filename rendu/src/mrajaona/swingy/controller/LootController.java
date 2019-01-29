package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import mrajaona.swingy.controller.MenuController.Cmd;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.character.HeroModel;

public class LootController extends MenuController {

    @SuppressWarnings("unused")
    private LootController() {}

    private static void invalid() {}

    private static Map<String, Cmd> cmdMap = initMap();
    private static Map<String, Cmd> initMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        map.putAll(MenuController.getCommonCmdMap());

        // loot
        map.put("equip", new Cmd() {
                public void run()           throws SQLException, IOException, SwingyException
                                            { HeroModel.equip(GameData.getData().getArtifact()); }
                public void run(String arg) { invalid(); }
            });
        map.put("leave", new Cmd() {
                public void run()           throws SQLException, IOException, SwingyException
                                            { HeroModel.noLoot(); }
                public void run(String arg) { invalid(); }
            });

        return Collections.unmodifiableMap(map);
    }

    public static void run(String[] args) throws SQLException, IOException, SwingyException {
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

        mrajaona.swingy.Game.getGame().waiting(false);
    }

    public static void delocalize(String[] args) throws SQLException, IOException, SwingyException {
        if (args.length <= 0 || args.length > 2) {
            invalid();
            return;
        }

        try {
            ResourceBundle locale    = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() );
            args[0] = locale.getString(args[0]);
        } catch (MissingResourceException e) {
            args[0] = null;
        }

        run(args);
    }

}
