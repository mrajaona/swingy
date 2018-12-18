package mrajaona.swingy;

/*
** Main loop for the game
*/

public class GameLoop {

    public static final String VIEW_TYPE_GUI     = "gui";
    public static final String VIEW_TYPE_CONSOLE = "console";
    public static enum ViewType {
        VIEW_TYPE_GUI,
        VIEW_TYPE_CONSOLE
    }

    public static ViewType viewType = ViewType.VIEW_TYPE_CONSOLE;

    private void game(String[] args) {

        if (args.length != 1)
            return ;

        try {

            switch(args[0]) {
                case VIEW_TYPE_GUI :
                    viewType = ViewType.VIEW_TYPE_GUI;
                    break;
                case VIEW_TYPE_CONSOLE :
                    viewType = ViewType.VIEW_TYPE_CONSOLE;
                    break;
                default :
                    // Exception
                    break;
            }

        	Save dbManager = Save.getManager();
            dbManager.openConnection();

            System.out.println("Hello Maven!" + System.lineSeparator() + "Welcome to Swingy!");

            // newHero();
            // loadHero(1);
            // saveHero();

            dbManager.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Creates an instance of the game
    public static void main(String[] args) {
        (new GameLoop()).game(args);
    }

}
