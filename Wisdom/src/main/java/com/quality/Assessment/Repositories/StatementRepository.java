package com.quality.Assessment.Repositories;

import com.quality.Assessment.models.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<Statement, Long>{

    Statement findBySentenceEquals(String string);

}
