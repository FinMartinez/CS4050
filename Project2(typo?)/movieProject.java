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

/*
 * NEEDS TO BE DEBUGGED!!!!!
 */
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Collections;
import java.io.IOException;
import java.util.Scanner;

public class movieProject {
    public static void main(String[] args){
        MovieDb movieDb = new MovieDb();
        movieDb.loadFromCSV("movies.csv");
        DisplayMenu displayMenu = new DisplayMenu(movieDb);
        displayMenu.start();
    }
}

class DisplayMenu {
    private Scanner scanner;
    private MovieDb movieDb;

    public DisplayMenu(MovieDb movieDb){
        this.scanner = new Scanner(System.in);
        this.movieDb = movieDb;
    }
    public void start() {
        while(true){
            System.err.println("Please specify user mode or manager mode:");
            System.err.println("1. User mode");
            System.err.println("2. Manager mode");
            System.err.println("3. Exit");
            int choice = getIntPut(1, 3);
            if (choice == 3) break;

            switch (choice){
                case 1:
                    userMode();
                    break;
                case 2:
                    managerMode();
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }


    private void managerMode(){
        while(true){
            System.err.println("Please select an option:");
            System.err.println("1. Add a movie");
            System.err.println("2. Remove a movie");
            System.err.println("3. Search for a movie");
            System.err.println("4. Sort movies");
            System.err.println("5. Display all movies");
            System.err.println("6. Exit");
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
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private void userMode(){
        while(true){
            System.err.println("Please select an option:");
            System.err.println("1. Search for a movie");
            System.err.println("2. Sort movies");
            System.err.println("3. Display all movies");
            System.err.println("4. Exit");
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
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private void addMovie(){
        System.err.println("Enter the title of the movie: ");
        String title = scanner.nextLine();
        System.err.println("Enter the genre of the movie: ");
        String genre = scanner.nextLine();
        System.err.println("Enter the director of the movie: ");
        String director = scanner.nextLine();
        System.err.println("Enter the year the movie was released: ");
        int year = getIntPut(1888, 2100);

        Movie movie = new Movie(title, genre, director, year);
        movieDb.addMovie(movie);
        System.out.println("Movie added successfully.");
    }

    private void removeMovie(){
        System.err.println("Enter the title of the movie you would like to remove: ");
        String title = scanner.nextLine();
        movieDb.removeMovie(title);
        System.out.println("Movie removed successfully.");
    }

    private void searchMovie(){
        System.err.println("Enter an attribute to search by (title, genre, director, year): ");
        String attribute = scanner.nextLine();
        System.out.println("Enter a search query: ");
        String query = scanner.nextLine();
        System.out.println("Binary search? (true/false)");
        boolean binarySearch = scanner.nextBoolean();
        System.out.println("Sort ascending? (true/false)");
        boolean ascending = scanner.nextBoolean();
        scanner.nextLine();

        List<Movie> results = movieDb.searchMovies(query, attribute, binarySearch, ascending);
        if (results.isEmpty()){
            System.out.println("No results found.");
        } else {
            for (Movie movie : results){
                System.out.println(movie);
            }
        }
    }

    private void sortMovies(){
        System.err.println("Enter an attribute to sort by (title, genre, director, year): ");
        String attribute = scanner.nextLine();
        System.err.println("Enter a sort type (bubble(1), merge(2), selection(3), quick(4)): ");
        String sortType = scanner.nextLine();
        System.out.println("Sort ascending? (true/false)");
        boolean ascending = scanner.nextBoolean();
        scanner.nextLine();

        movieDb.sortMovies(attribute, sortType, ascending);
        System.out.println("Movies sorted successfully.");
    }

    private int getIntPut(int min, int max){
        int choice;
        while (true){
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max){
                    return choice;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
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


    public MovieDb(){
        this.movieList = new ArrayList<>();
    }

    public void loadFromCSV(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                movieList.add(new Movie(values[0], values[1], values[2], Integer.parseInt(values[3])));
            }
        } catch (IOException e) {
            System.out.println("An error occurred:" + e.getMessage());
        }
    }

    public void addMovie(Movie movie){
        movieList.add(movie);
    }

    public void removeMovie(String title){
        movieList.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

    public List<Movie> searchMovies(String query, String attribute, boolean binarySearch, boolean ascending){
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
                System.out.println("Invalid attribute. Please try again.");
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
                System.out.println("Invalid sort type. Please try again.");
        }
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

/*
    public void sortMovies(int choice){
        // Sort movies
        switch (choice) {
            case 1:
                System.err.println("How would you like to sort the movies?");
                System.err.println("1. Ascending");
                System.err.println("2. Descending");
                Scanner scanner = new Scanner(System.in);
                int sortChoice = scanner.nextInt();
                scanner.nextLine();
                if (sortChoice == 1){
                    movieList.sort((movie1, movie2) -> movie1.getTitle().compareTo(movie2.getTitle()));
                } else if (sortChoice == 2){
                    movieList.sort((movie1, movie2) -> movie2.getTitle().compareTo(movie1.getTitle()));
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
                break;
            case 2:
                System.err.println("How would you like to sort the movies?");
                System.err.println("1. Ascending");
                System.err.println("2. Descending");
                Scanner scanner2 = new Scanner(System.in);
                int sortChoice2 = scanner2.nextInt();
                scanner2.nextLine();
                if (sortChoice2 == 1){
                    movieList.sort((movie1, movie2) -> movie1.getGenre().compareTo(movie2.getGenre()));
                } else if (sortChoice2 == 2){
                    movieList.sort((movie1, movie2) -> movie2.getGenre().compareTo(movie1.getGenre()));
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
                break;
            case 3:
                System.err.println("How would you like to sort the movies?");
                System.err.println("1. Ascending");
                System.err.println("2. Descending");
                Scanner scanner3 = new Scanner(System.in);
                int sortChoice3 = scanner3.nextInt();
                scanner3.nextLine();
                if (sortChoice3 == 1){
                    movieList.sort((movie1, movie2) -> movie1.getDirector().compareTo(movie2.getDirector()));
                } else if (sortChoice3 == 2){
                    movieList.sort((movie1, movie2) -> movie2.getDirector().compareTo(movie1.getDirector()));
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
                break;
            case 4:
                System.err.println("How would you like to sort the movies?");
                System.err.println("1. Ascending");
                System.err.println("2. Descending");
                Scanner scanner4 = new Scanner(System.in);
                int sortChoice4 = scanner4.nextInt();
                scanner4.nextLine();
                if (sortChoice4 == 1){
                    movieList.sort((movie1, movie2) -> movie1.getYear() - movie2.getYear());
                } else if (sortChoice4 == 2){
                    movieList.sort((movie1, movie2) -> movie2.getYear() - movie1.getYear());
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
                break;
            default:
                break;
        }
    }

    public void displayAllMovies(){
        for (Movie movie : movieList){
            System.out.println(movie);
        }
    }
}
*/

/*
 * class MovieManager {

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
                        searchSelection();
                        break;
                    case 4:
                        // Sort movies
                        sortSelection();
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

    public void searchSelection(){
        // Search for a movie
        System.out.println("Enter a search keyword: ");
        Scanner scanner = new Scanner(System.in);
        String keyword = scanner.nextLine();
        MovieDb movieDb = new MovieDb();   
        movieDb.searchMovie(keyword);
        scanner.close();
    }

    public void sortSelection(){
        // Sort movies
        System.out.println("Please select a sorting criteria:");
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by genre");
        System.out.println("3. Sort by director");
        System.out.println("4. Sort by year");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Sort the movies
        MovieDb movieDb = new MovieDb();
        movieDb.sortMovies(choice);
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
 */