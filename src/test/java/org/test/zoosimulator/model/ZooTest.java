package org.test.zoosimulator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZooTest {
    private Zoo zoo;
    private Animal lion;
    private Animal tiger;
    private Animal zebra;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        zoo = new Zoo();
        lion = mock(Lion.class);
        tiger = mock(Tiger.class);
        zebra = mock(Zebra.class);
        zoo.addAnimal(lion);
        zoo.addAnimal(tiger);
        zoo.addAnimal(zebra);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testFeedAnimalWhenZooIsOpen() {
        zoo.open();
        zoo.feedAnimal(lion);
        Mockito.verify(lion).eat();
    }

    @Test
    public void testFeedAnimalWhenZooIsClosed() {
        zoo.feedAnimal(lion);
        assertEquals("Zoo is closed !!\n", outputStream.toString());
        Mockito.verifyNoInteractions(lion);
    }

    @Test
    public void testFeedAnimalWithUnknownAnimal() {
        Animal unknownAnimal = mock(Animal.class);
        assertThrows(IllegalArgumentException.class, () -> zoo.feedAnimal(unknownAnimal));
        Mockito.verifyNoInteractions(lion, tiger, zebra);
    }

    @Test
    public void testOpenWhenZooIsClosed() {
        zoo.open();
        assertTrue(zoo.isOpen());
        Mockito.verify(lion).play();
        Mockito.verify(tiger).play();
        Mockito.verify(zebra).play();
    }

    @Test
    public void testOpenWhenZooIsAlreadyOpen() {
        zoo.open(); // First open
        reset(lion, tiger, zebra);
        zoo.open(); // Second open
        assertTrue(zoo.isOpen());
        Mockito.verifyNoInteractions(lion, tiger, zebra);
    }

    @Test
    public void testCloseWhenZooIsOpen() {
        zoo.open();
        zoo.close();
        assertFalse(zoo.isOpen());
        Mockito.verify(lion).sleep();
        Mockito.verify(tiger).sleep();
        Mockito.verify(zebra).sleep();
    }

    @Test
    public void testCloseWhenZooIsAlreadyClosed() {
        zoo.close(); // First close
        reset(lion, tiger, zebra);
        zoo.close(); // Second close
        assertFalse(zoo.isOpen());
        Mockito.verifyNoInteractions(lion, tiger, zebra);
    }

    @Test
    public void testPrintDailyReport() {
        when(lion.getName()).thenReturn("Simbha");
        when(lion.getAge()).thenReturn(5);
        when(lion.getHealth()).thenReturn(100);
        when(lion.getEnergy()).thenReturn(80);
        when(tiger.getName()).thenReturn("Sher Khan");
        when(tiger.getAge()).thenReturn(8);
        when(tiger.getHealth()).thenReturn(90);
        when(tiger.getEnergy()).thenReturn(70);
        when(zebra.getName()).thenReturn("Zara");
        when(zebra.getAge()).thenReturn(10);
        when(zebra.getHealth()).thenReturn(80);
        when(zebra.getEnergy()).thenReturn(90);

        zoo.printDailyReport();

        List<String> expectedOutput = Arrays.asList(
                "Name: Simbha",
                "Age: 5",
                "Health: 100",
                "Energy: 80",
                "===============",
                "Name: Sher Khan",
                "Age: 8",
                "Health: 90",
                "Energy: 70",
                "===============",
                "Name: Zara",
                "Age: 10",
                "Health: 80",
                "Energy: 90",
                "===============");

        assertEquals(String.join("\n", expectedOutput) + "\n", outputStream.toString());
    }
}