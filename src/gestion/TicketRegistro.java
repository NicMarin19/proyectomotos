package gestion;

import java.time.LocalDateTime;

public class TicketRegistro {
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;
    private LocalDateTime fechaHora;

    public TicketRegistro(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
        this.fechaHora = LocalDateTime.now();
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double calcularValor() {
        // Lógica a implementar dependiendo de la tarifa
        return 0.0;
    }
}
