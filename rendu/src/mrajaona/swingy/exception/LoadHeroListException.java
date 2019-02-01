package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class LoadHeroListException extends SwingyException {

    private static final long serialVersionUID = 4172089971451036226L;

    public LoadHeroListException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale()
                )
            .getString("loadHeroListException")
        );
    }

    public LoadHeroListException(String message) {
        super(message);
    }

}
