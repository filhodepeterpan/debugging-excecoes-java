package br.com.dio.excecoes.validator;

import br.com.dio.excecoes.exceptions.ValidatorException;
import br.com.dio.excecoes.model.UserModel;

public class UserValidator {

    private UserValidator(){}

    public static void verifyModel(final UserModel model) throws ValidatorException{

        if (model.getName() == null || model.getName().isBlank()){
            throw new ValidatorException("Informe um nome válido.");
        }
        if (model.getName().length() < 2){
            throw new ValidatorException("O nome deve ter ao menos 2 caracteres.");
        }
        if (model.getEmail() == null || model.getEmail().isBlank()){
            throw new ValidatorException("Informe um email válido.");
        }
        if(!model.getEmail().contains("@") || !model.getEmail().contains(".")){
            throw new ValidatorException("Formato de email inválido. Use como exemplo: user@domain.com");
        }
    }
}
