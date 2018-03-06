package wisdom.Repositories;

import wisdom.models.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<Statement, Long>{

    Statement findBySentenceEquals(String string);

}
