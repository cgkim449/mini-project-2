package mini.project.server.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import mini.project.server.util.Prompt;

public class HelpCommand implements Command {


  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println(" ");
      out.println("[가이드 페이지]");
      out.println("/signUp => 회원가입");
      out.println("/login => 로그인");
      out.println("/logout => 로그아웃");

      out.println("/test/start => 테스트 시작");
      out.println("/test/result => 자신의 테스트 결과 조회");
      out.println(" ");

      out.println("[관리자 가이드] ");
      out.println("/member/list => 회원 간략조회");
      out.println("/member/detail =>  회원 상세조회");
      out.println("/member/update =>  회원 데이터 수정");
      out.println("/member/delete =>  회원 데이터 삭제");

      //      out.println("/test/list =>  회원의 테스트 결과 간략조회");
      //      out.println("/test/delete => 회원의 테스트 결과 삭제");

      out.println("/test/result => 회원의 테스트 결과 상세조회");

      out.println("/type/add => 성격유형 추가하기");
      out.println("/type/list => 등록된 성격유형 간략조회");
      out.println("/type/detail => 등록된 성격유형 상세조회");
      out.println("/type/update => 등록된 성격유형 수정");
      out.println("/type/delete => 등록된 성격유형 삭제");
      out.println(" ");
      out.println("나가시려면 아무 키나 눌러주세요.");
      Prompt.inputString(" ", out, in);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
