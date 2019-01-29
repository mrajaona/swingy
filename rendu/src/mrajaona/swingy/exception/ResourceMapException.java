package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class ResourceMapException extends SwingyException {

    private static final long serialVersionUID = 159428138150852673L;

    public ResourceMapException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale()
                )
            .getString("resourceMapException")
        );
    }

    public ResourceMapException(String message) {
        super(message);
    }

}
