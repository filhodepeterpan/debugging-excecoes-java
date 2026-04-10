package br.com.dio.excecoes.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public class UserModel{
    private long id;
    private String name;
    private String email;
    private OffsetDateTime birthday;

    public UserModel() {
    }

    public UserModel(long id, String name, String email, OffsetDateTime birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    // GETTERS e SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(OffsetDateTime birthday) {
        this.birthday = birthday;
    }

    // OUTROS MÉTODOS


    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // Verifica se os dois objetos são exatamente o mesmo na memória
        // (mesma referência). Se for, já são iguais.
        if (this == o) return true;

        // Verifica se o objeto passado é null OU se é de outra classe
        // Se qualquer um desses for verdadeiro, não podem ser iguais
        if (o == null || getClass() != o.getClass()) return false;

        // Faz o cast do objeto genérico (Object) para UserModel
        // Agora podemos acessar os atributos (id, name, etc.)
        UserModel userModel = (UserModel) o;

        // Compara todos os atributos relevantes do objeto
        // id é primitivo → usa ==
        // objetos (String, OffsetDateTime) → usa Objects.equals para evitar erro com null
        return id == userModel.id &&
                Objects.equals(name, userModel.name) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(birthday, userModel.birthday);
    }

    @Override
    public int hashCode() {
        // Gera um número (hash) baseado nos atributos do objeto
        // Esse número é usado por estruturas como HashSet e HashMap
        // Objetos com os mesmos valores devem gerar o mesmo hash
        return Objects.hash(id, name, email, birthday);
    }
}

