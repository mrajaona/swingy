package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class GameMapBuilderException extends BuilderException {

    private static final long serialVersionUID = -7961573588182243732L;

    public GameMapBuilderException() {
        super(
                ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ErrorResource",
                    GameData.getData().getLocale())
                .getString("gameMapBuilderException")
            );
    }

    public GameMapBuilderException(String message) {
        super(message);
    }

}
