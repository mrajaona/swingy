package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class InvalidCoordException extends SwingyException {

    private static final long serialVersionUID = 5813547412489878895L;

    public InvalidCoordException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale()
                )
            .getString("invalidCoordException")
        );
    }

    public InvalidCoordException(String message) {
        super(message);
    }

}
