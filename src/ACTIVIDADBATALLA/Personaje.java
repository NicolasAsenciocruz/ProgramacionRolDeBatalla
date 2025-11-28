package ACTIVIDADBATALLA;

public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private Tipo tipo;

    public Personaje(String nombre, int vida, int ataque, int defensa, Tipo tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public void atacar(Personaje enemigo) {
        int danio=this.ataque-enemigo.defensa;
        if(danio<0){
            danio=0;
        }
        enemigo.recibirDanio(danio);
    }

    public void recibirDanio(int danio) {
        this.vida-= danio;
        if(this.vida<0){
            this.vida=0;
        }

    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    @Override
    public String toString() {
        return  "\n=====PERSONAJE=====" +
                "\nNombre  ="  + nombre +
                "\nVida    ="  + vida +
                "\nAtaque  ="  + ataque +
                "\nDefensa ="  + defensa +
                "\nTipo    ="  + tipo+
                "\n==================";

    }
}