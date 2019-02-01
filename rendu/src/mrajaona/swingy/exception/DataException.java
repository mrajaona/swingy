package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class DataException extends SwingyException {

    private static final long serialVersionUID = 646687656957945283L;

    public DataException() {
        super(
                ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ErrorResource",
                    GameData.getData().getLocale()
                    )
                .getString("dataException")
            );
    }

    public DataException(String message) {
        super(message);
    }

}
