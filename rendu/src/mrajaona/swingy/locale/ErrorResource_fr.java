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
            {"invalidLanguage", "Langue invalide (en, fr)."},

            /* Exceptions */
            {"builderException",         "Impossible de creer un Objet valide."},
            {"enemyBuilderException",    "Impossible de creer un ennemi valide."},
            {"gameMapBuilderException",  "Impossible de creer une carte valide."},
            {"heroBuilderException",     "Impossible de creer un hero valide."},
            {"saveFileBuilderException", "Impossible de creer un fichier de sauvegarde valide."},

            {"invalidArtifactException", "Artefact invalide."},
            {"invalidCoordException",    "Coordonnees invalides."},
            {"invalidSaveFileException", "Fichier de sauvegarde invalide."},
            {"invalidScreenException",   "Ecran invalide."},
            {"invalidViewTypeException", "Type de vue invalide."},

            {"loadHeroListException",    "Impossible de charger la liste de heros."},
            {"resourceMapException",     "Impossible de charger la ressource."},
            {"dataException",            "Erreur lors du chargement des donnees."}

       };
    }


}
