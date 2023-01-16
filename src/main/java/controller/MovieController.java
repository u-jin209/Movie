package controller;


import model.MovieDTO;

import java.util.ArrayList;

public class MovieController {
    private ArrayList<MovieDTO> movieList;
    private int nextId;

    public MovieController() {
        movieList = new ArrayList<>();
        nextId =1;
    }

    public void add(MovieDTO m) {
        m.setMovieId(nextId++);
        movieList.add(m);

    }




    public void update(MovieDTO m) {
        movieList.set(movieList.indexOf(m),m);
    }
    public void delete(int id) {
        MovieDTO m = new MovieDTO();
        m.setMovieId(id);
        movieList.remove(m);
    }


    public ArrayList<MovieDTO> selectAll() {
        ArrayList<MovieDTO> temp = new ArrayList<>();
        for (MovieDTO m : movieList){
            temp.add(new MovieDTO(m));
        }

        return temp;
    }

    public MovieDTO selectOne(int id) {
        MovieDTO m = new MovieDTO();
        m.setMovieId(id);
        if (movieList.contains(m)) {
            return new MovieDTO(movieList.get(movieList.indexOf(m)));

        } else {
            return null;
        }
    }
}
