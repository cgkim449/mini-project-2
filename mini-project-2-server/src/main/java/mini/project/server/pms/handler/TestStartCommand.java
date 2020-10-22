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
      out.println("[테스트 시작]");
      out.println("1. 다른 사람들에게 자신을 소개하는 것을 어려워 합니다.");
      test.setFirstAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));

      out.println("종종 주변을 무시하거나 잊어버리는 생각에 빠지곤 합니다.");
      test.setSecondAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));

      out.println("이메일에 가능한 빨리 회신하려고 하고 지저분한 편지함을 참을 수 없습니다.");
      test.setThirdAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));

      out.println("중암갑을 받을 때에도 쉽게 침착하게 집중력을 유지할 수 있습니다.");
      test.setFourthAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));

      out.println("보통 대화를 먼저 시작하지 않습니다.");
      test.setFifthAnswer((Prompt.inputInt(
          "매우 그렇다(1), 그렇다(2), 보통이다(3), 아니다(4), 매우 아니다(5)", out, in)));

      out.println("[테스트 결과]");

      test.setTotalScore(test.getFirstAnswer(), test.getSecondAnswer(), test.getThirdAnswer(),
          test.getFourthAnswer(), test.getFifthAnswer());
      //      test.totalScore = test.getFirstAnswer() + test.getSecondAnswer() + test.getThirdAnswer() +
      //          test.getFourthAnswer() + test.getFifthAnswer();

      out.printf("%s님의 검사결과입니다.\n", login.getName());

      if (test.getTotalScore() == 5) {
        Type type = findByNo(1);
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
      } else if (5 < test.getTotalScore() && test.getTotalScore() < 10) {
        Type type = findByNo(2);
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
      } else if (10 <= test.getTotalScore() && test.getTotalScore() < 15) {
        Type type = findByNo(3);
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
      } else if (15 <= test.getTotalScore() && test.getTotalScore() < 20) {
        Type type = findByNo(4);
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
      } else if (20 <= test.getTotalScore() && test.getTotalScore() <= 25) {
        Type type = findByNo(5);
        out.printf("유형 : %s\n",type.getName());
        out.printf("설명 : %s\n",type.getIntroduction());
        out.printf("장점 : %s\n",type.getStrength());
        out.printf("약점 : %s\n",type.getWeakness());
      } else {
      }
      out.printf("%s님의 테스트 결과를 저장하였습니다.\n", login.getName());
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
