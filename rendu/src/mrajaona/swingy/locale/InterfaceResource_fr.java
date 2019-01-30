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
            {"createButton", "Créer"},
            {"cancelButton", "Annuler"},

            // Main
            {"fightButton",  "Combattre"},
            {"runButton",    "Fuir"},
            {"equipButton",  "Equiper"},
            {"leaveButton",  "Laisser"},

            // Win
            {"winLabel", "Félicitations, vous avez gagné !"},
            {"autoSaveLabel",  "Votre partie va etre enregistrée."},
            {"titleButton", "Ecran titre"},
            {"continueButton", "Nouvelle carte"},

            // Game Over
            {"loseLabel",      "Votre héros est vaincu."},
            {"giveUpButton",   "Ecran titre"},
            {"reloadButton",   "Dernière sauvegarde"},

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

            {"msgEncounter", "Un %2$s de niveau %1$d apparaît !"}, // level, enemy type

            {"msgRun",        "%1$s tente de fuir..."}, // hero name
            {"msgRunFail",    "...mais échoue."},
            {"msgRunSuccess", "...et réussit !"},

            {"msgAttack",     "%1$s attaque."}, // hero name / enemy type
            {"msgMissed",     "%1$s rate la cible."}, // hero name / enemy type
            {"msgDamage",     "%1$s reçoit %2$d dégâts."}, // hero name / enemy type, damage
            {"msgDied",       "%1$s est mort."}, // hero name / enemy type

            {"msgDrop",       "L'ennemi a fait tomber un/une %1$s. (%2$s : %3$s + %4$d)"}, // artifact name, type, stat, modifier
            {"msgEquip",      "%1$s équipe le/la %2$s."}, // hero name, artifact name
            {"msgUnequip",    "%1$s déséquipe le/la %1$s."}, // hero name, artifact name
            {"msgLeave",      "L'artefact disparait dans un nuage magique."},

            {"msgLvlUp",      "%1$s est monté au niveau %2$d !"}, // hero name, level

            {"msgSave",       "Partie enregistrée."},

        };
    }

}
