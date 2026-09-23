package datos;

public class Espacio {
    private int numero;
    private boolean ocupado;
    private Moto motoAsignada; // Relación con la clase Moto del diagrama

    public Espacio(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.motoAsignada = null;
    }


    public void ocupar(Moto moto) {
        this.ocupado = true;
        this.motoAsignada = moto;
    }


    public void liberar() {
        this.ocupado = false;
        this.motoAsignada = null;
    }

    public boolean estaOcupado() {
        return this.ocupado;
    }

    public Moto getMotoAsignada() {
        return this.motoAsignada;
    }
}
