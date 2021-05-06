package com.example.surfshop.service;

import com.example.surfshop.entities.Benutzer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 30.04.2021
 */
public class CustomUserDetails implements UserDetails {

    private Benutzer benutzer;

    public CustomUserDetails(Benutzer benutzer) {
        this.benutzer = benutzer;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(benutzer.getRolle()));
    }

    @Override
    public String getPassword() {
        return benutzer.getPasswort();
    }

    @Override
    public String getUsername() {
        return benutzer.getBenutzername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    /**
     * Wir müssen die equals Methode überschreiben damit wir richtig
     * prüfen können ob der Benutzer bereits angemeldet ist
     * @param otherUser
     * @return
     */
    @Override
    public boolean equals(Object otherUser) {
        if(otherUser == null) return false;
        else if (!(otherUser instanceof UserDetails)) return false;
        else return (otherUser.hashCode() == hashCode());
    }

    /**
     * Wir müssen die hashCode Methode überschreiben damit wir hier richtig den Benutzernamen prüfen
     * @return
     */
    @Override
    public int hashCode() {
        return benutzer.getBenutzername().hashCode() ;
    }
}
