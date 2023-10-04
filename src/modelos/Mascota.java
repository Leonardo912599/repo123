/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import java.util.Date;


public class Mascota {

    private int id_mascota;
    private String nombre;
    private String edad;
    private String raza;
    private String color;
    private String tamano;
    private String sexo;
    Date fecha_nacimiento;
    private String dni_cliente;
    private int id_animal;
    private Animal animal;

    public Mascota() {
    }

    public Mascota(int id_mascota, String nombre, String edad, String raza, String color, String tamano, String sexo, Date fecha_nacimiento, String dni_cliente, int id_animal, Animal animal) {
        this.id_mascota = id_mascota;
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.color = color;
        this.tamano = tamano;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.dni_cliente = dni_cliente;
        this.id_animal = id_animal;
        this.animal = animal;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    
}
