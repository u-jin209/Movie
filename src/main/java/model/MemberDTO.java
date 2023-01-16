package model;

import java.awt.desktop.ScreenSleepEvent;

public class MemberDTO {

    private int memberId;
    private String userName;

    private String password;

    private String nickname;
    private String grade;
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean equals(Object o){
        if(o instanceof MemberDTO){
            MemberDTO m = (MemberDTO) o;
            return memberId ==  m.memberId;
        }
        return false;
    }

    public MemberDTO(MemberDTO origin){
        memberId = origin.memberId;
        userName = origin.userName;
        password =origin.password;
        nickname = origin.nickname;
        grade = origin.grade;
    }

    public MemberDTO(){

    }

}