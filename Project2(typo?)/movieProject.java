/**
 * Movie Database Application
 * 
 * This application provides functionality to manage and search through a collection 
 * of movies. Users can view, sort, and search movies by various attributes such as 
 * title, genre, director, and year. In manager mode, additional options are available 
 * to add or remove movies from the database.
 * 
 * Features:
 * - User and Manager modes with password protection for manager mode.
 * - Add, remove, search, and display movies.
 * - Sort movies by various attributes using different sorting algorithms:
 *      - Bubble sort
 *      - Merge sort
 *      - Selection sort
 *      - Quick sort
 * - Search movies by title, genre, director, or year using linear or binary search.
 * - Validate user inputs to ensure robustness and prevent invalid entries.
 * 
 * Classes:
 * - Movie: Represents a single movie entity with attributes like title, genre, director, and year.
 * - MovieDb: Manages a collection of Movie objects, providing methods to add, remove, sort, and search movies.
 * - DisplayMenu: Handles user interactions through a text-based menu system, allowing navigation through 
 *                different features and functionalities of the application.
 * 
 * Author: Fin Martinez, with help from ChatGPT and GitHub Copilot
 * Date: 9/26/2024
 * Version: 1.0
 */

/*
 * Debugging: so far so good...
 */

// Importing required libraries
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Main class
public class movieProject {
    public static void main(String[] args){
        MovieDb movieDb = new MovieDb();
        movieDb.loadFromCSV("movies_list.csv");
        DisplayMenu displayMenu = new DisplayMenu(movieDb);
        displayMenu.start();
    }
}

// Display Menu and main user interaction
class DisplayMenu {
    private Scanner scanner;
    private MovieDb movieDb;
    // Manager password (no peeking!)
    private final String managerPassword = "password";

    public DisplayMenu(MovieDb movieDb){
        this.scanner = new Scanner(System.in);
        this.movieDb = movieDb;
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
            System.out.println("6. Exit");
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
                    movieDb.displayAllMovies();
                    break;
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
            System.out.println("4. Exit");
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
                    movieDb.displayAllMovies();
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

        System.out.println("Binary search? (true/false)");
        boolean binarySearch = getBoolInput();

        System.out.println("Sort ascending? (true/false)");
        boolean ascending = getBoolInput();
        scanner.nextLine();

        List<Movie> results = movieDb.searchMovies(query, attribute, binarySearch, ascending);
        if (results.isEmpty()){
            System.err.println("No results found.");
        } else {
            for (Movie movie : results){
                System.out.println(movie);
            }
        }
    }

    private void sortMovies(){
        System.out.println("Enter an attribute to sort by (title, genre, director, year): ");
        String attribute = getAttrInput();

        System.out.println("Enter a sort type (bubble, merge, selection, quick): ");
        String sortType = getSortInput();

        System.out.println("Sort ascending? (true/false)");
        boolean ascending = getBoolInput();
        scanner.nextLine();

        movieDb.sortMovies(attribute, sortType, ascending);
        System.out.println("Movies sorted successfully.");

        //Display newly sorted movies
        System.out.println("Results of movies sorted by " + sortType + " sort:");
        movieDb.displayAllMovies();
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
    private List<Movie> movieList;
    private Set<String> movieTitles = new HashSet<>();
    //May need more, depending...

    public MovieDb(){
        this.movieList = new ArrayList<>();
    }

    public void loadFromCSV(String filePath){

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                System.out.println("Reading line: " + line);
                String[] values = parseCSVLine(line);
                System.out.println("Values: " + Arrays.toString(values));
                if (values.length == 4){
                    String title = values[0].trim();
                    String genre = values[1].trim();
                    String director = values[2].trim();
                    String yearStr = values[3].trim();
                    try {
                        int year = Integer.parseInt(yearStr);
                        if (!movieTitles.contains(title)){
                            movieList.add(new Movie(title, genre, director, year));
                            movieTitles.add(title);
                        } else {
                            System.err.println("Duplicate movie title: " + title);
                        }
                    } catch (NumberFormatException e){
                        System.err.println("Invalid year format: " + yearStr);
                    }
                } else {
                    System.err.println("Invalid .csv format: " + line);

                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred:" + e.getMessage());
        }
    }

    private String[] parseCSVLine(String line){

        List<String> values = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|([^,]+)").matcher(line);
        while (matcher.find()){
            if (matcher.group(1) != null){
                values.add(matcher.group(1));
            } else {
                values.add(matcher.group(2));
            }
        }
        return values.toArray(new String[0]);
    }

    public void addMovie(Movie movie){
        movieList.add(movie);
    }

    public void removeMovie(String title){
        movieList.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

    public List<Movie> searchMovies(String query, String attribute, boolean binarySearch, boolean ascending){
        long startTime = System.nanoTime();

        List<Movie> results = new ArrayList<>();
        switch (attribute.toLowerCase()) {
            case "title":
                results = searchByTitle(query, binarySearch, ascending);
                break;
            case "genre":
                results = searchByGenre(query, binarySearch, ascending);
                break;
            case "director":
                results = searchByDirector(query, binarySearch, ascending);
                break;
            case "year":
                results = searchByYear(Integer.parseInt(query), binarySearch, ascending);
                break;
            default:
                System.out.println("Invalid attribute. Please try again.");
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println("Search time: " + duration + "ms");

        return results;
    }

    private List<Movie> searchByTitle(String title, boolean binarySearch, boolean ascending){
        sortMovies("title", "merge", ascending);
        return binarySearch ? binarySearchByTitle(title) : linearSearchByTitle(title);
    }

    private List<Movie> searchByGenre(String genre, boolean binarySearch, boolean ascending){
        sortMovies("genre", "merge", ascending);
        return binarySearch ? binarySearchByGenre(genre) : linearSearchByGenre(genre);
    }

    private List<Movie> searchByDirector(String director, boolean binarySearch, boolean ascending){
        sortMovies("director", "merge", ascending);
        return binarySearch ? binarySearchByDirector(director) : linearSearchByDirector(director);
    }

    private List<Movie> searchByYear(int year, boolean binarySearch, boolean ascending){
        sortMovies("year", "merge", ascending);
        return binarySearch ? binarySearchByYear(year) : linearSearchByYear(year);
    }

    private List<Movie> linearSearchByTitle(String title){
        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList){
            if (movie.getTitle().equalsIgnoreCase(title)){
                results.add(movie);
            }
        }
        return results;
    }

    private List<Movie> binarySearchByTitle(String title){

        List<Movie> results = new ArrayList<>();
        int index = Collections.binarySearch(movieList, new Movie(title, "", "", 0), Comparator.comparing(Movie::getTitle, String.CASE_INSENSITIVE_ORDER));
        if (index >= 0){
            results.add(movieList.get(index));
        }
        return results;
    }

    private List<Movie> linearSearchByGenre(String genre){

        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList){
            if (movie.getGenre().equalsIgnoreCase(genre)){
                results.add(movie);
            }
        }
        return results;
    }

    private List<Movie> binarySearchByGenre(String genre){

        List<Movie> results = new ArrayList<>();
        int index = Collections.binarySearch(movieList, new Movie("", genre, "", 0), Comparator.comparing(Movie::getGenre, String.CASE_INSENSITIVE_ORDER));
        if (index >= 0){
            results.add(movieList.get(index));
        }
        return results;
    }

    private List<Movie> linearSearchByDirector(String director){

        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList){
            if (movie.getDirector().equalsIgnoreCase(director)){
                results.add(movie);
            }
        }
        return results;
    }

    private List<Movie> binarySearchByDirector(String director){

        List<Movie> results = new ArrayList<>();
        int index = Collections.binarySearch(movieList, new Movie("", "", director, 0), Comparator.comparing(Movie::getDirector, String.CASE_INSENSITIVE_ORDER));
        if (index >= 0){
            results.add(movieList.get(index));
        }
        return results;
    }

    private List<Movie> linearSearchByYear(int year){

        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList){
            if (movie.getYear() == year){
                results.add(movie);
            }
        }
        return results;
    }

    private List<Movie> binarySearchByYear(int year){

        List<Movie> results = new ArrayList<>();
        int index = Collections.binarySearch(movieList, new Movie("", "", "", year), Comparator.comparing(Movie::getYear));
        if (index >= 0){
            results.add(movieList.get(index));
        }
        return results;
    }

    public void sortMovies(String attribute, String sortType, boolean ascending){
        long startTime = System.nanoTime();

        Comparator<Movie> comparator;
        switch (attribute.toLowerCase()) {
            case "title":
                comparator = Comparator.comparing(Movie::getTitle, String.CASE_INSENSITIVE_ORDER);
                break;
            case "genre":
                comparator = Comparator.comparing(Movie::getGenre, String.CASE_INSENSITIVE_ORDER);
                break;
            case "director":
                comparator = Comparator.comparing(Movie::getDirector, String.CASE_INSENSITIVE_ORDER);
                break;
            case "year":
                comparator = Comparator.comparing(Movie::getYear);
                break;
            default:
                System.err.println("Invalid attribute. Please try again.");
                return;
        }
        if (!ascending){
            comparator = comparator.reversed();
        }

        switch (sortType.toLowerCase()) {
            case "bubble":
                bubbleSort(comparator);
                break;
            case "merge":
                mergeSort(0, movieList.size() - 1, comparator);
                break;
            case "selection":
                selectionSort(comparator);
                break;
            case "quick":
                quickSort(0, movieList.size() - 1, comparator);
                break;
            default:
                System.err.println("Invalid sort type. Please try again.");
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println("Sort time: " + duration + "ms");
    }

    private void bubbleSort(Comparator<Movie> comparator){

        int n = movieList.size();
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (comparator.compare(movieList.get(j), movieList.get(j + 1)) > 0){
                    Collections.swap(movieList, j, j + 1);
                }
            }
        }
    }

    private void mergeSort(int left, int right, Comparator<Movie> comparator){

        if (left < right){
            int middle = (left + right) / 2;
            mergeSort(left, middle, comparator);
            mergeSort(middle + 1, right, comparator);
            merge(left, middle, right, comparator);
        }
    }

    private void merge(int left, int middle, int right, Comparator<Movie> comparator){

        List<Movie> leftList = new ArrayList<>(movieList.subList(left, middle + 1));
        List<Movie> rightList = new ArrayList<>(movieList.subList(middle + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()){
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0){
                movieList.set(k++, leftList.get(i++));
                i++;
            } else {
                movieList.set(k++, rightList.get(j++));
            }

        }

        while (i < leftList.size()){
            movieList.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()){
            movieList.set(k++, rightList.get(j++));
        }
    }

    private void selectionSort(Comparator<Movie> comparator){

        int n = movieList.size();
        for (int i = 0; i < n - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < n; j++){
                if (comparator.compare(movieList.get(j), movieList.get(minIndex)) < 0){
                    minIndex = j;
                }
            }
            Collections.swap(movieList, i, minIndex);
        }
    }

    private void quickSort(int low, int high, Comparator<Movie> comparator){

        if (low < high){
            int pi = partition(low, high, comparator);
            quickSort(low, pi - 1, comparator);
            quickSort(pi + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<Movie> comparator){
        
        Movie pivot = movieList.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++){
            if (comparator.compare(movieList.get(j), pivot) < 0){
                i++;
                Collections.swap(movieList, i, j);
            }
        }
        Collections.swap(movieList, i + 1, high);
        return i + 1;
    }

    public void displayAllMovies(){
        for (Movie movie : movieList){
            System.out.println(movie);
        }
    }
}
