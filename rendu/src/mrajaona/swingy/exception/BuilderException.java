package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class BuilderException extends SwingyException {

    private static final long serialVersionUID = 1361038541933653328L;

    public BuilderException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale())
            .getString("builderException")
        );
    }

    public BuilderException(String message) {
        super(message);
    }
    
}
