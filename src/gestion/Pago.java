package gestion;

import java.time.LocalDateTime;

public class Pago {
    private double valor;
    private String metodoPago;
    private LocalDateTime fechaHora;

    public Pago(double valor, String metodoPago) {
        this.valor = valor;
        this.metodoPago = metodoPago;
        this.fechaHora = LocalDateTime.now();
    }

    public void registrarPago() {
        System.out.println("Pago registrado: $" + valor + " mediante " + metodoPago);
    }

    public double getValor() { return valor; }
}
