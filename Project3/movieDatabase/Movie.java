package movieDatabase;

class Movie implements Comparable<Movie> {
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
    public int compareTo(Movie other){
        return this.title.trim().toLowerCase().compareTo(other.title.trim().toLowerCase());
    }

    public int compareByGenre(Movie other){
        return genre.compareTo(other.genre);
    }

    public int compareByDirector(Movie other){
        return director.compareTo(other.director);
    }

    public int compareByYear(Movie other){
        return Integer.compare(this.year, other.year);
    }

    @Override
    public String toString(){
        return "Title: " + title + ", Genre: " + genre + ", Director: " + director + ", Year: " + year;
    }

}
