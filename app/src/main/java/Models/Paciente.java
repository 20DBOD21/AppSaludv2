package Models;

import android.net.Uri;

public class Paciente extends Persona{
    private String ciudad;
    private String dni;
    private double peso;
    private double altura;
    private byte[] foto;

    public Paciente() {
        super();
    }

    public Paciente(String nombres, String apellidos, String genero, int edad, String ciudad, String dni, double peso, double altura, byte[] foto) {
        super(nombres, apellidos, genero, edad);
        this.ciudad = ciudad;
        this.dni = dni;
        this.peso = peso;
        this.altura = altura;
        this.foto = foto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String nombreCompleto() {
        return getNombres() + " " + getApellidos();
    }

    public int calcularIMC() {
        double par = peso / (altura * altura);
        return par < 20 ? -1 : par > 25 ? 1 : 0;
    }

    public boolean mayorDeEdad() {
        return getEdad() >= 18;
    }

    public static boolean verificarDNI(String dni) {
        return dni.length() == 8;
    }

    public String edadPersona() {
        return "Edad: " + (mayorDeEdad() ? "Mayor de Edad" : "Menor de Edad");
    }

    public String pesoPersona() {
        String[] tipoPeso = { "Por debajo de lo Ideal", "En lo Ideal", "Por encima de lo Ideal" };
        return "Peso: " + tipoPeso[ calcularIMC() + 1 ];
    }

    @Override
    public String toString() {
        return "Persona: " + nombreCompleto() + ", tiene un peso " + pesoPersona() + ", y es " + edadPersona();
    }
}
