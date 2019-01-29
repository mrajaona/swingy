package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class HeroBuilderException extends BuilderException {

    private static final long serialVersionUID = -8621992056248621806L;

    public HeroBuilderException() {
        super(
                ResourceBundle.getBundle(
                    "mrajaona.swingy.locale.ErrorResource",
                    GameData.getData().getLocale())
                .getString("heroBuilderException")
            );
    }

    public HeroBuilderException(String message) {
        super(message);
    }

}
