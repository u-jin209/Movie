package viewer;

import controller.MemberController;
import model.MemberDTO;
import model.RankDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberViewer {

    private final Scanner SCANNER;
    private MemberController memberController;

    private MemberDTO logIn = null;
    private MovieViewer movieViewer;
    private RankViewer rankViewer;
    private String[] grade = {"일반관람객","전문 평론가", "관리자"};
    private CinemaViewer cinemaViewer;
    private ShowViewer showViewer;


    public MemberViewer(Scanner scanner) {
        SCANNER = scanner;
        memberController = new MemberController();
    }

    public void showIndex() {
        String message =
                "1. 회원가입\n" +
                "2. 로그인" ;
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                register();
            }
            else if (userChoice == 2) {
                auth();
                if (logIn != null) {
                    System.out.println(logIn.getNickname()+"님 환영합니다 !");
                    movieViewer.setLogIn(logIn);
                    rankViewer.setLogIn(logIn);
                    cinemaViewer.setLogIn(logIn);
                    showViewer.setLogIn(logIn);

                    showMenu();
                }
            }
        }
    }

    public void showMenu() {

        String message =
                "1. 영화 정보 보기\n2. 극장 정보 보기\n3. 로그아웃\n"+
                "4. 회원 정보 관리\n5. 종료";

        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                movieViewer.showMovieMenu();
            } else if (userChoice == 2) {
                cinemaViewer.showCinemaMenu();
            } else if (userChoice == 3) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
                showIndex();
            } else if (userChoice ==4) {
                printOne();
            } else if (userChoice ==5) {
                System.out.println("사용해주셔서 감사합니다.");
                logIn = null;
                showIndex();
                break;
            }
        }
    }

    private void printOne() {

        System.out.println("회원 번호: " + logIn.getMemberId());
        System.out.println("회원 닉네임: " + logIn.getNickname());
        System.out.println("회원 등급: " + logIn.getGrade());
        System.out.println("---------------------------------------------");
        String message = "1.수정     2.탈퇴   3.(관리자)회원 등급 변경   4.뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            delete();
        } else if (userChoice == 3) {
            if (logIn.getGrade() == "관리자") {
                memberPrint();
                message = "등급을 변경하실 사용자 아이디를 입력해 주세요.";
                int id = ScannerUtil.nextInt(SCANNER,message);

                MemberDTO m = memberController.selectOne(id);
                if (m != null && m.getMemberId() == id) {
                    message = "새로운 등급을 입력해주세요.\n" +
                                "1. 일반관람객   2. 전문평론가    3.관리자";
                    int newgrade = ScannerUtil.nextInt(SCANNER,message);
                    m.setGrade(grade[newgrade-1]);
                    memberController.update(m);
                    System.out.println("등급이 설정되었습니다.\n");
                    printOne();
                }
            }else{
                System.out.println("권한이 없습니다.\n");

                printOne();
            }

        } else if (userChoice ==4){
            showMenu();
        }
    }

    private void memberPrint() {
        ArrayList<MemberDTO> memberList = memberController.selectAll();
        if (memberList.isEmpty()){
            System.out.println("등록된 유저가 없습니다.\n");
        }else{
            for (MemberDTO m: memberList){
                System.out.printf("%d| 닉네임 : %s| 아이디 : %s | 등급 : %s\n",
                        m.getMemberId(),m.getNickname(),m.getUserName(),m.getGrade());
            }
        }
    }

    private void register() {

        String message;
        message = "사용하실 아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        while (!memberController.validateUsername(username)) {
            System.out.println("해당 아이디는 사용하실 수 없습니다.");
            message = "사용하실 아이디나 뒤로 가실려면 \"X\"를 입력해주세요.";
            username = ScannerUtil.nextLine(SCANNER, message);

            if (username.equalsIgnoreCase("X")) {
                break;
            }
        }

        if(!username.equalsIgnoreCase("X")){
        MemberDTO m = new MemberDTO();
        m.setUserName(username);

        message = "사용하실 비밀번호를 입력해주세요.";
        m.setPassword(ScannerUtil.nextLine(SCANNER, message));

        message = "사용하실 닉네임을 입력해주세요.";
        m.setNickname(ScannerUtil.nextLine(SCANNER, message));
        m.setGrade("일반관람객");

        memberController.insert(m);
    }
}


    private void auth() {
        String message;
        message = "아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀번호를 입력해주세요.";
        String password = ScannerUtil.nextLine(SCANNER, message);

        logIn = memberController.auth(username, password);

        if (logIn == null) {
            System.out.println("로그인 정보가 정확하지 않습니다.");
        }
    }
    private void update() {

        String message = "새로운 비밀번호를 입력해주세요.";
        String newPassword = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 닉네임을 입력해주세요.";
        String newNickname = ScannerUtil.nextLine(SCANNER, message);

        message = "기존 비밀번호를 입력해주세요.";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);

        if (logIn.getPassword().equals(oldPassword)) {
            logIn.setNickname(newNickname);
            logIn.setPassword(newPassword);

            memberController.update(logIn);
        } else {
            System.out.println("회원 정보 변경에 실패하였습니다.");
        }
    }

    private void delete() {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            message = "비밀번호를 입력해주세요.";
            String password = ScannerUtil.nextLine(SCANNER, message);

            if (password.equals(logIn.getPassword())) {
                memberController.delete(logIn.getMemberId());
                logIn = null;
            }
        }
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    public void setRankViewer(RankViewer rankViewer) {
        this.rankViewer = rankViewer;
    }

    public void setCinemaViewer(CinemaViewer cinemaViewer) {
        this.cinemaViewer = cinemaViewer;
    }

    public void setShowViewer(ShowViewer showViewer) {
        this.showViewer = showViewer;
    }
}
