package viewer;

import controller.ShowController;
import model.MemberDTO;
import model.MovieDTO;
import model.RankDTO;
import model.ShowDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowViewer {
    private final Scanner SCANNER;
    private ShowController showController;
    private MemberDTO logIn;
    private CinemaViewer cinemaViewer;
    private MovieViewer movieViewer;
    private RankViewer rankViewer;
    private MemberViewer memberViewer;

    public ShowViewer(Scanner scanner) {
        SCANNER = scanner;
        showController = new ShowController();
    }

    public void showCinemaMovie() {

        ArrayList<ShowDTO> showlist = showController.selectAll();

        if (showlist.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
            managerMenu();


        } else {
            for (ShowDTO s : showlist) {
                System.out.println("======================================================");
                System.out.printf("%d \n", s.getMovieId());
                System.out.println("------------------------------------------------------");

                System.out.println("======================================================");
            }
        }


    }

    private void ShowManager() {

        String message =
                "1. 상영중인 영화등록\n" +
                "2. 상영중인 영화 정보 수정\n" +
                "3. 상영중인 영화 정보 삭제\n"+
                "4. 종료";

        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                writeShow();
            } else if (userChoice == 2) {
                updateShow();
            } else if (userChoice == 3) {
                deleteShow();
            }else if (userChoice==4){
                cinemaViewer.showCinemaMenu();
            }

        }
    }

    private void deleteShow() {

        showCinemaMovie();
        System.out.println("--------------------------------------------------");
        String message = "삭제할 영화시간의 아이디를 입력해 주세요";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        ShowDTO s = showController.selectOne(userChoice);
        if (s != null) {
            message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("Y")) {
                showController.delete(userChoice);
            }
        }
    }

    private void updateShow() {
        showCinemaMovie();
        System.out.println("--------------------------------------------------");
        ShowDTO s = new ShowDTO();
        String message = "변경할 영화관을 입력해주세요.";
        s.setCinemaId(ScannerUtil.nextInt(SCANNER,message));
        movieViewer.printMovie(2);
        message = " 변경할 영화를 입력해주세요.";
        s.setMovieId(ScannerUtil.nextInt(SCANNER,message));
        message = "새로운 영화 시간을 입력해주세요.";
        s.setShowTime(ScannerUtil.nextLine(SCANNER,message));


        showController.add(s);
    }

    private void writeShow() {
        showCinemaMovie();
        System.out.println("--------------------------------------------------");
        ShowDTO s = new ShowDTO();
        String message = "등록할 영화관을 입력해주세요.";
        s.setCinemaId(ScannerUtil.nextInt(SCANNER,message));
        movieViewer.printMovie(2);
        message = "등록할 영화를 입력해주세요.";
        s.setMovieId(ScannerUtil.nextInt(SCANNER,message));
        message = "영화 시간을 입력해주세요.";
        s.setShowTime(ScannerUtil.nextLine(SCANNER,message));


        showController.add(s);


    }


    public void managerMenu(){

        String message = "   1. 상영중인 영화 정보 관리\n" +
                "   2. 뒤로 가기";
        int choice = ScannerUtil.nextInt(SCANNER, message);
        if (choice == 1) {
            while (logIn != null) {
                if (logIn.getGrade() != "관리자") {
                    System.out.println("접근권한이 없습니다.");
                    cinemaViewer.showCinemaMenu();
                } else {
                    ShowManager();
                }
            }
        } else if (choice == 2) {
            cinemaViewer.showCinemaMenu();
        }
    }

    public void setLogIn(MemberDTO logIn) {
        this.logIn = logIn;
    }

    public void setMemberViewer(MemberViewer memberViewer) {
        this.memberViewer = memberViewer;
    }

    public void setRankViewer(RankViewer rankViewer) {
        this.rankViewer = rankViewer;
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    public void setCinemaViewer(CinemaViewer cinemaViewer) {
        this.cinemaViewer = cinemaViewer;
    }
}