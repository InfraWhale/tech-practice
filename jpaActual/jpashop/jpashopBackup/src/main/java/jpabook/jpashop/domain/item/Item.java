package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

// @BatchSize(size = 1000) // xToOne의 경우 엔티티에 적어줌 , default_batch_fetch_size 와 같음
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 한 테이블에 다 넣음
@DiscriminatorColumn(name = "dtype") // 아이템 유형별 구분 컬럼
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==// 응집력을 위해 엔티티에 구현
    
    /*
    * stock 증가
    * */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
    * stock 감소
    * */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
