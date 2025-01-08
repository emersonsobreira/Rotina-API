package example.com.Rotina.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
@Table(name = "habitos")
public class HabitoModel {
    @NotNull
    private String name;
    @NotNull
    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull
    private LocalTime horarioDesejado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserModel usuarioModel;

    private int frequenciaSemanal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter e Setter para descricao
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter e Setter para horarioDesejado
    public LocalTime getHorarioDesejado() {
        return horarioDesejado;
    }

    public void setHorarioDesejado(LocalTime horarioDesejado) {
        this.horarioDesejado = horarioDesejado;
    }

    // Getter e Setter para frequenciaSemanal
    public int getFrequenciaSemanal() {
        return frequenciaSemanal;
    }

    public void setFrequenciaSemanal(int frequenciaSemanal) {
        this.frequenciaSemanal = frequenciaSemanal;
    }

    // Getter e Setter para usuarioModel
    public UserModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UserModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
}
