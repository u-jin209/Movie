package viewer;

import controller.CinemaController;
import controller.MovieController;
import model.CinemaDTO;
import model.MemberDTO;
import model.MovieDTO;
import model.RankDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class CinemaViewer {
    private final Scanner SCANNER;
    private CinemaController cinemaController;
    private MemberDTO logIn;
    private MemberViewer memberViewer;
    private RankViewer rankViewer;
    private MovieViewer movieViewer;
    private ShowViewer showViewer;
    private MovieController movieController;


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


    public CinemaViewer(Scanner scanner) {
        SCANNER = scanner;
        cinemaController = new CinemaController();

    }

    public void showCinemaMenu() {
        String message =
                "   1. 극장 정보 보기\n" +
                "   2. 상영중인 영화 보기\n" +
                "   3. 뒤로 가기";

        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                printCinema();
                managerMenu();
            } else if (userChoice == 2) {
                showViewer.showCinemaMovie();
                showViewer.managerMenu();
            } else if (userChoice == 3) {
                memberViewer.showMenu();
            }
        }
    }

    private void CinemaManager() {
        String message = "1. 극장 정보 등록\n" +
                        "2. 극장 정보 수정\n" +
                        "3. 극장 정보 삭제\n"+
                        "4. 종료";

        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                writeCinema();
            } else if (userChoice == 2) {
                updateCinema();
            } else if (userChoice == 3) {
                deleteCinema();
            }else if (userChoice==4){
                showCinemaMenu();
            }

        }
    }

    private void deleteCinema() {
        printCinema();
        System.out.println("--------------------------------------------------");
        String message = "삭제할 극장의 아이디를 입력해 주세요";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        CinemaDTO c = cinemaController.selectOne(userChoice);
        if (c != null) {
            message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("Y")) {
                cinemaController.delete(userChoice);
            }
        }
    }

    private void updateCinema() {
        printCinema();
        System.out.println("--------------------------------------------------");
        String message = "수정할 극장의 아이디를 입력해 주세요";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        CinemaDTO c = cinemaController.selectOne(userChoice);

        if (c != null ) {
            message = "극장 이름을 입력해주세요.";
            c.setCinemaName(ScannerUtil.nextLine(SCANNER, message));

            message = "극장의 지역 입력해주세요.";
            c.setLoction(ScannerUtil.nextLine(SCANNER, message));

            message = "전화번호를 입력해주세요." ;
            c.setCinemaCall(ScannerUtil.nextInt(SCANNER, message));

            cinemaController.update(c);
        }
    }
    private void writeCinema() {

        CinemaDTO cinemaDTO = new CinemaDTO();
        String message = "극장 이름을 입력해주세요.";
        cinemaDTO.setCinemaName(ScannerUtil.nextLine(SCANNER, message));

        message = "극장의 지역 입력해주세요.";
        cinemaDTO.setLoction(ScannerUtil.nextLine(SCANNER, message));

        message = "전화번호를 입력해주세요." ;
        cinemaDTO.setCinemaCall(ScannerUtil.nextInt(SCANNER, message));

        cinemaController.add(cinemaDTO);
    }


    private void managerMenu(){
        String message = "   1. 극장 정보 관리\n" +
                "   2. 뒤로 가기";
        int choice = ScannerUtil.nextInt(SCANNER, message);
        if (choice == 1) {
            while (logIn != null) {
                if (logIn.getGrade() != "관리자") {
                    System.out.println("접근권한이 없습니다.");
                    showCinemaMenu();
                } else {
                    CinemaManager();
                }
            }
        } else if (choice == 2) {
            CinemaManager();
        }
    }

    private void printCinema() {

        ArrayList<CinemaDTO> cinemalist = cinemaController.selectAll();

        if (cinemalist.isEmpty()) {
            System.out.println("등록된 극장이 없습니다.");
        } else {

            for (CinemaDTO c : cinemalist) {
                System.out.println("======================================================");
                System.out.printf("%d |극장명 : %s | 지역  %s |전화번호 : %d\n",
                        c.getCinemaId(), c.getCinemaName(), c.getLoction(), c.getCinemaCall());

                System.out.println("======================================================");

            }
        }


    }

    public void setShowViewer(ShowViewer showViewer) {
        this.showViewer = showViewer;
    }
}
