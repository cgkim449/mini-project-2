package mini.project.server.pms.domain;

public class Login {

  private String name;
  private int admin =3;
  private int adminPw;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getAdmin() {
    return admin;
  }
  public void setAdmin(int admin) {
    this.admin = admin;
  }
  public int getAdminPw() {
    return adminPw;
  }
  public void setAdminPw(int adminPw) {
    this.adminPw = adminPw;
  }

}
