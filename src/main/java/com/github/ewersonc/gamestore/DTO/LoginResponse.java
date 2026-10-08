package com.github.ewersonc.gamestore.DTO;

public class LoginResponse {
    private String id;
    private String nome;
    private String email;
    private String token;


    public LoginResponse(String id, String nome, String email, String token) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.token = token;
    }


    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getToken() { return token; }
}
