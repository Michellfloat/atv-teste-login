package validar.login.model;

import validar.login.model.enums.NivelUsuario;

public class Usuario {
    private String nome;
    private String login;
    private String email;
    private String senha;
    private NivelUsuario nivel;
    private int tentativasInvalidas;
    private boolean bloqueado;

    public Usuario(String nome, String login, String email, String senha, NivelUsuario nivel){
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
        this.tentativasInvalidas = 0;
        this.bloqueado = false;
    }

    // Getters e Setters
    public String getNome(){return nome;}
    public String getLogin(){return login;}
    public String getEmail(){return email;}
    public String getSenha(){return senha;}
    public NivelUsuario getNivel(){return nivel;}
    public int getTentativasInvalidas(){return tentativasInvalidas;}
    public boolean isBloqueado(){return bloqueado;}

    public void incrementarTentativas(){
        this.tentativasInvalidas++;
        if (this.tentativasInvalidas >= 3) {
            this.bloqueado = true;
        }
    }

    public void resetarTentativas(){
        this.tentativasInvalidas = 0;
    }
}
