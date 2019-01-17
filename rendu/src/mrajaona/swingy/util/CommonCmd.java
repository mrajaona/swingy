package mrajaona.swingy.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import mrajaona.swingy.model.GameModel;

public class CommonCmd {

    public interface Cmd {
        public void run()           throws SQLException, IOException;
        public void run(String arg) throws SQLException, IOException;
    }

    private static void invalid() {
        // error
    }

    @Getter private static Map<String, Cmd> CommonCmdMap = initCommonMap();
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
                                            { GameModel.changeViewType(Util.ViewTypes.GUI); }
                public void run(String arg) { invalid(); }
            });
        map.put("console", new Cmd() { // TODO
                public void run()           throws SQLException, IOException
                                            { GameModel.changeViewType(Util.ViewTypes.CONSOLE); }
                public void run(String arg) { invalid(); }
            });
        map.put("language", new Cmd() { // TODO
                public void run()           { invalid(); }
                public void run(String arg) { GameModel.setLocale(arg); }
            });
        return Collections.unmodifiableMap(map);
    }

}
