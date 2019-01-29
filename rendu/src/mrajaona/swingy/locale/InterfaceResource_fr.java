package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class InterfaceResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"gui",     "gui"},
            {"console", "console"},

            // Title
            {"newButton", "Nouveau"},
            {"loadButton", "Charger"},
            {"deleteButton", "Supprimer"},

            // New
            {"createButton", "Creer"},
            {"cancelButton", "Annuler"},

            // Main
            {"fightButton",  "Combattre"},
            {"runButton",    "Fuir"},
            {"equipButton",  "Equiper"},
            {"leaveButton",  "Laisser"},

            // Win
            {"winLabel", "Felicitations, vous avez gagne !"},
            {"titleButton", "Ecran titre"},
            {"continueButton", "Nouvelle carte"},

            // Game Over
            {"loseLabel",      "Votre heros est vaincu."},
            {"autoSaveLabel",  "Votre partie va etre enregistree."},
            {"giveUpButton",   "Ecran titre"},
            {"reloadButton",   "Derniere sauvegarde"},

            // Menu
            {"menu", "Menu"},
            {"saveItem", "Sauvegarder"},
            {"consoleItem", "Vue console"},
            {"exitItem", "Quitter"},

            {"langMenu", "Langue"},
            {"enItem", "Anglais"},
            {"frItem", "Francais"},

            /** log messages **/

            {"msgTitleInput", "Que voulez-vous faire ?"},
            {"msgGetInput",   "Vous êtes en position [%1$d, %2$d]. Que voulez-vous faire ?"}, // x, y
            {"msgMove",       "%1$s va dans la direction %2$s."}, // hero name, direction

            {"msgEncounter", "Un %2$s de niveau %1$d apparait !"}, // level, enemy type

            {"msgRun",        "%1$s tente de fuir..."}, // hero name
            {"msgRunFail",    "...mais echoue."},
            {"msgRunSuccess", "...et reussit !"},

            {"msgAttack",     "%1$s attaque."}, // hero name / enemy type
            {"msgDamage",     "%1$s recoit %2$d degats."}, // hero name / enemy type, damage
            {"msgDied",       "%1$s est mort."}, // hero name / enemy type

            {"msgDrop",       "L'ennemi a fait tomber un/une %1$s. (%2$s : %3$s + %4$d)"}, // artifact name, type, stat, modifier
            {"msgEquip",      "%1$s equipe le/la %2$s."}, // hero name, artifact name
            {"msgUnequip",    "%1$s desequipe le/la %1$s."}, // hero name, artifact name
            {"msgLeave",      "L'artefact disparait dans un nuage magique."},

            {"msgLvlUp",      "%1$s est monte au niveau %2$d !"}, // hero name, level

            {"msgSave",       "Partie enregistree."},

        };
    }

}
