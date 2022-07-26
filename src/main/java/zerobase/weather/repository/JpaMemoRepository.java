package zerobase.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

import java.util.Optional;

@Repository
public interface JpaMemoRepository extends JpaRepository<Memo, Integer> {
}
