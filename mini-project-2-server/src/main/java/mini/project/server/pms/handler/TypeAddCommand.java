package mini.project.server.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import mini.project.server.pms.domain.Login;
import mini.project.server.pms.domain.Type;
import mini.project.server.util.Prompt;

public class TypeAddCommand implements Command {

  List<Type> typeList;
  Login login;

  public TypeAddCommand(List<Type> list, Login login) {
    this.typeList = list;
    this.login = login;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    if (login.getAdmin() != 0) {
      out.print("권한이 없습니다.");
      out.println();
      out.flush();
      return;
    }
    try {
      out.println("[타입 등록]");

      Type type = new Type();
      type.setNo(Prompt.inputInt("번호? ", out, in));
      type.setName(Prompt.inputString("유형? ", out, in));
      type.setIntroduction(Prompt.inputString("소개? ", out, in));
      type.setRomance(Prompt.inputString("연애? ", out, in));
      type.setFriendship(Prompt.inputString("우정? ", out, in));
      type.setStrength(Prompt.inputString("장점? ", out, in));
      type.setWeakness(Prompt.inputString("약점? ", out, in));

      typeList.add(type);

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
}
