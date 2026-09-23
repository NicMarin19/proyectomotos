package gestion;

import datos.Espacio;
import datos.Moto;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;

public class Parqueadero {
    private int maxEspacios;
    private double tarifaMinuto;
    private double totalDia;
    private List<Espacio> espacios;

    public Parqueadero(int maxEspacios, double tarifaMinuto) {
        this.maxEspacios = maxEspacios;
        this.tarifaMinuto = tarifaMinuto;
        this.totalDia = 0.0;
        this.espacios = new ArrayList<>();

        for (int i = 1; i <= this.maxEspacios; i++) {
            espacios.add(new Espacio(i));
        }
    }

    public boolean registrarIngreso(String placa, String marca, String cedulaDueno) {
        for (Espacio espacio : espacios) {
            if (!espacio.estaOcupado()) {
                Moto nuevaMoto = new Moto(placa, marca, cedulaDueno);
                espacio.ocupar(nuevaMoto);
                return true;
            }
        }
        return false;
    }

    public Pago registrarSalida(String placaAConsultar, TipoPago tipoPago) {
        for (Espacio espacio : espacios) {
            if (espacio.estaOcupado() && espacio.getMotoAsignada().obtenerPlaca().equalsIgnoreCase(placaAConsultar)) {

                Moto moto = espacio.getMotoAsignada();
                espacio.liberar();

                LocalDateTime horaSalida = LocalDateTime.now();
                long minutos = Duration.between(moto.getHoraIngreso(), horaSalida).toMinutes();

                if (minutos == 0) {
                    minutos = 1;
                }

                double valorCobrado = minutos * this.tarifaMinuto;
                this.totalDia += valorCobrado;

                return new Pago(valorCobrado, tipoPago.name());
            }
        }

        return null;
    }

    public double calcularTotalDia() {
        return this.totalDia;
    }
}
