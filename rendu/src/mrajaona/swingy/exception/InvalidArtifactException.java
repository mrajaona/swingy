package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class InvalidArtifactException extends SwingyException {

    private static final long serialVersionUID = 1172494634950406310L;

    public InvalidArtifactException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale()
                )
            .getString("invalidArtifactException")
        );
    }

    public InvalidArtifactException(String message) {
        super(message);
    }

}
