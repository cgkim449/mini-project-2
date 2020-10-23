package mini.project.server.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import mini.project.server.pms.domain.Login;
import mini.project.server.util.Prompt;

public class LogoutCommand implements Command {

  Login login;

  public LogoutCommand (Login login) {
    this.login = login;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      if (login.getAdmin() == 3) {
        out.println("이미 로그아웃 되어있습니다.");
        out.println(" ");
        out.println();
        out.flush();
        return;
      }

      String request = Prompt.inputString("로그아웃하시겠습니까? (y/N) : ", out, in);
      out.println(" ");

      if (request.equalsIgnoreCase("y")) {
        login.setAdmin(3);
        login.setName(null);
        out.println("로그아웃 되었습니다.");
        out.println(" ");
        out.println();
        out.flush();
      } else {
        out.println("로그아웃이 취소되었습니다.");
        out.println(" ");
        out.println();
        out.flush();
      }

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
}
