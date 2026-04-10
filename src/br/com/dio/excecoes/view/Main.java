package br.com.dio.excecoes.view;
import br.com.dio.excecoes.dao.UserDAO;

import br.com.dio.excecoes.exceptions.*;
import br.com.dio.excecoes.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static br.com.dio.excecoes.validator.UserValidator.verifyModel;

public class Main {

    private final static UserDAO dao = new UserDAO();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main (String [] args){

        while(true){
            System.out.println("\nBem-vindo ao cadastro de usuários. Selecione a opção desejada: \n");

            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar por ID");
            System.out.println("5 - Listar");
            System.out.println("6 - Sair");

            var userInput = scanner.nextInt();

            var selectedOption = MenuOption.values()[userInput - 1];

            switch (selectedOption) {
                case SAVE -> {
                    try{
                        var user = dao.save(requestToSave());
                        System.out.printf("Usuário %s cadastrado!", user);
                    }catch(ValidatorException ex){
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }

                }
                case UPDATE -> {
                    try{
                        var user = dao.update(requestToUpdate());
                        System.out.printf("Usuário %s alterado!", user);
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }catch(ValidatorException ex){
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
                case DELETE -> {
                    try{
                        dao.delete(requestId());
                        System.out.println("Usuário excluído!");
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }
                    finally {
                        System.out.println("=======================");
                    }
                }
                case FIND_BY_ID -> {
                    try{
                        var id = requestId();
                        var user = dao.findById(id);

                        System.out.printf("Usuário com o ID %s:", id);
                        System.out.println(user);
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }

                }
                case FIND_ALL -> {
                    try{
                        var users = dao.findAll();

                        System.out.println("Usuários cadastrados:");
                        System.out.println("======================");

                        users.forEach(System.out::println);
                        System.out.println("======================");

                    }catch (EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                case EXIT -> {
                    System.exit(0);
                }
            }
        }

    }

    private static long requestId(){
        System.out.println("ID:\n");
        return scanner.nextLong();
    }

    private static UserModel validateInputs(final long id, final String name, final String email, final LocalDate birthday) throws ValidatorException{
        var user = new UserModel(0, name, email, birthday);
        verifyModel(user);
        return user;
    }

    private static UserModel requestToSave() throws ValidatorException {
        System.out.println("Nome:\n");
        var name = scanner.next();
        System.out.println("Email:\n");
        var email = scanner.next();
        System.out.println("Data de Nascimento (dd/MM/yyyy):\n");
        var birthdayString = scanner.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        var birthday = LocalDate.parse(birthdayString, formatter);

        return validateInputs(0, name, email, birthday);
    }

    private static UserModel requestToUpdate() throws ValidatorException {
        System.out.println("ID:\n");
        var id = scanner.nextLong();
        System.out.println("Nome:\n");
        var name = scanner.next();
        System.out.println("Email:\n");
        var email = scanner.next();
        System.out.println("Data de Nascimento (dd/MM/yyyy):\n");
        var birthdayString = scanner.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        var birthday = LocalDate.parse(birthdayString, formatter);

        UserModel model = new UserModel();

        return validateInputs(0, name, email, birthday);
    }
}
