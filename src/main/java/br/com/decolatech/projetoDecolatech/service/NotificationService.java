package br.com.decolatech.projetoDecolatech.service;

import br.com.decolatech.projetoDecolatech.domain.models.Notification;

import java.util.List;

public interface NotificationService {

    Notification findById(Long id);
    
    List<Notification> findAll();
    
    List<Notification> findUnreadNotifications();
    
    Notification create(Notification notificationToCreate);
    
    Notification markAsRead(Long id);
    
    Notification markAsUnread(Long id);
    
    void delete(Long id);
}
