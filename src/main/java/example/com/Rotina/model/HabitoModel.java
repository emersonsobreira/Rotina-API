package example.com.Rotina.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitos")
public class HabitoModel {

    private String nome;
    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horarioDesejado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private UsuarioModel usuarioModel;

    private int frequenciaSemanal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
}
