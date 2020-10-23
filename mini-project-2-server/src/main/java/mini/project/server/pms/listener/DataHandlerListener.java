package mini.project.server.pms.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import mini.project.server.context.ApplicationContextListener;
import mini.project.server.pms.domain.Login;
import mini.project.server.pms.domain.Member;
import mini.project.server.pms.domain.Test;
import mini.project.server.pms.domain.Type;

// 게시물, 회원, 프로젝트, 작업 데이터를 파일에서 로딩하고 파일로 저장하는 일을 한다.
public class DataHandlerListener implements ApplicationContextListener {

  Login login = new Login();

  List<Member> memberList = new LinkedList<>();
  File memberFile = new File("./member.json");

  List<Type> typeList = new LinkedList<>();
  File typeFile = new File("./type.json");
  List<Test> testList = new LinkedList<>();
  File testFile = new File("./test.json");

  @Override
  public void contextInitialized(Map<String,Object> context) {
    loadData(typeList, typeFile, Type[].class);
    loadData(testList, testFile, Test[].class);
    loadData(memberList, memberFile, Member[].class);
    context.put("login", login);
    context.put("typeList", typeList);
    context.put("testList", testList);
    context.put("memberList", memberList);
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    saveData(typeList, typeFile);
    saveData(testList, testFile);
    saveData(memberList, memberFile);
  }

  private <T> void loadData(
      Collection<T> list, // 객체를 담을 컬렉션
      File file, // JSON 문자열이 저장된 파일
      Class<T[]> clazz // JSON 문자열을 어떤 타입의 배열로 만들 것인지 알려주는 클래스 정보
      ) {
    BufferedReader in = null;

    try {
      in = new BufferedReader(new FileReader(file));
      list.addAll(Arrays.asList(new Gson().fromJson(in, clazz)));
      System.out.printf("'%s' 파일에서 총 %d 개의 객체를 로딩했습니다.\n",
          file.getName(), list.size());

    } catch (Exception e) {
      System.out.printf("'%s' 파일 읽기 중 오류 발생! - %s\n",
          file.getName(), e.getMessage());

    } finally {
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private void saveData(Collection<?> list, File file) {
    BufferedWriter out = null;

    try {
      out = new BufferedWriter(new FileWriter(file));

      Gson gson = new Gson();
      String jsonStr = gson.toJson(list);
      out.write(jsonStr);

      out.flush();

      System.out.printf("총 %d 개의 객체를 '%s' 파일에 저장했습니다.\n",
          list.size(), file.getName());

    } catch (IOException e) {
      System.out.printf("객체를 '%s' 파일에  쓰는 중 오류 발생! - %s\n",
          file.getName(), e.getMessage());

    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }
}
