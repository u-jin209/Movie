package model;

public class ShowDTO {
    private int showId;
    private int movieId;
    private int cinemaId;
    private String showTime;

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int memberId) {
        this.movieId = memberId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }


    public boolean equals(Object o) {
        if( o instanceof ShowDTO){
            ShowDTO s = (ShowDTO) o;
            return showId == s.showId;
        }
        return false;
    }

    public ShowDTO(ShowDTO origin){
        showId = origin.showId;
        movieId =origin.movieId;
        cinemaId =origin.cinemaId;
        showTime = origin.showTime;
    }

    public ShowDTO(){

    }
}
