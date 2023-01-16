package model;

public class CinemaDTO {
    private int cinemaId;
    private String cinemaName;
    private String loction;
    private int cinemaCall;

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getLoction() {
        return loction;
    }

    public void setLoction(String loction) {
        this.loction = loction;
    }

    public int getCinemaCall() {
        return cinemaCall;
    }

    public void setCinemaCall(int cinemaCall) {
        this.cinemaCall = cinemaCall;
    }


    public boolean equals(Object o) {
        if(o instanceof CinemaDTO){
            CinemaDTO c = (CinemaDTO) o;
            return cinemaId == c.cinemaId;
        }
        return false;
    }

    public CinemaDTO(CinemaDTO origin){
        cinemaId = origin.cinemaId;
        cinemaName = origin.cinemaName;
        loction = origin.loction;
        cinemaCall = origin.cinemaCall;
    }

    public CinemaDTO(){

    }
}
