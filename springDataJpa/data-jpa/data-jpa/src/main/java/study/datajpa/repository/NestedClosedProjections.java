package study.datajpa.repository;

// 팀 이름까지 같이 가져오도록
public interface NestedClosedProjections {

    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
