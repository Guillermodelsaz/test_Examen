package es.ufv.dis.final2022.GSL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Usuario {



    @SerializedName(value = "id")
    @JsonProperty("id")
    private int id;
    @SerializedName(value = "usuario")
    @JsonProperty("usuario")
    private String usuario;
    @SerializedName(value = "tweet")
    @JsonProperty("tweet")
    private String tweet;


// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @SerializedName(value = "fecha")
    @JsonProperty("fecha")
    String fecha;

    public Usuario(int id, String usuario, String tweet, String fecha) {
        this.id = id;
        this.usuario = usuario;
        this.tweet = tweet;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", tweet='" + tweet + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}

