package mini.project.server.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import mini.project.server.pms.domain.Login;
import mini.project.server.pms.domain.Member;
import mini.project.server.util.Prompt;

public class LoginCommand implements Command {

  Login login;
  List<Member> memberList;

  public LoginCommand (Login login, List<Member> memberList) {
    this.login = login;
    this.memberList = memberList;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {

      Member member;

      out.println("[로그인] ");

      if (login.getAdmin() < 2 && login.getAdmin() >= 0) {
        out.println("이미 로그인 되어있습니다.");
        out.println(" ");
        out.println();
        out.flush();
        return;
      }

      while (true) {
        String name = Prompt.inputString("아이디를 입력해주세요 : ", out, in);
        if (findByName(name) == null) {
          String response = Prompt.inputString(
              "없는 아이디입니다. 계속하시겠습니까?(y/N):", out, in);
          if (response.equalsIgnoreCase("y")) {
            continue;
          } else {
            out.println("로그인을 취소합니다.");
            out.println(" ");
            out.flush();
            return;
          }
        } else {
          member = findByName(name);
          break;
        }
      }


      while(true) {
        if (member.getPassword() != Prompt.inputInt("비밀번호 : ", out, in)) {
          String response = Prompt.inputString(
              "비밀번호가 틀렸습니다. 계속하시겠습니까?(y/N):", out, in);
          if (response.equalsIgnoreCase("y")) {
            continue;
          } else {
            out.println("로그인을 취소합니다.");
            out.println(" ");
            out.flush();
            return;
          }
        } else {
          break;
        }
      }
      out.println(" ");
      if (member.getName().equalsIgnoreCase("bitcamp")) {
        out.println("관리자님 안녕하세요.");
        out.println(" ");
        out.println();
        out.flush();
        login.setName(member.getName());
        login.setAdmin(0);

      } else {
        out.printf("%s님 안녕하세요. '/test/start'로 B-BTI검사를 시작하세요.\n", member.getName());
        out.println(" ");
        out.println();
        out.flush();
        login.setName(member.getName());
        login.setAdmin(1);
      }
    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  public Member findByName(String name) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getName().equals(name)) {
        return member;
      }
    }
    return null;
  }
}
