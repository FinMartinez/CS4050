package movieDatabase;

public class MovieDBmain {
    public static void main(String[] args){
        MovieDb movieDb = new MovieDb();
        movieDb.loadFromCSV("movies_list.csv");
        MainMenu displayMenu = new MainMenu(movieDb);
        displayMenu.start();
        }
}

