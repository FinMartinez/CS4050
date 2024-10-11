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
        return this.genre.trim().compareTo(other.genre.trim().toLowerCase());
    }

    public int compareByDirector(Movie other){
        return this.director.trim().compareTo(other.director.trim().toLowerCase());
    }

    public int compareByYear(Movie other){
        return Integer.compare(this.year, other.year);
    }

    @Override
    public String toString(){
        return "Title: " + title + ", Genre: " + genre + ", Director: " + director + ", Year: " + year;
    }

    //Dummy movie methods
    public void setTitle(String title){
        this.title = title;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public void setYear(int year){
        this.year = year;
    }

}
