package br.com.decolatech.projetoDecolatech.service.impl;

import br.com.decolatech.projetoDecolatech.domain.models.Notification;
import br.com.decolatech.projetoDecolatech.domain.repository.NotificationRepository;
import br.com.decolatech.projetoDecolatech.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification findById(Long id) {
        return notificationRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> findUnreadNotifications() {
        return notificationRepository.findAll().stream()
                .filter(notification -> !notification.isRead())
                .collect(Collectors.toList());
    }

    @Override
    public Notification create(Notification notificationToCreate) {
        if (notificationToCreate.getId() != null && notificationRepository.existsById(notificationToCreate.getId())) {
            throw new IllegalArgumentException("This notification ID already exists.");
        }
        
        // Set current date time if not provided
        if (notificationToCreate.getDateTime() == null) {
            notificationToCreate.setDateTime(new Date());
        }
        
        // Set read status to false by default
        notificationToCreate.setRead(false);
        
        return notificationRepository.save(notificationToCreate);
    }

    @Override
    public Notification markAsRead(Long id) {
        Notification notification = findById(id);
        notification.setRead(true);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification markAsUnread(Long id) {
        Notification notification = findById(id);
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(Long id) {
        Notification notification = findById(id);
        notificationRepository.delete(notification);
    }
}
