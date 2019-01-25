package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class ErrorResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"error", "Message d'erreur"},
            {"exception", "Message d'exception"},

            /* Errors */
            {"invalidClass", "Classe invalide (guerrier, voleur, magicien, pretre)."},
            {"invalidName", "Nom invalide : lettres (sans accents) et espaces seulement."},
            {"invalidDirection", "Direction invalide (nord, sud, est, ouest)."},
            {"invalidLanguage", "Langue invalide (en, fr)."}

       };
    }


}
