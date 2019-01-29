package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class SaveFileBuilderException extends BuilderException {

    private static final long serialVersionUID = -9004911829237009161L;

    public SaveFileBuilderException() {
        super(
                ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ErrorResource",
                    GameData.getData().getLocale())
                .getString("saveFileBuilderException")
            );
    }

    public SaveFileBuilderException(String message) {
        super(message);
    }

}
