package hruler.portfolio.repository;

import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.dto.SearchStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    List<Cafe> findByName(String name);

    @Query("select c " +
            "from Cafe c " +
            "where c.name like %:searchInfo%")
    List<Cafe> findListByName(@Param("searchInfo") String searchInfo);

    @Query("select c " +
            "from Cafe c " +
            "where c.address.city like %:searchInfo% or " +
            "c.address.street like %:searchInfo% or " +
            "c.address.zipcode like %:searchInfo%")
    List<Cafe> findByAddress(@Param("searchInfo") String searchInfo);

}
