package model;

public class RankDTO {
    private int rankId;
    private int memberId;
    private int movieId;
    private String review;

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }


    public boolean equals(Object o){
        if(o instanceof RankDTO){
            RankDTO r = (RankDTO) o;
            return rankId ==  r.rankId;
        }
        return false;
    }

    public RankDTO(RankDTO origin){
        movieId = origin.movieId;
        rankId = origin.rankId;
        memberId =origin.memberId;
        review = origin.review;
    }

    public RankDTO(){

    }
}
