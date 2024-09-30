package movieDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieSearch {
    MovieDb movieDb = new MovieDb();
    ArrayList<Movie> movieList = movieDb.getMovies();

    public static ArrayList<Movie> searchMovies(ArrayList<Movie> movieList, String query, String attribute, boolean binarySearch, boolean ascending){

        ArrayList<Movie> results = new ArrayList<>();
        switch (attribute.toLowerCase()) {
            case "title":
                results = searchByTitle(movieList, query, binarySearch, ascending);
                break;
            case "genre":
                results = searchByGenre(movieList, query, binarySearch, ascending);
                break;
            case "director":
                results = searchByDirector(movieList, query, binarySearch, ascending);
                break;
            case "year":
                results = searchByYear(movieList, Integer.parseInt(query), binarySearch, ascending);
                break;
            default:
                System.out.println("Invalid attribute. Please try again.");
        }

        return results;
    }

    private static ArrayList<Movie> searchByTitle(ArrayList<Movie> movieList, String title, boolean binarySearch, boolean ascending){
        return binarySearch ? binarySearchByTitle(movieList, title) : linearSearchByTitle(movieList, title);
    }

    private static ArrayList<Movie> searchByGenre(ArrayList<Movie> movieList, String genre, boolean binarySearch, boolean ascending){
        return binarySearch ? binarySearchByGenre(movieList, genre) : linearSearchByGenre(movieList, genre);
    }

    private static ArrayList<Movie> searchByDirector(ArrayList<Movie> movieList, String director, boolean binarySearch, boolean ascending){
        return binarySearch ? binarySearchByDirector(movieList, director) : linearSearchByDirector(movieList, director);
    }

    private static ArrayList<Movie> searchByYear(ArrayList<Movie> movieList, int year, boolean binarySearch, boolean ascending){
        return binarySearch ? binarySearchByYear(movieList, year) : linearSearchByYear(movieList, year);
    }

    private static ArrayList<Movie> linearSearchByTitle(ArrayList<Movie> movieList, String title){
        ArrayList<Movie> results = new ArrayList<>();
        for (Movie movie : movieList){
            if (movie.getTitle().equalsIgnoreCase(title)){
                results.add(movie);
            }
        }
        return results;
    }

    private static ArrayList<Movie> binarySearchByTitle(ArrayList<Movie> movieList, String title){

        ArrayList<Movie> binarySorting = new ArrayList<>(movieList);
        ArrayList<Movie> results = new ArrayList<>();

        Comparator<Movie> comparator = Comparator.comparing(Movie::getTitle, String.CASE_INSENSITIVE_ORDER);
        MovieSort.quickSort(0, binarySorting.size() - 1, comparator, binarySorting);

        int index = Collections.binarySearch(binarySorting, new Movie(title, "", "", 0), comparator);
        if (index >= 0){
            //Find all movies with the same title
            int left = index;
            while (left >= 0 && binarySorting.get(left).getTitle().equalsIgnoreCase(title)){
                results.add(binarySorting.get(left));
                left--;
            }
            int right = index + 1;
            while (right < binarySorting.size() && binarySorting.get(right).getTitle().equalsIgnoreCase(title)){
                results.add(binarySorting.get(right));
                right++;
            }
        }
        return results;
    }

    private static ArrayList<Movie> linearSearchByGenre(ArrayList<Movie> movieList, String genre){

        ArrayList<Movie> results = new ArrayList<>();
        for (Movie movie : movieList){
            if (movie.getGenre().equalsIgnoreCase(genre)){
                results.add(movie);
            }
        }
        return results;
    }

    private static ArrayList<Movie> binarySearchByGenre(ArrayList<Movie> movieList, String genre){

        ArrayList<Movie> binarySorting = new ArrayList<>(movieList);
        ArrayList<Movie> results = new ArrayList<>();

        Comparator<Movie> comparator = Comparator.comparing(Movie::getGenre, String.CASE_INSENSITIVE_ORDER);
        MovieSort.quickSort(0, binarySorting.size() - 1, comparator, binarySorting);

        int index = Collections.binarySearch(binarySorting, new Movie("", genre, "", 0), comparator);
        if (index >= 0){
            //Find all movies with the same genre
            int left = index;
            while (left >= 0 && binarySorting.get(left).getGenre().equalsIgnoreCase(genre)){
                results.add(binarySorting.get(left));
                left--;
            }
            int right = index + 1;
            while (right < binarySorting.size() && binarySorting.get(right).getGenre().equalsIgnoreCase(genre)){
                results.add(binarySorting.get(right));
                right++;
            }
        }
        return results;
    }

    private static ArrayList<Movie> linearSearchByDirector(ArrayList<Movie> movieList, String director){

        ArrayList<Movie> results = new ArrayList<>();
        for (Movie movie : movieList){
            if (movie.getDirector().equalsIgnoreCase(director)){
                results.add(movie);
            }
        }
        return results;
    }

    private static ArrayList<Movie> binarySearchByDirector(ArrayList<Movie> movieList, String director){

        ArrayList<Movie> binarySorting = new ArrayList<>(movieList);
        ArrayList<Movie> results = new ArrayList<>();

        Comparator<Movie> comparator = Comparator.comparing(Movie::getDirector, String.CASE_INSENSITIVE_ORDER);
        MovieSort.quickSort(0, binarySorting.size() - 1, comparator, binarySorting);

        int index = Collections.binarySearch(binarySorting, new Movie("", "", director, 0), comparator);
        if (index >= 0){
            //Find all movies with the same director
            int left = index;
            while (left >= 0 && binarySorting.get(left).getDirector().equalsIgnoreCase(director)){
                results.add(binarySorting.get(left));
                left--;
            }
            int right = index + 1;
            while (right < binarySorting.size() && binarySorting.get(right).getDirector().equalsIgnoreCase(director)){
                results.add(binarySorting.get(right));
                right++;
            }
        }
        return results;
    }

    private static ArrayList<Movie> linearSearchByYear(ArrayList<Movie> movieList, int year){

        ArrayList<Movie> results = new ArrayList<>();
        for (Movie movie : movieList){
            if (movie.getYear() == year){
                results.add(movie);
            }
        }
        return results;
    }

    private static ArrayList<Movie> binarySearchByYear(ArrayList<Movie> movieList, int year){

        ArrayList<Movie> binarySorting = new ArrayList<>(movieList);
        ArrayList<Movie> results = new ArrayList<>();

        Comparator<Movie> comparator = Comparator.comparing(Movie::getYear);
        MovieSort.quickSort(0, binarySorting.size() - 1, comparator, binarySorting);

        int index = Collections.binarySearch(binarySorting, new Movie("", "", "", year), comparator);
        if (index >= 0){
            //Find all movies with the same year
            int left = index;
            while (left >= 0 && binarySorting.get(left).getYear() == year){
                results.add(binarySorting.get(left));
                left--;
            }
            int right = index + 1;
            while (right < binarySorting.size() && binarySorting.get(right).getYear() == year){
                results.add(binarySorting.get(right));
                right++;
            }
        }
        return results;
    }
}
