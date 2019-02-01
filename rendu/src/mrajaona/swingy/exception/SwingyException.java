package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class SwingyException extends Exception {

    private static final long serialVersionUID = 8062420357979928369L;

    public SwingyException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale()
                )
            .getString("defaultException")
        );
    }

    public SwingyException(String message) {
        super(message);
    }

}
