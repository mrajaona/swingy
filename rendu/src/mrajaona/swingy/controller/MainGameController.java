package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import mrajaona.swingy.Game;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.character.HeroModel;
import mrajaona.swingy.util.ResourceMap;
import mrajaona.swingy.util.SaveManager;
import mrajaona.swingy.view.helper.MainHelper;

public class MainGameController extends MenuController {

    @SuppressWarnings("unused")
    private MainGameController() {}

    private static void invalid() {}
    private static void noCmd() {
        Game.getGame().insertToQueue(
            new Runnable() {
                    public void run() {
                        invalid();
                    }
                }
            );
    }

    private static Map<String, Cmd> cmdMap = initMap();
    private static Map<String, Cmd> initMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        map.putAll(MenuController.getCommonCmdMap());

        // On the map
        map.put("move", new Cmd() {
                public void run()           { invalid(); }
                public void run(String arg) throws SQLException, IOException, SwingyException
                                            { HeroModel.move(arg); }
            });
        map.put("status", new Cmd() {
                public void run()           throws SQLException, IOException, SwingyException
                                            { HeroModel.viewStats(); }
                public void run(String arg) { invalid(); }
            });

        map.put("save", new Cmd() {
                public void run()           throws SQLException, IOException, SwingyException
                                            { SaveManager.getManager().save(); }
                public void run(String arg) { invalid(); }
            });

        return Collections.unmodifiableMap(map);
    }

    public static void run(final String[] args) throws SQLException, IOException, SwingyException {
        if (args == null || args.length <= 0 || args.length > 2) {
            noCmd();
            return;
        }

        final Cmd cmd = cmdMap.get(args[0]);

        if (cmd != null && args.length == 1) {
            Game.getGame().insertToQueue(
                new Runnable() {
                        public void run() {
                            try {
                                cmd.run();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                );
        } else if (cmd != null && args.length == 2) {
            Game.getGame().insertToQueue(
                new Runnable() {
                        public void run() {
                            try {
                                cmd.run(args[1]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                );
        }
        else
            noCmd();
    }

    public static void delocalize(String[] args) throws SQLException, IOException, SwingyException {
        if (args == null || args.length <= 0 || args.length > 2) {
            noCmd();
            return;
        }

        try {
            ResourceBundle locale    = ResourceBundle.getBundle( "mrajaona.swingy.locale.GameResource", GameData.getData().getLocale() );
            args[0] = locale.getString(args[0]);
        } catch (MissingResourceException e) {
            args[0] = null;
        }

        if (args.length == 2) {
            // move cmd
            if(args[0].equals(new String("move"))) {
                ResourceMap subLocale = (ResourceMap) ResourceBundle.getBundle( "mrajaona.swingy.locale.DirectionResource", GameData.getData().getLocale() )
                    .getObject("DirectionList");
                args[1] = subLocale.getKeyByValue(args[1]);
            }
        }

        run(args);
    }


}
