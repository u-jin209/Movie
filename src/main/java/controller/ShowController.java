package controller;


import model.ShowDTO;

import java.util.ArrayList;

public class ShowController {
    private ArrayList<ShowDTO> showList;
    private int nextId;

    public ShowController() {
        showList = new ArrayList<>();
        nextId =1;
    }

    public void add(ShowDTO m) {
        m.setMovieId(nextId++);
        showList.add(m);

    }

    public void update(ShowDTO s) {
        showList.set(showList.indexOf(s),s);
    }
    public void delete(int id) {
        ShowDTO s = new ShowDTO();
        s.setMovieId(id);
        showList.remove(s);
    }


    public ArrayList<ShowDTO> selectAll() {
        ArrayList<ShowDTO> temp = new ArrayList<>();
        for (ShowDTO s : showList){
            temp.add(new ShowDTO(s));
        }

        return temp;
    }

    public ShowDTO selectOne(int id) {
        ShowDTO s = new ShowDTO();
        s.setMovieId(id);
        if (showList.contains(s)) {
            return new ShowDTO(showList.get(showList.indexOf(s)));

        } else {
            return null;
        }
    }
}
