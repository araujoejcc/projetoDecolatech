package br.com.decolatech.projetoDecolatech.domain.repository;

import br.com.decolatech.projetoDecolatech.domain.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
