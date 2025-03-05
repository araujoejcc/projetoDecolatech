package br.com.decolatech.projetoDecolatech.domain.repository;

import br.com.decolatech.projetoDecolatech.domain.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
