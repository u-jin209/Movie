package main;

import viewer.*;

import java.util.Scanner;

public class run {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberViewer memberViewer = new MemberViewer(scanner);
        MovieViewer movieViewer = new MovieViewer(scanner);
        RankViewer rankViewer = new RankViewer(scanner);
        CinemaViewer cinemaViewer = new CinemaViewer(scanner);
        ShowViewer showViewer = new ShowViewer(scanner);

        memberViewer.setMovieViewer(movieViewer);
        memberViewer.setRankViewer(rankViewer);
        memberViewer.setCinemaViewer(cinemaViewer);
        memberViewer.setShowViewer(showViewer);

        movieViewer.setMemberViewer(memberViewer);
        movieViewer.setRankViewer(rankViewer);
        movieViewer.setCinemaViewer(cinemaViewer);
        movieViewer.setShowViewer(showViewer);

        rankViewer.setMemberViewer(memberViewer);
        rankViewer.setMovieViewer(movieViewer);
        rankViewer.setCinemaViewer(cinemaViewer);
        rankViewer.setShowViewer(showViewer);

        cinemaViewer.setMemberViewer(memberViewer);
        cinemaViewer.setRankViewer(rankViewer);
        cinemaViewer.setMovieViewer(movieViewer);
        cinemaViewer.setShowViewer(showViewer);

        showViewer.setMemberViewer(memberViewer);
        showViewer.setRankViewer(rankViewer);
        showViewer.setMovieViewer(movieViewer);
        showViewer.setCinemaViewer(cinemaViewer);

        memberViewer.showIndex();

    }
}
