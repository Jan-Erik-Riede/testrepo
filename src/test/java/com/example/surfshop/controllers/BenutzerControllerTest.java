package com.example.surfshop.controllers;

import com.example.surfshop.entities.Benutzer;
import com.example.surfshop.repositories.BenutzerRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static com.example.surfshop.entities.Arbeitszeit.VOLLZEIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BenutzerControllerTest {

    private static BenutzerController benutzerController;
    private static BenutzerRepository mockedBenutzerRepository;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeClass
    public static void setUpBenutzerControllerInstance() {
        mockedBenutzerRepository = mock(BenutzerRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        benutzerController = new BenutzerController(mockedBenutzerRepository);
    }

    /**
     * When BindingResult ist False then return "redirect:/template"
     * */
    @Test
    public void whenCalledaddUser_ifFalse() {
        Benutzer benutzer = new Benutzer("Max", "Mustermann", "MaxiMus", "Test1", "2", "123456", "Max@Surfshop.de", VOLLZEIT, true);

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(benutzerController.addUser(benutzer, mockedBindingResult)).isEqualTo("redirect:/benutzeruebersicht-success");
    }

    /**
     * When BindingResult ist True then return "create-user"
     * */
    @Test
    public void whenCalledaddUser_ifTrue() {
        Benutzer benutzer = new Benutzer("Max", "Mustermann", "MaxiMus", "Test1", "2", "123456", "Max@Surfshop.de", VOLLZEIT, true);

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(benutzerController.addUser(benutzer, mockedBindingResult)).isEqualTo("createuser");
    }

    @Test
    public void whenCallShowCreateUser() {
        assertThat(benutzerController.showCreateUser(new Benutzer(), mockedModel)).isEqualTo("createuser");
    }

    @Test
    public void whenCallshowBenutzeruebersicht() {
        assertThat(benutzerController.showBenutzeruebersicht(mockedModel)).isEqualTo("benutzeruebersicht");
    }
}
