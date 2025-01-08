package example.com.Rotina.dto;

public class HabitoDto {
        private String name;
        private String descricao;
        private String horarioDesejado;
        private int frequenciaSemanal;

    public int getFrequenciaSemanal() {
        return frequenciaSemanal;
    }

    public void setFrequenciaSemanal(int frequenciaSemanal) {
        this.frequenciaSemanal = frequenciaSemanal;
    }

    public String getHorarioDesejado() {
        return horarioDesejado;
    }

    public void setHorarioDesejado(String horarioDesejado) {
        this.horarioDesejado = horarioDesejado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

