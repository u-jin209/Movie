package controller;



import model.CinemaDTO;
import model.MovieDTO;

import java.util.ArrayList;

public class CinemaController {
    private ArrayList<CinemaDTO> cinemaList;

    private int nextId;

    public CinemaController() {
        this.cinemaList = new ArrayList<>();
        nextId =1;
    }

    public void add(CinemaDTO c) {
        c.setCinemaId(nextId++);
        cinemaList.add(c);

    }

    public void update(CinemaDTO c) {
        cinemaList.set(cinemaList.indexOf(c),c);
    }
    public void delete(int id) {
        CinemaDTO c = new CinemaDTO();
        c.setCinemaId(id);
        cinemaList.remove(c);
    }


    public ArrayList<CinemaDTO> selectAll() {
        ArrayList<CinemaDTO> temp = new ArrayList<>();
        for (CinemaDTO c : cinemaList){
            temp.add(new CinemaDTO(c));
        }

        return temp;
    }

    public CinemaDTO selectOne(int id) {
        CinemaDTO c = new CinemaDTO();
        c.setCinemaId(id);
        if (cinemaList.contains(c)) {
            return new CinemaDTO(cinemaList.get(cinemaList.indexOf(c)));

        } else {
            return null;
        }
    }

}
