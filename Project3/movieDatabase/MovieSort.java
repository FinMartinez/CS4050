package movieDatabase;

import java.util.*;
public class MovieSort {
    MovieDb movieDb = new MovieDb();
    ArrayList<Movie> movieList = movieDb.getMovies();

    public static ArrayList<Movie> sortMovies(ArrayList<Movie> movieList, String attribute, String sortType, boolean ascending){

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
                return movieList;
        }
        if (!ascending){
            comparator = comparator.reversed();
        }
        long startTime = System.currentTimeMillis();

        switch (sortType.toLowerCase()) {
            case "bubble":
                bubbleSort(movieList, comparator);
                break;
            case "merge":
                mergeSort(movieList, 0, movieList.size() - 1, comparator);
                break;
            case "selection":
                selectionSort(movieList, comparator);
                break;
            case "quick":
                quickSort(0, movieList.size() - 1, comparator, movieList);
                break;
            default:
                System.err.println("Invalid sort type. Please try again.");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Processing time: " + (endTime - startTime) + " ms");

        return movieList;
    }

    private static void bubbleSort(ArrayList<Movie> movieList, Comparator<Movie> comparator){

        int n = movieList.size();
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (comparator.compare(movieList.get(j), movieList.get(j + 1)) > 0){
                    Collections.swap(movieList, j, j + 1);
                }
            }
        }
    }

    private static void mergeSort(ArrayList<Movie> movieList, int left, int right, Comparator<Movie> comparator){

        if (left < right){
            int middle = (left + right) / 2;
            mergeSort(movieList, left, middle, comparator);
            mergeSort(movieList, middle + 1, right, comparator);
            merge(movieList, left, middle, right, comparator);
        }
    }

    private static void merge(ArrayList<Movie> movieList, int left, int middle, int right, Comparator<Movie> comparator){

        ArrayList<Movie> leftList = new ArrayList<>(movieList.subList(left, middle + 1));
        ArrayList<Movie> rightList = new ArrayList<>(movieList.subList(middle + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()){
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0){
                movieList.set(k++, leftList.get(i++));
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

    private static void selectionSort(ArrayList<Movie> movieList, Comparator<Movie> comparator){

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
    
    protected static void quickSort(int low, int high, Comparator<Movie> comparator, ArrayList<Movie> movieList){

        if (low < high){
            int pi = partition(low, high, comparator, movieList);
            quickSort(low, pi - 1, comparator, movieList);
            quickSort(pi + 1, high, comparator, movieList);
        }
    }

    private static int partition(int low, int high, Comparator<Movie> comparator, ArrayList<Movie> movieList){
        
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
}
