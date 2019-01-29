package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class InvalidSaveFileException extends SwingyException {

    private static final long serialVersionUID = 8368352372593474979L;

    public InvalidSaveFileException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale()
                )
            .getString("invalidSaveFileException")
        );
    }

    public InvalidSaveFileException(String message) {
        super(message);
    }

}
