package com.jose.conduceapp.modelo;

public class Referidos {
    private String uId;
    private String ContactoReferido;
    private String Contactorefiere;
    private String DocumentoReferido;
    private String DocumentoRefiere;
    private String LicenciaInteres;
    private String NombreReferido;
    private String NombreRefiere;

    //Constructor
    public Referidos()
    {

    }
    //Encapsulacion


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getContactoReferido() {
        return ContactoReferido;
    }

    public void setContactoReferido(String contactoReferido) {
        ContactoReferido = contactoReferido;
    }

    public String getContactorefiere() {
        return Contactorefiere;
    }

    public void setContactorefiere(String contactorefiere) {
        Contactorefiere = contactorefiere;
    }

    public String getDocumentoReferido() {
        return DocumentoReferido;
    }

    public void setDocumentoReferido(String documentoReferido) {
        DocumentoReferido = documentoReferido;
    }

    public String getDocumentoRefiere() {
        return DocumentoRefiere;
    }

    public void setDocumentoRefiere(String documentoRefiere) {
        DocumentoRefiere = documentoRefiere;
    }

    public String getLicenciaInteres() {
        return LicenciaInteres;
    }

    public void setLicenciaInteres(String licenciaInteres) {
        LicenciaInteres = licenciaInteres;
    }

    public String getNombreReferido() {
        return NombreReferido;
    }

    public void setNombreReferido(String nombreReferido) {
        NombreReferido = nombreReferido;
    }

    public String getNombreRefiere() {
        return NombreRefiere;
    }

    public void setNombreRefiere(String nombreRefiere) {
        NombreRefiere = nombreRefiere;
    }

    //Metodo ToString


    @Override
    public String toString() {
        return "Referidos{" +
                "uId='" + uId + '\'' +
                ", ContactoReferido='" + ContactoReferido + '\'' +
                ", Contactorefiere='" + Contactorefiere + '\'' +
                ", DocumentoReferido='" + DocumentoReferido + '\'' +
                ", DocumentoRefiere='" + DocumentoRefiere + '\'' +
                ", LicenciaInteres='" + LicenciaInteres + '\'' +
                ", NombreReferido='" + NombreReferido + '\'' +
                ", NombreRefiere='" + NombreRefiere + '\'' +
                '}';
    }
}
