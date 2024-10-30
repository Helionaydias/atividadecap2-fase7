package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UsuarioModel {
    @Expose
    private String idUsuario;

    @Expose
    private String nmUsuario;

    @Expose
    private String dsEmail;

    @Expose
    private String nrCpf;

    @Expose
    private String dtNasc;
}
