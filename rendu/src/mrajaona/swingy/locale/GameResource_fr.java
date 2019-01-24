package mrajaona.swingy.locale;

import java.util.ListResourceBundle;

public class GameResource_fr extends ListResourceBundle {

    protected Object[][] getContents() {
        return new Object[][] {
            /* Strings */
            {"msgGetInput", "Que voulez-vous faire ?"},
            {"msgMove",       "%1$s va dans la direction %2$s (%3$d, %4$d)."}, // hero name, direction, x, y

            {"msgEncounter", "Un %2$s de niveau %1$d apparait !"}, // level, enemy type

            {"msgRun",        "%1$s tente de fuir..."}, // hero name
            {"msgRunFail",    "...mais echoue."},
            {"msgRunSuccess", "...et reussit !"},

            {"msgAttack",     "%1$s attaque."}, // hero name / enemy type
            {"msgDamage",     "%1$s recoit %2$d degats."}, // hero name / enemy type, damage
            {"msgDied",       "%1$s est mort."}, // hero name / enemy type

            {"msgDrop",       "L'ennemi a fait tomber un %1$s."}, // artifact name
            {"msgEquip",      "%1$s equipe le %2$s."}, // hero name, artifact name
            {"msgUnequip",    "%1$s desequipe le %1$s."}, // hero name, artifact name
            {"msgLeave",      "L'artefact disparait dans un nuage magique."},

            {"msgLvlUp",      "%1$s est monte au niveau %2$d !"}, // hero name, level

            {"msgSave",       "Partie enregistree."},

            /* commands */
            // common
            {"exit",     ""},
            {"help",     ""},
            {"gui",      ""},
            {"console",  ""},
            {"language", ""},

            // title
            {"new",      ""},
            {"load",     ""},
            {"delete",   ""},

            // new
            {"create",   ""},

            // main
            {"move",     ""},
            {"status",   ""},
            {"save",     ""},
       };
    }

}
