package study.datajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;
// 순수 Jpa 기반 Auditing

@Getter
@MappedSuperclass // 속성만 상속하는 경우
public class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist // persist 하기 전 이벤트 발생
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now; // this 생략해도 됨. 중복 여지 있는 경우에만 사용 (id를 쓰기 때문)
        updatedDate = now;
    }

    @PreUpdate // update 하기 전 이벤트 발생
    public void preUpdate() {
        updatedDate = LocalDateTime.now();;
    }
}
