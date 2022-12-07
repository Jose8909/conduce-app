package com.jose.conduceapp.modelo;

public class Horarios {
    //Declaracion de variables

    private String uId;
    private String DiaClase;
    private String HorarioClase;
    private String Instructor;
    private String nombreClase;

    //Constructor
    public Horarios() {

    }
    //Encapsulacion


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getDiaClase() {
        return DiaClase;
    }

    public void setDiaClase(String diaClase) {
        DiaClase = diaClase;
    }

    public String getHorarioClase() {
        return HorarioClase;
    }

    public void setHorarioClase(String horarioClase) {
        HorarioClase = horarioClase;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    //Metodo ToString


    @Override
    public String toString() {
        return "Horarios{" +
                "uId='" + uId + '\'' +
                ", DiaClase='" + DiaClase + '\'' +
                ", HorarioClase='" + HorarioClase + '\'' +
                ", Instructor='" + Instructor + '\'' +
                ", nombreClase='" + nombreClase + '\'' +
                '}';
    }
}
