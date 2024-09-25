/*
* 1. Include all sorting/searching methods brought up in class
* - 4 sorting methods minimum
* - 2 searching methods minimum
*
* 2. Read in a list of movies from a file
3. Needs to have two modes: user mode and manager mode
- User mode: can search for movies, sort movies, and display all movies
- Manager mode: can add movies, remove movies, search for movies, sort movies, and display all movies
*/

import java.util.ArrayList;
import java.util.Scanner;

public class movieProject {
    public static void main(String[] args){
        MovieManager manager = new MovieManager();
    
        /* Include a read-in method to clear the data base, then read in a .csv file containing 16+ movies for use with the program */
        manager.run();
        
    }
}

class MovieManager {

    private boolean managerMode = false;

    public void movieManager(){
        //ArrayList<Movie> movieList = new ArrayList<Movie>();
        // ^ May not need this line
    }

    public void setManagerMode(boolean mode){
        this.managerMode = mode;
    }

    public boolean isManagerMode(){
        return this.managerMode;
    }

    public void run(){
        // Read in a list of movies from a file
        // Clear the database
        // Read in a .csv file containing 16+ movies for use with the program
        Scanner scanner = new Scanner(System.in);
        Boolean validInput = true;

        while (validInput){
            displayMenu();
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice){
                    case 1:
                        // Add a movie
                        if (isManagerMode()){
                            addMovie();
                        } else {
                            System.out.println("You do not have permission to add a movie.");
                        }
                        break;
                    case 2:
                        // Remove a movie
                        if (isManagerMode()){
                            removeMovie();
                        } else {
                            System.out.println("You do not have permission to remove a movie.");
                        }
                        break;
                    case 3:
                        // Search for a movie
                        searchMovie();
                        break;
                    case 4:
                        // Sort movies
                        sortMovies();
                        break;
                    case 5:
                        // Display all movies
                        displayAllMovies();
                        break;
                    case 6:
                        // Exit
                        validInput = false;
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            }
        }
    }

    public void addMovie(){
        // Add a movie
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie: ");
        String title = scanner.nextLine();
        System.out.println("Enter the genre of the movie: ");
        String genre = scanner.nextLine();
        System.out.println("Enter the director of the movie: ");
        String director = scanner.nextLine();
        System.out.println("Enter the year the movie was released: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        MovieDb.addMovie(new Movie(title, genre, director, year));
        scanner.close();
    }

    public void removeMovie(){
        // Remove a movie
        System.out.println("Enter the title of the movie you would like to remove: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        MovieDb movieDb = new MovieDb();
        movieDb.removeMovie(title);
        scanner.nextLine();
        scanner.close();
    }

    public void searchMovie(){
        // Search for a movie
        System.out.println("Enter a search keyword: ");
        Scanner scanner = new Scanner(System.in);
        String keyword = scanner.nextLine();
        MovieDb movieDb = new MovieDb();   
        movieDb.searchMovie(keyword);
        scanner.close();
    }

    public void sortMovies(){
        // Sort movies
        System.out.println("Please select a sorting criteria:");
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by genre");
        System.out.println("3. Sort by director");
        System.out.println("4. Sort by year");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please select a sorting method:");
        System.out.println("1. Bubble sort");
        System.out.println("2. Selection sort");
        System.out.println("3. Insertion sort");
        System.out.println("4. Merge sort");
        int method = scanner.nextInt();
        scanner.nextLine();
        scanner.close();   
    }   

    public void toggleMode(Scanner scanner){
        // Toggle between user and manager mode
        System.out.println("Please enter the password to toggle modes: ");
        String password = scanner.nextLine();
        if (password.equals("password")){
            setManagerMode(!isManagerMode());
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

    public void displayAllMovies(){
        // Display all movies
    }

    public void displayMenu(){
        System.out.println("Please select an option:");
        System.out.println("1. Add a movie");
        System.out.println("2. Remove a movie");
        System.out.println("3. Search for a movie");
        System.out.println("4. Sort movies");
        System.out.println("5. Display all movies");
        System.out.println("6. Exit");
    }
}

class Movie {
    private String title;
    private String genre;
    private String director;
    private int year;

    public Movie(String title, String genre, String director, int year){
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
    }

    public String getTitle(){
        return title;
    }

    public String getGenre(){
        return genre;
    }

    public String getDirector(){
        return director;
    }

    public int getYear(){
        return year;
    }

    @Override
    public String toString(){
        return "Title: " + title + ", Genre: " + genre + ", Director: " + director + ", Year: " + year;
    }

}

class MovieDb {
    private ArrayList<Movie> movieList;

    public MovieDb(){
        movieList = new ArrayList<Movie>();
    }

    public static void addMovie(Movie movie){
        
    }

    public void removeMovie(String title){
        movieList.removeIf(movie -> movie.getTitle().equals(title));
    }

    public void searchMovie(String title){
        for (Movie movie : movieList){
            if (movie.getTitle().equals(title)){
                System.out.println(movie);
            }
        }
    }

    public void sortMovies(){
        // Sort movies
    }

    public void displayAllMovies(){
        for (Movie movie : movieList){
            System.out.println(movie);
        }
    }
}