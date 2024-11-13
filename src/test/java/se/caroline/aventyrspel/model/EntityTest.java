package se.caroline.aventyrspel.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    private Resident resident;
    private Burglar burglar;

    @BeforeEach
    void setUp() {
        // Här skapar jag  instanser av Resident och Burglar
        resident = new Resident();
        burglar = new Burglar();
    }
    @Test
    void punch() {
        // Spara initial hälsa för Burglar
        int initialHealth = burglar.getHealth();

        // Låt Resident attackera Burglar
        resident.punch(burglar);

        // Verifiera att Burglar's hälsa har minskat med Resident's skada
        int expectedHealthAfterPunch = initialHealth - resident.getDamage();
        assertEquals(expectedHealthAfterPunch, burglar.getHealth(),
                "Burglar's hälsa borde minska med Resident's skada. Burglars hälsa = 12, resident skada = 3");
    }

    }

