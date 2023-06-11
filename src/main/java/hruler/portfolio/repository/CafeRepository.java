package hruler.portfolio.repository;

import hruler.portfolio.domain.cafe.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    List<Cafe> findByName(String name);
}
