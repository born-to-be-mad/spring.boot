package by.dma.crudwebapp.controller.business;

import by.dma.crudwebapp.controller.exception.CardNotFoundException;
import by.dma.crudwebapp.controller.repository.UserRepository;
import by.dma.crudwebapp.controller.dto.UserRequestDTO;
import by.dma.crudwebapp.controller.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Long create(UserRequestDTO request) {
        User user = new User();
        user.setLogin(request.getLogin());

        User saved = repository.save(user);
        return saved.getId();
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(long id) {
        Optional<User> foundEntity = repository.findById(id);
        if (foundEntity.isEmpty()) {
            throw new CardNotFoundException(String.format("User with id %s not found", id));
        }
        return foundEntity.get();
    }

    @Transactional
    public User update(long id, UserRequestDTO entityToUpdateRequest) {
        Optional<User> foundEntity = repository.findById(id);
        if (foundEntity.isEmpty()) {
            throw new CardNotFoundException(String.format("User with id %s not found", id));
        }
        User user = foundEntity.get();
        user.setLogin(entityToUpdateRequest.getLogin());
        return user;
    }
}
