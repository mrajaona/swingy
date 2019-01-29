package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class InvalidViewTypeException extends SwingyException {

    private static final long serialVersionUID = -7546250874179970224L;

    public InvalidViewTypeException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale()
                )
            .getString("invalidViewTypeException")
        );
    }

    public InvalidViewTypeException(String message) {
        super(message);
    }

}
