package ACTIVIDADBATALLA;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Batalla {
    ArrayList<Personaje> heroes;
    ArrayList<Personaje> villanos;
    Scanner sc;
    Random rand;

    public Batalla() {
        sc=new Scanner(System.in);
        heroes = new ArrayList<>();
        villanos = new ArrayList<>();
        this.rand=new Random();
    }

    public void MostrarMenu() {

        int opcion;
        do {
            System.out.println("==MENU BATALLA==");
            System.out.println("1. AÑADIR CABALLERO");
            System.out.println("2. AÑADIR MAGO");
            System.out.println("3. AÑADIR ORCO");
            System.out.println("4. INICIAR BATALLA");
            System.out.println("5. MOSTRAR LISTA HEROES Y VILLANOS");
            System.out.println("6. SALIR");
            System.out.println("Introduce una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    crearPersonaje(false, Tipo.Caballero);
                    break;
                case 2:
                    crearPersonaje(false, Tipo.Mago);
                    break;
                case 3:
                    crearPersonaje(true, Tipo.Orco);
                    break;
                case 4:
                    iniciarBatalla();
                    break;
                case 5:
                    muestraListas();
                    break;
                case 6:
                    System.out.println("Saliste del juego");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Esta opcion no existe");
                    break;
            }
        } while (opcion!=6);
    }


    public void crearPersonaje(boolean esOrco, Tipo tipo) {
        System.out.println("Ingrese nombre del personaje: ");
        String Nombre = this.sc.next();
        System.out.println("Ingrese la vida del personaje: ");
        int vida = this.sc.nextInt();
        System.out.println("Introduce su Ataque: ");
        int ataque = this.sc.nextInt();
        System.out.println("Introduce su defensa: ");
        int defensa = this.sc.nextInt();

        Personaje p = new Personaje(Nombre, vida, ataque, defensa, tipo);

        if (esOrco) {
            this.villanos.add(p);
        } else {
            this.heroes.add(p);
        }
        System.out.println("Estadisticas y datos de tu personaje" + p);

    }


    public void muestraListas() {
        System.out.println("===LISTA DE HEROES===");
        for (Personaje h : this.heroes) {
            System.out.println(h);
        }
        System.out.println("===LISTA DE VILLANOS===");
        for (Personaje v : this.villanos) {
            System.out.println(v);
        }
    }


    public void iniciarBatalla() {
        if (this.heroes.isEmpty() || this.villanos.isEmpty()) {
            System.out.println("No se puede iniciar la batalla, debes tener heroes y villanos para luchar");
            return;
        }

        System.out.println("========COMIENZO DE LA BATALLA========");
        while (!heroes.isEmpty() && !villanos.isEmpty()) {
            Personaje h = heroes.get(rand.nextInt(heroes.size()));
            Personaje v = villanos.get(rand.nextInt(villanos.size()));

            System.out.println("===============COMBATE================");
            System.out.println("============ENFRENTAMIENTO============");
            System.out.println("heroe: " + h);
            System.out.println("====VS====");
            System.out.println("villano: " + v);

            // El héroe ataca primero
            h.atacar(v);

            // Solo si el villano sigue vivo, contraataca
            if (v.estaVivo()) {
                v.atacar(h);
            }

            System.out.println("===ATAQUES CONCLUIDOS===");
            System.out.println("Heroe: " + h.getVida() + " vida");
            System.out.println("Villano: " + v.getVida() + " vida");

            // COMPROBAMOS MUERTES
            if (!h.estaVivo()) {
                System.out.println("Tu heroe ha sido derrotado");
                this.heroes.remove(h);
            }
            if (!v.estaVivo()) {
                System.out.println("El villano ha sido derrotado");
                this.villanos.remove(v);
            }

            System.out.println();
            this.muestraListas();
        }

        // ANUNCIAR GANADOR
        if (this.heroes.isEmpty()) {
            System.out.println("LOS VILLANOS HAN GANADO LA BATALLA");
        } else if (this.villanos.isEmpty()) {
            System.out.println("LOS HEROES HAN GANADO LA BATALLA!");
        }
    }

}
