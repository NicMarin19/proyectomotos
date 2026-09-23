package datos;

import java.time.LocalDateTime;

public class Moto {
    private String placa;
    private String marca;
    private String cedulaDueno;
    private LocalDateTime horaIngreso;

    public Moto(String placa, String marca, String cedulaDueno) {
        this.placa = placa;
        this.marca = marca;
        this.cedulaDueno = cedulaDueno;
        this.horaIngreso = LocalDateTime.now();
    }

    public String obtenerPlaca() {
        return this.placa;
    }

    public String getMarca() { return marca; }
    public String getCedulaDueno() { return cedulaDueno; }
    public LocalDateTime getHoraIngreso() { return horaIngreso; }
}
