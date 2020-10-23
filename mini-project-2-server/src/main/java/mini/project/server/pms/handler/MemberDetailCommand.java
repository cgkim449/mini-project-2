package mini.project.server.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import mini.project.server.pms.domain.Login;
import mini.project.server.pms.domain.Member;
import mini.project.server.util.Prompt;

public class MemberDetailCommand implements Command {

  List<Member> memberList;
  Login login;

  public MemberDetailCommand(List<Member> list, Login login) {
    this.memberList = list;
    this.login =login;
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
      out.println("[회원 상세보기]");
      int no = Prompt.inputInt("번호? ", out, in);
      Member member = findByNo(no);

      if (member == null) {
        out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      out.printf("이름: %s\n", member.getName());
      out.printf("이메일: %s\n", member.getEmail());
      out.printf("사진: %s\n", member.getPhoto());
      out.printf("전화: %s\n", member.getTel());
      out.printf("등록일: %s\n", member.getRegisteredDate());
      out.printf("관리자 권한: %d", member.getAdmin());
      out.println();
      out.flush();

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  private Member findByNo(int no) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getNo() == no) {
        return member;
      }
    }
    return null;
  }
}
