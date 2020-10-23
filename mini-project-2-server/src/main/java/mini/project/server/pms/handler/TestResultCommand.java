package mini.project.server.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import mini.project.server.pms.domain.Login;
import mini.project.server.pms.domain.Test;
import mini.project.server.pms.domain.Type;
import mini.project.server.util.Prompt;

public class TestResultCommand implements Command {
  List<Test> testList;
  List<Type> typeList;
  Login login;

  public TestResultCommand (List<Test> list, List<Type> typeList, Login login) {
    this.testList = list;
    this.typeList = typeList;
    this.login = login;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      Test test;
      out.println("[테스트 결과 조회]");

      while(true) {
        String name = Prompt.inputString(
            "누구의 테스트 결과를 조회하시겠습니까? (이름 입력): ", out, in);
        out.flush();
        if (login.getAdmin() != 0 && !name.equalsIgnoreCase(login.getName())) {
          out.println(" ");
          String response = Prompt.inputString(
              "권한이 없습니다. 계속하시겠습니까?(y/N):", out, in);
          if (response.equalsIgnoreCase("y")) {
            continue;
          } else {
            out.println("결과 조회를 취소합니다.");
            out.println(" ");
            return;
          }
        } else if (findByName(name) == null){
          out.println(" ");
          String response = Prompt.inputString(
              "저장된 결과가 없습니다. 계속하시겠습니까?(y/N):", out, in);
          if (response.equalsIgnoreCase("y")) {
            continue;
          } else {
            out.println("결과 조회를 취소합니다.");
            out.println(" ");
            return;
          }
        } else {
          test = findByName(name);
          break;
        }
      }
      test.setTotalScore(test.getFirstAnswer(), test.getSecondAnswer(), test.getThirdAnswer(),
          test.getFourthAnswer(), test.getFifthAnswer());
      //      test.totalScore = test.getFirstAnswer() + test.getSecondAnswer() + test.getThirdAnswer() +
      //          test.getFourthAnswer() + test.getFifthAnswer();

      out.println(" ");
      out.printf("%s님의 검사결과입니다.\n", test.getName());
      out.println(" ");

      if (test.getTotalScore() == 5) {
        Type type = findByNo(1);
        out.println("IIIII NN   NN TTTTTTT PPPPPP  \r\n" +
            " III  NNN  NN   TTT   PP   PP \r\n" +
            " III  NN N NN   TTT   PPPPPP  \r\n" +
            " III  NN  NNN   TTT   PP      \r\n" +
            "IIIII NN   NN   TTT   PP \r");
        out.println(" ");
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
        out.printf("연애 : %s\n",type.getRomance());
        out.printf("우정 : %s\n",type.getFriendship());
      } else if (5 < test.getTotalScore() && test.getTotalScore() < 10) {
        Type type = findByNo(2);
        out.println("EEEEEEE  SSSSS  TTTTTTT PPPPPP \r\n" +
            "EE      SS        TTT   PP   PP \r\n" +
            "EEEEE    SSSSS    TTT   PPPPPP \r\n" +
            "EE           SS   TTT   PP \r\n" +
            "EEEEEEE  SSSSS    TTT   PP \r");
        out.println(" ");
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
        out.printf("연애 : %s\n",type.getRomance());
        out.printf("우정 : %s\n",type.getFriendship());
      } else if (10 <= test.getTotalScore() && test.getTotalScore() < 15) {
        Type type = findByNo(3);
        out.println("IIIII NN   NN TTTTTTT     JJJ \r\n" +
            " III  NNN  NN   TTT       JJJ \r\n" +
            " III  NN N NN   TTT       JJJ \r\n" +
            " III  NN  NNN   TTT   JJ  JJJ \r\n" +
            "IIIII NN   NN   TTT    JJJJJ  ");
        out.println(" ");
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
        out.printf("연애 : %s\n",type.getRomance());
        out.printf("우정 : %s\n",type.getFriendship());
      } else if (15 <= test.getTotalScore() && test.getTotalScore() < 20) {
        Type type = findByNo(4);
        out.println("EEEEEEE  SSSSS  FFFFFFF     JJJ \r\n" +
            "EE      SS      FF          JJJ \r\n" +
            "EEEEE    SSSSS  FFFF        JJJ \r\n" +
            "EE           SS FF      JJ  JJJ \r\n" +
            "EEEEEEE  SSSSS  FF       JJJJJ");
        out.println(" ");
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
        out.printf("연애 : %s\n",type.getRomance());
        out.printf("우정 : %s\n",type.getFriendship());
      } else if (20 <= test.getTotalScore() && test.getTotalScore() <= 25) {
        Type type = findByNo(5);
        out.println("IIIII  SSSSS  TTTTTTT PPPPPP  \r\n" +
            " III  SS        TTT   PP   PP \r\n" +
            " III   SSSSS    TTT   PPPPPP  \r\n" +
            " III       SS   TTT   PP      \r\n" +
            "IIIII  SSSSS    TTT   PP ");
        out.println(" ");
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
        out.printf("연애 : %s\n",type.getRomance());
        out.printf("우정 : %s\n",type.getFriendship());
      } else {
      }
      out.println(" ");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  private Type findByNo(int no) {
    for (int i = 0; i < typeList.size(); i++) {
      Type type = typeList.get(i);
      if (type.getNo() == no) {
        return type;
      }
    }
    return null;
  }

  private Test findByName(String name) {
    for (int i = 0; i < testList.size(); i++) {
      Test test = testList.get(i);
      if (test.getName().equals(name)) {
        return test;
      }
    }
    return null;
  }
}
