package mrajaona.swingy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import lombok.Getter;
import mrajaona.swingy.Game;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.exception.SwingyException;
import mrajaona.swingy.model.GameModel;
import mrajaona.swingy.util.Util.ViewTypes;

public class MenuController {

    public interface Cmd {
        public void run()           throws SQLException, IOException, SwingyException;
        public void run(String arg) throws SQLException, IOException, SwingyException;
    }

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

    @Getter private static Map<String, Cmd> commonCmdMap = initCommonMap();
    private static Map<String, Cmd> initCommonMap() {
        Map<String, Cmd> map = new HashMap<String, Cmd>();

        map.put("exit", new Cmd() { // TODO
                public void run()           { GameModel.exitGame(); }
                public void run(String arg) { invalid(); }
            });
        map.put("gui", new Cmd() {
                public void run()           throws SQLException, IOException, SwingyException
                                            { GameModel.changeViewType(ViewTypes.GUI); }
                public void run(String arg) { invalid(); }
            });
        map.put("console", new Cmd() {
                public void run()           throws SQLException, IOException, SwingyException
                                            { GameModel.changeViewType(ViewTypes.CONSOLE); }
                public void run(String arg) { invalid(); }
            });
        map.put("language", new Cmd() {
                public void run()           { invalid(); }
                public void run(String arg) throws SQLException, IOException
                                            { GameModel.setLocale(arg); }
            });
        return Collections.unmodifiableMap(map);
    }

    public static void run(final String[] args) throws SQLException, IOException, SwingyException {
        if (args == null || args.length <= 0 || args.length > 2) {
            noCmd();
            return;
        }

        final Cmd cmd = commonCmdMap.get(args[0]);

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

        run(args);
    }

}
