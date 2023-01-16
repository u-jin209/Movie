package viewer;

import controller.MovieController;
import model.MemberDTO;
import model.MovieDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieViewer {
    private final Scanner SCANNER;
    private MovieController movieController;
    private MemberDTO logIn;
    private MemberViewer memberViewer;
    private RankViewer rankViewer;
    private String[] grade = {"전체이용가","12세 이용가", "15세 이용가",  "청소년 관람 불가"};
    private CinemaViewer cinemaViewer;
    private ShowViewer showViewer;

    public MovieViewer(Scanner scanner){
        SCANNER = scanner;
        movieController = new MovieController();
    }


    public void showMovieMenu() {
        String message =
                "   1. 영화 정보 보기\n" +
                "   2. 영화별 전체 평점 보기\n" +
                "   3. 관리자 메뉴\n"+
                "   4. 뒤로 가기";

        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                printMovie(1);
            } else if (userChoice == 2) {
                rankViewer.showRankMenu();
            } else if (userChoice == 3) {
                if(logIn.getGrade() != "관리자"){
                    System.out.println("접근권한이 없습니다.");
                    showMovieMenu();
                }else{
                    movieManager();
                }
            }else if(userChoice ==4){
                memberViewer.showMenu();
            }
        }
    }


    public void movieManager() {

        String message =
                "1. 영화 정보 등록\n" +
                "2. 영화 정보 수정\n" +
                "3. 영화 정보 삭제\n"+
                "4. 종료";

        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                writeMovie();
            } else if (userChoice == 2) {
                updateMovie(2);
            } else if (userChoice == 3) {
                deleteMovie(2);
            }else if (userChoice==4){
                showMovieMenu();
            }
        }
    }
    private void deleteMovie(int option){
        printMovie(option);
        String message = "삭제할 영화의 아이디를 입력해 주세요";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        MovieDTO m = movieController.selectOne(userChoice);
        if (m != null) {
            message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("Y")) {
                movieController.delete(userChoice);
            }
        }


    }
    private void updateMovie(int option) {



        printMovie(option);
        System.out.println("--------------------------------------------------");
        String message = "수정할 영화의 아이디를 입력해 주세요";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        MovieDTO m = movieController.selectOne(userChoice);

        if (m != null ) {
            message = "새로운 영화 제목을 입력해주세요.";
            String newMovieName = ScannerUtil.nextLine(SCANNER, message);

            message = "새로운 영화 내용을 입력해주세요.";
            String newMovieContent = ScannerUtil.nextLine(SCANNER, message);

            message = "새로운 영화 등급을 입력해주세요.\n" +
                    "1. 전체이용가 2. 12세 이용가 3. 15세 이용가 4. 청소년 관람 불가";
            int choice = ScannerUtil.nextInt(SCANNER, message);
            String newMovieGrade = grade[choice-1];

            m.setMovieName(newMovieName);
            m.setMovieContent(newMovieContent);
            m.setMovieGrade(newMovieGrade);

            movieController.update(m);
        }



    }

    private void writeMovie() {
        MovieDTO movieDTO = new MovieDTO();
        String message = "영화 제목을 입력해주세요.";
        movieDTO.setMovieName(ScannerUtil.nextLine(SCANNER, message));

        message = "영화 내용을 입력해주세요.";
        movieDTO.setMovieContent(ScannerUtil.nextLine(SCANNER, message));

        message = "영화 등급을 번호로 입력해주세요.\n" +
                "1. 전체이용가 2. 12세 이용가 3. 15세 이용가 4. 청소년 관람 불가";
        int choice = ScannerUtil.nextInt(SCANNER, message);
        String MovieGrade = grade[choice-1];
        movieDTO.setMovieGrade(MovieGrade);

        movieController.add(movieDTO);
    }



    public void printMovie(int option) {
        ArrayList<MovieDTO> movielist = movieController.selectAll();

        if (movielist.isEmpty()){
            System.out.println("등록된 영화가 없습니다.");
            showMovieMenu();

        }else{
            if (option == 1){
                for(MovieDTO m :movielist){
                    System.out.println("======================================================");
                    System.out.printf("%d | %s |%s\n" ,m.getMovieId(),m.getMovieName(),m.getMovieGrade());
                    System.out.println("------------------------------------------------------");
                    System.out.println(m.getMovieContent());
                    System.out.println("======================================================");
                }
            } else if (option ==2) {
                for(MovieDTO m :movielist){
                    System.out.printf("%d. | %s |%s\n" ,m.getMovieId(),m.getMovieName(),m.getMovieGrade());

                }
            }
        }

    }

    public void setMemberViewer(MemberViewer memberViewer) {
        this.memberViewer = memberViewer;
    }

    public void setLogIn(MemberDTO logIn) {
        this.logIn = logIn;
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
