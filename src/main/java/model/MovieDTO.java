package model;

public class MovieDTO {

    private int movieId;
    private String movieName;

    private String movieContent;
    private String movieGrade;
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieContent() {
        return movieContent;
    }

    public void setMovieContent(String movieContent) {
        this.movieContent = movieContent;
    }

    public String getMovieGrade() {
        return movieGrade;
    }

    public void setMovieGrade(String movieGrade) {
        this.movieGrade = movieGrade;
    }

    public boolean equals(Object o){
        if(o instanceof MovieDTO){
            MovieDTO m = (MovieDTO) o;
            return movieId ==  m.movieId;
        }
        return false;
    }

    public MovieDTO(MovieDTO origin){
        movieId = origin.movieId;
        movieName = origin.movieName;
        movieContent =origin.movieContent;
        movieGrade = origin.movieGrade;
    }

    public MovieDTO(){

    }


}
