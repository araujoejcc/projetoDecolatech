package br.com.decolatech.projetoDecolatech.domain.repository;

import br.com.decolatech.projetoDecolatech.domain.models.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,Long> {
}
