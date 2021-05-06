package com.example.surfshop.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Bitte geben Sie ein Vorname ein!")
    private String vorname;
    @NotBlank(message = "Bitte geben Sie ein Nachname ein!")
    private String nachname;
    @Column(unique = true)
    @NotBlank(message = "Bitte geben Sie ein Benutzername ein!")
    private String benutzername;
    @Length(min = 3, message = "Passwort muss mindestens 3 Zeichen lang sein!")
    private String passwort;
    @NotBlank(message = "Bitte geben Sie Urlaubstage an")
    @Length(max = 2, message = "keiner bekommt mehr als 100 Urlaubstage")
    private String urlaubstage;
    @NotBlank(message = "Bitte geben Sie eine Telefonnummer ein!")
    private String telefonnummer;
    @NotBlank(message = "Bitte geben Sie eine email ein!")
    private String email;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Bitte waehlen")
    private Arbeitszeit arbeitszeit;
    private boolean angemeldet;
    @NotNull(message = "Bitte eine Rolle auswaehlen")
    private String rolle;

    public Benutzer() {
    }

    /**
     * Konstruktor wird für den Test BenutzerController benoetigt.
     * */
    public Benutzer(String vorname, String nachname, String benutzername, String passwort, String urlaubstage, String telefonnummer, String email, Arbeitszeit arbeitszeit, boolean chef) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.urlaubstage = urlaubstage;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.arbeitszeit = arbeitszeit;
    }
    /*---------------------------- Getter und Setter ----------------------------*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getUrlaubstage() {
        return urlaubstage;
    }

    public void setUrlaubstage(String urlaubstage) {
        this.urlaubstage = urlaubstage;
    }

    public Arbeitszeit getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(Arbeitszeit arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAngemeldet() {
        return angemeldet;
    }

    public void setAngemeldet(boolean angemeldet) {
        this.angemeldet = angemeldet;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", benutzername='" + benutzername + '\'' +
                ", passwort='" + passwort + '\'' +
                ", urlaubstage='" + urlaubstage + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                ", email='" + email + '\'' +
                ", arbeitszeit=" + arbeitszeit +
                ", angemeldet=" + angemeldet +
                ", rolle='" + rolle + '\'' +
                '}';
    }
}
