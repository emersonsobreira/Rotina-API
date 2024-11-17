package example.com.Rotina.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
    @Table(name = "registroAtividade")

public class RegistroAtividadeModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "habito_id")
        private HabitoModel habito;

        private LocalDateTime dataConclusao;

}
