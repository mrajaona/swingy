package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class InvalidScreenException extends SwingyException {

    private static final long serialVersionUID = -1950943463602315420L;

    public InvalidScreenException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale()
                )
            .getString("invalidScreenException")
        );
    }

    public InvalidScreenException(String message) {
        super(message);
    }

}
