package viewer;


import controller.MovieController;
import controller.RankController;
import model.MemberDTO;
import model.MovieDTO;
import model.RankDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class RankViewer {

    private final Scanner SCANNER;
    private RankController rankController;
    private MemberDTO logIn;
    private MemberViewer memberViewer;
    private MovieViewer movieViewer;
    private MovieController movieController;
    private CinemaViewer cinemaViewer;
    private ShowViewer showViewer;

    public RankViewer(Scanner scanner) {
        SCANNER = scanner;
        rankController = new RankController();
    }

    public void showRankMenu() {

        String message ="1. 평론가 평점 / 평론 보기\n" +
                "2. 일반 관람객 평점 보기\n" +
                "3. 평점 등록 하기\n"+
                "4. 뒤로가기 ";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (logIn != null) {
            movieViewer.printMovie(2);

            if (userChoice == 1) {
                message ="평점을 볼 영화를 선택하세요.";
                int movieChoice = ScannerUtil.nextInt(SCANNER, message);
                movieCommentPrint(1,movieChoice);
                
            } else if (userChoice == 2) {
                message ="평점을 볼 영화를 선택하세요.";
                int movieChoice = ScannerUtil.nextInt(SCANNER, message);
                movieCommentPrint(2,movieChoice);
            } else if (userChoice == 3) {
                message ="평점을 등록할 영화를 선택하세요.";
                int movieChoice = ScannerUtil.nextInt(SCANNER, message);
                movieCommentManager(movieChoice);
            } else if (userChoice == 4) {
                movieViewer.showMovieMenu();
                break;
            }

            showRankMenu();

        }
    }

    private void movieCommentPrint(int option,int movieChoice) {
        ArrayList<RankDTO> ranklist = rankController.selectAll();

        if (ranklist.isEmpty()) {
            System.out.println("등록된 평점이 없습니다.");
        } else {
            if (option == 1) {
                for (RankDTO r : ranklist) {
                    if (r.getMovieId() == movieChoice && logIn.getGrade() == "전문 평론가") {
                        System.out.println("======================================================");
                        System.out.printf("%d | %s \n", r.getRankId(), "전문 평론가");
                        System.out.println("------------------------------------------------------");
                        System.out.println(r.getReview());
                        System.out.println("======================================================");

                    }else {
                        System.out.println("등록된 평점이 없습니다.");
                    }

                }

            } else if (option == 2) {
                for (RankDTO r : ranklist) {
                    if (r.getMovieId() == movieChoice && logIn.getGrade() == "일반관람객") {
                        System.out.println("======================================================");
                        System.out.printf("%d | %s \n", r.getRankId(), "일반관람객");
                        System.out.println("------------------------------------------------------");
                        System.out.println(r.getReview());
                        System.out.println("======================================================");
                    }
                    else {
                        System.out.println("등록된 평점이 없습니다.");
                    }
                }
            }
        }
    }

    private void movieCommentManager(int movieChoice) {
        String message ="";
        int option = 0;

        if(logIn.getGrade() == "전문 평론가"){

            message = "1. 전문가 평점 등록\n" +
                    "2. 전문가 평점 수정\n" +
                    "3. 전문가 평점 삭제\n"+
                    "4. 종료" ;

            option =1;
            
            
        } else if (logIn.getGrade() == "일반관람객") {

            message = "1. 일반 관람객 평점 등록\n" +
                    "2. 일반 관람객 평점 수정\n" +
                    "3. 일반 관람객 평점 삭제\n"+
                    "4. 종료" ;
            option =2;
        }else if (logIn.getGrade() == "관리자"){
            System.out.println("관리자는 리뷰를 등록할 수 없습니다.");
            showRankMenu();
        }
        int id;
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {

                writeComment(movieChoice);
            } else if (userChoice == 2) {
                movieCommentPrint(option,movieChoice);
                message = "수정할 리뷰 번호를 선택 해주세요 ";
                id = ScannerUtil.nextInt(SCANNER,message);
                updateComment(id,movieChoice);
                showRankMenu();
            } else if (userChoice == 3) {
                movieCommentPrint(option,movieChoice);
                message = "삭제할 리뷰 번호를 선택 해주세요 ";
                id = ScannerUtil.nextInt(SCANNER,message);
                deleteComment(id, movieChoice);
                showRankMenu();
            }else if (userChoice==4){
                showRankMenu();
            }
        }
    }

    private void updateComment(int id, int movieId) {
        RankDTO r = rankController.selectOne(id);
        if (r != null && r.getMovieId() == movieId && r.getMemberId() == logIn.getMemberId()) {
            String message = "새로운 평점을 입력해주세요.";
            r.setReview(ScannerUtil.nextLine(SCANNER, message));
            rankController.update(r);
        }
    }

    private void deleteComment(int id, int movieId) {
        RankDTO r = rankController.selectOne(id);
        if (r != null && r.getMovieId() == movieId && r.getMemberId() == logIn.getMemberId()) {
            String message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("Y")) {
                rankController.delete(id);
            }
        }
    }

    private void writeComment( int movieId) {
      
        RankDTO r = new RankDTO();
        r.setMovieId(movieId);
        r.setMemberId(logIn.getMemberId());
        String message = "평론 내용을 입력해주세요.";
        r.setReview(ScannerUtil.nextLine(SCANNER, message));

        rankController.add(r);

            

    }

    public void setMemberViewer(MemberViewer memberViewer) {
        this.memberViewer = memberViewer;
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    public void setLogIn(MemberDTO logIn) {
        this.logIn = logIn;
    }

    public void setCinemaViewer(CinemaViewer cinemaViewer) {
        this.cinemaViewer = cinemaViewer;
    }

    public void setShowViewer(ShowViewer showViewer) {
        this.showViewer = showViewer;
    }
}
