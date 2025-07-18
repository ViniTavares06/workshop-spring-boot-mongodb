package com.workshop.workshopmongo.service;

import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.dto.UserDTO;
import com.workshop.workshopmongo.repository.UserRepository;
import com.workshop.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repo.findById(id);
        if (user.isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user.get();
    }

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        User existingUser = repo.findById(obj.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        existingUser.setName(obj.getName());
        existingUser.setEmail(obj.getEmail());
        return repo.save(existingUser);
    }

    public User fromDto(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
