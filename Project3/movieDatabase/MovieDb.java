package movieDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MovieDb {
    private ArrayList<Movie> movies;

    private Set<String> movieTitles = new HashSet<>();
    //May need more, depending...

    public MovieDb(){
        movies = new ArrayList<Movie>();
    }

    public MovieDb(ArrayList<Movie> newMovies){
        movies = newMovies;
    }

    public ArrayList<Movie> getMovies(){
        //Return a copy of the list to prevent direct modification
        return new ArrayList<>(movies);
    }

    void clearMovies() {
        movies.clear();
        movieTitles.clear();
        System.out.println("Movie list and titles cleared.");
    }

    public void loadFromCSV(String filePath){
        //Clear existing movie list before loading new data
        clearMovies();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine){
                    isFirstLine = false;
                    continue;
                }
                //System.out.println("Reading line: " + line);
                String[] values = parseCSVLine(line);
                //System.out.println("Values: " + Arrays.toString(values));
                if (values.length == 4){
                    String title = values[0].trim();
                    String genre = values[1].trim();
                    String director = values[2].trim();
                    String yearStr = values[3].trim();
                    try {
                        int year = Integer.parseInt(yearStr);
                        if (!movieTitles.contains(title)){
                            movies.add(new Movie(title, genre, director, year));
                            movieTitles.add(title);
                        } else {
                            System.err.println("Duplicate detected, not adding movie: " + title);
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
        verifyNoDuplicates();
    }

    void verifyNoDuplicates() {
        Set<String> seenMovies = new HashSet<>();
        for (Movie movie : movies){
            if(!seenMovies.add(movie.getTitle())){
                System.err.println("Duplicate movie title: " + movie.getTitle());
            }
        }
        System.out.println("Duplicate check complete. Total unique movies: " + seenMovies.size());
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
        if(!movieTitles.contains(movie.getTitle())){
            movies.add(movie);
            movieTitles.add(movie.getTitle());
        } else {
            System.err.println("Duplicate movie title: " + movie.getTitle());
        }
    }

    public void removeMovie(String title){
        movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

}
