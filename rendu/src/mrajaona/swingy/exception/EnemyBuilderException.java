package mrajaona.swingy.exception;

import java.util.ResourceBundle;

import mrajaona.swingy.data.GameData;

public class EnemyBuilderException extends BuilderException {

    private static final long serialVersionUID = 6109344037885854070L;

    public EnemyBuilderException() {
        super(
            ResourceBundle.getBundle(
                "mrajaona.swingy.locale.ErrorResource",
                GameData.getData().getLocale())
            .getString("enemyBuilderException")
        );
    }

    public EnemyBuilderException(String message) {
        super(message);
    }
    
}
