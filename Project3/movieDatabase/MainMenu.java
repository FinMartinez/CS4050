package movieDatabase;

import java.util.Scanner;
import java.util.*;

class MainMenu {

    private Scanner scanner;
    private MovieDb movieDb;
    private BinarySearchTree<Movie> movieTree;
    private SinglyLinkedList<Movie> movieList;
    private ArrayList<Movie> movieArray;

    // Manager password (no peeking!)
    private final String managerPassword = "password";

    public MainMenu(MovieDb movieDb){
        this.scanner = new Scanner(System.in);
        this.movieDb = movieDb;
        this.movieArray = movieDb.getMovies();
        this.movieTree = new BinarySearchTree<>();
        this.movieList = new SinglyLinkedList<>();

        // Populate binary search tree
        for (Movie movie : movieArray){
            movieTree.insert(movie);
        }

        // Print movies in sorted order (inorder traversal)
        System.out.println("Movies in sorted order:");
        movieTree.inOrder();

        // Populate singly linked list
        for (Movie movie : movieArray){
            movieList.insert(movie);
        }
    }

    public void start() {
        while(true){
            System.out.println("Please specify user mode or manager mode:");
            System.out.println("1. User mode");
            System.out.println("2. Manager mode");
            System.out.println("3. Exit");
            int choice = getIntPut(1, 3);
            if (choice == 3) break;

            switch (choice){
                case 1:
                    userMode();
                    break;
                case 2:
                    verifyManager(); // validate admin password
                    break;
                default:
                    System.err.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private void verifyManager(){
        System.out.println("Enter the manager password: ");
        String password = scanner.nextLine();
        if (password.equals(managerPassword)){
            managerMode();
        } else {
            System.err.println("Invalid password. Please try again.");
        }
    }

    private void managerMode(){
        while(true){
            System.out.println("Please select an option:");
            System.out.println("1. Add a movie");
            System.out.println("2. Remove a movie");
            System.out.println("3. Search for a movie");
            System.out.println("4. Sort movies");
            System.out.println("5. Display all movies");
            System.out.println("6. Return to previous menu");
            int choice = getIntPut(1, 6);
            if (choice == 6) break;

            switch (choice){
                case 1:
                    addMovie();
                    break;
                case 2:
                    removeMovie();
                    break;
                case 3:
                    searchMovie();
                    break;
                case 4:
                    sortMovies();
                    break;
                case 5:
                    movieDb.getMovies();
                    for (Movie movie : movieDb.getMovies()){
                    System.out.println(movie);
                    }
                default:
                    System.err.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private void userMode(){
        while(true){
            System.out.println("Please select an option:");
            System.out.println("1. Search for a movie");
            System.out.println("2. Sort movies");
            System.out.println("3. Display all movies");
            System.out.println("4. Return to previous menu");
            int choice = getIntPut(1, 4);
            if (choice == 4) break;

            switch (choice){
                case 1:
                    searchMovie();
                    break;
                case 2:
                    sortMovies();
                    break;
                case 3:
                    movieDb.getMovies();
                    for (Movie movie : movieDb.getMovies()){
                        System.out.println(movie);
                    }
                    break;
                default:
                    System.err.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private void addMovie(){
        System.out.println("Enter the title of the movie: ");
        String title = getStringInput(); //Validate title input

        System.out.println("Enter the genre of the movie: ");
        String genre = getStringInput(); //Validate genre input

        System.out.println("Enter the director of the movie: ");
        String director = getStringInput(); //Validate director input

        System.out.println("Enter the year the movie was released: ");
        int year = getIntPut(1888, 2100); //Validate year input

        Movie movie = new Movie(title, genre, director, year);
        movieDb.addMovie(movie);
        movieArray.add(movie);
        movieTree.insert(movie);

        System.out.println("Movie added successfully.");
    }

    private void removeMovie(){
        System.out.println("Enter the title of the movie you would like to remove: ");
        String title = getStringInput();
        movieDb.removeMovie(title);
        System.out.println("Movie removed successfully.");
    }

    private void searchMovie(){
        System.out.println("Enter an attribute to search by (title, genre, director, year): ");
        String attribute = getAttrInput();

        System.out.println("Enter a search query: ");
        String query = getStringInput();

        //may need to toss in a switch statement when I add in SingleLinkedList
        System.out.println("Please select a data structure to use for search: ");
        System.out.println("1. Binary Search Tree");
        System.out.println("2. Singly Linked List");
        System.out.println("3. Array List");
        System.out.println("NOTE: Binary search is only supported for title search.");
        int choice = getIntPut(1, 3);
        //boolean useBST = getBoolInput();

        switch(choice){
            case 1:
            //may need alterations
            if (!attribute.equals("title")){
                System.err.println("Binary search is only supported for title search.");
                return;
            } else {
                searchBST(attribute, query);
            }
                break;
            case 2:
                searchList(attribute, query);
                break;
            case 3:
                arraySearch(attribute, query);
                break;
            default:
                System.err.println("Invalid input. Please try again.");
                break;
            }
        }

    private void searchBST(String attribute, String query){
        Movie searchMovie = createSearchMovie(attribute, query);

        //Start clock
        long startTime = System.currentTimeMillis();
        // Execute search
        boolean found = movieTree.searchByAttribute(searchMovie, attribute);
        //Stop clock
        long endTime = System.currentTimeMillis();

        //Calculate time taken
        long elapsedTime = (endTime - startTime);

        if (found){
            System.out.println("Movie found: " + searchMovie);
        } else {
            System.err.println("Movie not found.");
        }

        System.out.println("Processing time: " + elapsedTime + " ms");
    }

    private void searchList(String attribute, String query){
        Movie searchMovie = createSearchMovie(attribute, query);

        //Start clock
        long startTime = System.currentTimeMillis();
        // Execute search
        boolean found = movieList.search(searchMovie, attribute);
        //Stop clock
        long endTime = System.currentTimeMillis();

        //Calculate time taken
        long elapsedTime = (endTime - startTime) / 100_000_000;

        if (found){
            System.out.println("Movie found: " + searchMovie);
        } else {
            System.err.println("Movie not found.");
        }

        System.out.println("Processing time: " + elapsedTime + " ms");
    }

    private void arraySearch(String attribute, String query){
        System.out.println("Binary search? (true/false)");
        System.out.println("Notice: If Binary search is selected, quick sort will be used for sorting.");
        boolean binarySearch = getBoolInput();

        System.out.println("Sort ascending? (true/false)");
        boolean ascending = getBoolInput();

        ArrayList<Movie> movieList = movieDb.getMovies();
        ArrayList<Movie> results = MovieSearch.searchMovies(movieList, query, attribute, binarySearch, ascending);
        if (results.isEmpty()){
            System.err.println("No results found.");
        } else {
            for (Movie movie : results){
                System.out.println(movie);
            }
        }
    }

    private Movie createSearchMovie(String attribute, String query){
        switch(attribute.toLowerCase()){
            case "title":
                return new Movie(query, "", "", 0);
            case "genre":
                return new Movie("", query, "", 0);
            case "director":
                return new Movie("", "", query, 0);
            case "year":
                return new Movie("", "", "", Integer.parseInt(query));
            default:
                System.err.println("Invalid attribute.");
                return null;
        }
    }

    private void sortMovies(){
        System.out.println("Enter an attribute to sort by (title, genre, director, year): ");
        String attribute = getAttrInput();

        System.out.println("Enter a sort type (bubble, merge, selection, quick): ");
        String sortType = getSortInput();

        System.out.println("Sort ascending? (true/false)");
        boolean ascending = getBoolInput();

        //ArrayList<Movie> movieList = movieDb.getMovies();
        ArrayList<Movie> sortedMovies = MovieSort.sortMovies(movieArray, attribute, sortType, ascending);
        
        //Display sorted list
        System.out.println("After sorting, sorted list contains:");
        for (Movie movie : sortedMovies){
            System.out.println(movie);
        }
    }

    private int getIntPut(int min, int max){
        int choice;
        while (true){
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max){
                    return choice;
                } else {
                    System.err.println("Invalid input. Please try again.");
                }
            } catch (NumberFormatException e){
                System.err.println("Invalid input. Please try again.");
            }
        }
    }

    private String getStringInput(){
        String input;
        while (true){
            input = scanner.nextLine().trim();
            if(!input.isEmpty()){
                return input;
            } else {
                System.err.println("Input cannot be empty. Please try again.");
            }
        }
    }

    private boolean getBoolInput(){
        while(true){
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")){
                return true;
            } else if (input.equals("false")){
                return false;
            } else {
                System.err.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }
    }

    private String getAttrInput(){
        String attribute;
        while(true){
            attribute = scanner.nextLine().trim().toLowerCase();
            if (attribute.equals("title") || attribute.equals("genre") || attribute.equals("director") || attribute.equals("year")){
                return attribute;
            } else {
                System.err.println("Invalid attribute. Please enter 'title', 'genre', 'director', or 'year'.");
            }
        }
    }

    private String getSortInput(){
        String sortType;
        while(true){
            sortType = scanner.nextLine().trim().toLowerCase();
            if (sortType.equals("bubble") || sortType.equals("merge") || sortType.equals("selection") || sortType.equals("quick")){
                return sortType;
            } else {
                System.err.println("Invalid sort type. Please enter 'bubble', 'merge', 'selection', or 'quick'.");
            }
        }
    }
}
