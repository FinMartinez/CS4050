package movieDatabase;

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
