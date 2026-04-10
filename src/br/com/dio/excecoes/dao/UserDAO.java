package br.com.dio.excecoes.dao;

import java.util.List;
import java.util.ArrayList;

import br.com.dio.excecoes.model.UserModel;
import br.com.dio.excecoes.exceptions.UserNotFoundException;

public class UserDAO {
    private long nextId = 1L;
    private final List<UserModel> models = new ArrayList<>();

    public UserModel save(final UserModel model){
        model.setId(nextId);
        nextId++;

        models.add(model);

        return model;
    }

    public UserModel update(final UserModel model){
        var toUpdate = findById(model.getId());

        models.remove(toUpdate);
        models.add(model);

        return model;
    }

    public void delete(final long id){
        var toDelete = findById(id);

        models.remove(toDelete);
    }

    public UserModel findById(final long id){
        String message = "Não existe usuário com o ID %d".formatted(id);

        return models
            .stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException(message));
    }

    public List<UserModel> findAll(){
        return models;
    }
}
