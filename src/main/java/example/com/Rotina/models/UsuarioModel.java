package example.com.Rotina.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String senha;
    private String email;

    @OneToMany(mappedBy = "usuarioModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitoModel> habitos = new ArrayList<>();
  //  @JsonManagedReference

    // Getter e Setter para id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para senha
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Getter e Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter para a lista de hábitos
    public List<HabitoModel> getHabitos() {
        return habitos;
    }

    public void setHabitos(List<HabitoModel> habitos) {
        this.habitos = habitos;
    }

    public void addHabito(HabitoModel habito) {
        habitos.add(habito);
        habito.setUsuarioModel(this);
    }

    public void removeHabito(HabitoModel habito) {
        habitos.remove(habito);
        habito.setUsuarioModel(null);
    }
}
