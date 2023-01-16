package controller;

import model.MovieDTO;
import model.RankDTO;

import java.util.ArrayList;

public class RankController {
    private ArrayList<RankDTO> rankList;
    private int nextId;

    public RankController() {
        rankList = new ArrayList<>();
        nextId =1;
    }

    public void add(RankDTO r) {
        r.setMovieId(nextId++);
        rankList.add(r);
    }

    public void update(RankDTO r) {
        rankList.set(rankList.indexOf(r),r);
    }
    public void delete(int id) {
        RankDTO r= new RankDTO();
        r.setMovieId(id);
        rankList.remove(r);
    }


    public ArrayList<RankDTO> selectAll() {
        ArrayList<RankDTO> temp = new ArrayList<>();
        for (RankDTO r : rankList){
            temp.add(new RankDTO(r));
        }

        return temp;
    }

    public RankDTO selectOne(int id) {
        RankDTO r = new RankDTO();
        r.setMovieId(id);
        if (rankList.contains(r)) {
            return new RankDTO(rankList.get(rankList.indexOf(r)));

        } else {
            return null;
        }
    }
}
