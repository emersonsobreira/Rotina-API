package example.com.Rotina.models;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "habitos")
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private LocalTime horarioDesejado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuarioModel;

    private int frequenciaSemanal;

}
