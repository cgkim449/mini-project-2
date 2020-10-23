package mini.project.server.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import mini.project.server.pms.domain.Login;
import mini.project.server.pms.domain.Test;
import mini.project.server.pms.domain.Type;
import mini.project.server.util.Prompt;

public class TestStartCommand implements Command {

  List<Test> testList;
  List<Type> typeList;
  Login login;

  public TestStartCommand (List<Test> list, List<Type> typeList, Login login) {
    this.testList = list;
    this.typeList = typeList;
    this.login = login;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      Test test = new Test();
      test.setName(login.getName());

      out.println(" ");
      out.println("[테스트 시작]");
      out.print("아무 키나 눌러주세요.");
      Prompt.inputString(" ", out, in);

      int delay = 200;
      out.println("1. 다른 사람들에게 자신을 소개하는 것을 어려워 합니다.");
      test.setFirstAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");
      out.println("2. 종종 주변을 무시하거나 잊어버리는 생각에 빠지곤 합니다.");
      test.setSecondAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.println("3. 이메일에 가능한 빨리 회신하려고 하고 지저분한 편지함을 참을 수 없습니다.");
      test.setThirdAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.println("4. 중암갑을 받을 때에도 쉽게 침착하게 집중력을 유지할 수 있습니다.");
      test.setFourthAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.println("5. 보통 대화를 먼저 시작하지 않습니다.");
      test.setFifthAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.print("2페이지로 넘어갑니다. (계속 하시려면 아무 키나 눌러주세요.)");
      Prompt.inputString(" ", out, in);

      out.println("6. 자신이 다른 사람들보다 뛰어나다고 생각합니다.");
      test.setFirstAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.println("7. 적응을 잘 하는 것 보다 체계적인 것이 더 중요합니다.");
      test.setSecondAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.println("8. 대게 의욕적이고 활동적입니다.");
      test.setThirdAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.println("9. 논쟁에서 이기는 것이 상대방을 불쾌하지 않도록 하는 것보다 더 중요합니다.");
      test.setFourthAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.println("10. 종종 다른 사람들에게 자신을 정당화시켜야 할 것 만 같은 기분이 듭니다.");
      test.setFifthAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));
      Thread.sleep(delay);
      out.println(" ");

      out.println("검사 집계 중");
      out.println(".");
      Thread.currentThread().sleep(delay);
      out.println(".");
      Thread.currentThread().sleep(delay);
      out.println(".");
      Thread.currentThread().sleep(delay);
      out.println(".");
      out.println(" ");

      out.println("[테스트 결과 조회]");
      out.print("아무 키나 눌러주세요.");
      Prompt.inputString(" ", out, in);

      test.setTotalScore(test.getFirstAnswer(), test.getSecondAnswer(), test.getThirdAnswer(),
          test.getFourthAnswer(), test.getFifthAnswer());

      out.printf("%s님의 검사결과입니다.\n", login.getName());
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
      out.printf("%s님의 테스트 결과를 저장하였습니다.\n", login.getName());
      out.println(" ");
      out.printf("결과를 다시 보고 싶으시면 '/test/result'를 입력하세요.\n", login.getName());
      out.println(" ");
      out.flush();
      testList.add(test);

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
}
