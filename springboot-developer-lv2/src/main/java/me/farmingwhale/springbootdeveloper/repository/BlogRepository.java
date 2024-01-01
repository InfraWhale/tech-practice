package me.farmingwhale.springbootdeveloper.repository;

import me.farmingwhale.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
