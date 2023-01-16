package controller;

import model.MemberDTO;
import model.RankDTO;

import java.util.ArrayList;

public class MemberController {
    private ArrayList<MemberDTO> memberList;
    private int nextId;

    public MemberController(){
        memberList = new ArrayList<>();
        nextId = 1;

        MemberDTO m = new MemberDTO();
        m.setMemberId(1);
        m.setGrade("관리자");
        m.setNickname("관리자1");
        m.setPassword("a");
        m.setUserName("a");

        add(m);

    }

    private void add(MemberDTO m) {
        m.setMemberId(nextId++);
        memberList.add(m);
    }


    public boolean validateUsername(String username) {
        if (username.equalsIgnoreCase("X")){
            return false;
        }
        for (MemberDTO m :memberList){
            if(username.equalsIgnoreCase(m.getNickname())){
                return false;
            }
        }
        return true;
    }

    public ArrayList<MemberDTO> selectAll() {
        ArrayList<MemberDTO> temp = new ArrayList<>();
        for (MemberDTO m: memberList){
            temp.add(new MemberDTO(m));
        }

        return temp;
    }

    public MemberDTO selectOne(int id) {
        MemberDTO m = new MemberDTO();
        m.setMemberId(id);
        if (memberList.contains(m)) {
            return new MemberDTO(memberList.get(memberList.indexOf(m)));

        } else {
            return null;
        }
    }



    public MemberDTO auth(String username, String password) {
        for(MemberDTO m :memberList){
            if(username.equalsIgnoreCase(m.getUserName()) && password.equalsIgnoreCase(m.getPassword())){
                return new MemberDTO(m);
            }
        }
    return null;
    }
    public void delete(int id) {
        MemberDTO m = new MemberDTO();
        m.setMemberId(id);
        memberList.remove(m);
    }

    public void update(MemberDTO m) {
        memberList.set(memberList.indexOf(m),m);
    }
    public void insert(MemberDTO m) {
        m.setMemberId(nextId++);
        memberList.add(m);
    }
}
