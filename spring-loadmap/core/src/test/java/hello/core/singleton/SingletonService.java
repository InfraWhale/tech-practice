package hello.core.singleton;

public class SingletonService {

    // static 영역에 딱 하나만 올라가게 만듬
    private static final SingletonService instance = new SingletonService();

    // 조회는 이 메서드로만 가능
    public static SingletonService getInstance() {
        return instance;
    }
    
    // private로 선언하여 외부에서 new로 객체 인스턴스 생성되는 것을 막음
    private SingletonService() {
        
    }
    
    public void logic() {
        System.out.println(" 싱글톤 객체 로직 호출 ");
    }

    
}
