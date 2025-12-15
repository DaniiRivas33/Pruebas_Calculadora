/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author danie
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.netbeans.jemmy.operators.*;

public class Calculadora_CientificaTest {

    private static JFrameOperator frame;

    @BeforeAll
    public static void setUpClass() {
        new Thread(() -> Calculadora_Cientifica.main(new String[]{})).start();

        frame = new JFrameOperator("Calculadora_Cientifica");
    }

    @BeforeEach
    public void limpiar() {
        JButtonOperator C = new JButtonOperator(frame, "C");
        C.push();
    }

    // suma normal
    @Test
    public void testSuma() {
        JTextFieldOperator txt = new JTextFieldOperator(frame, 0);
        JTextFieldOperator txtRes = new JTextFieldOperator(frame, 1);

        new JButtonOperator(frame, "5").push();
        new JButtonOperator(frame, "+").push();
        new JButtonOperator(frame, "4").push();
        new JButtonOperator(frame, "=").push();

        assertEquals("9.0", txtRes.getText(), "5+4 debe ser 9");
    }

    // prueba op. combinadas
    @Test
    public void testOperacionesCombinadas() {
        JTextFieldOperator txtRes = new JTextFieldOperator(frame, 1);

        new JButtonOperator(frame, "5").push();
        new JButtonOperator(frame, "+").push();
        new JButtonOperator(frame, "3").push();
        new JButtonOperator(frame, "*").push();
        new JButtonOperator(frame, "4").push();
        new JButtonOperator(frame, "=").push();

        assertEquals("17.0", txtRes.getText(), "Debe respetar * antes que +");
    }

    // prueba division
    @Test
    public void testDivision() {
        JTextFieldOperator txtRes = new JTextFieldOperator(frame, 1);

        new JButtonOperator(frame, "4").push();
        new JButtonOperator(frame, "/").push();
        new JButtonOperator(frame, "2").push();
        new JButtonOperator(frame, "=").push();

        assertEquals("2.0", txtRes.getText(), "4/2 es 2");
    }

    //prueba trigonometria en radianes
    @Test
    public void testSenoRadianes() {
        JTextFieldOperator txt = new JTextFieldOperator(frame, 0);

        txt.setText("0");
        new JButtonOperator(frame, "sen").push();

        assertEquals("0.0", txt.getText(), "sen(0) en radianes debe ser 0");
    }

    // prueba trigonometria
    @Test
    public void testCos45Grados() {
        JTextFieldOperator txt = new JTextFieldOperator(frame, 0);

        // Cambiar a grados
        new JButtonOperator(frame, "Grados").push();

        txt.setText("60");
        new JButtonOperator(frame, "cos").push();

        assertEquals("0.5", txt.getText(), "cos(60°) debe ser 0.5");
    }

    // numero pi correcto
    @Test
    public void testBotonPi() {
        JTextFieldOperator txt = new JTextFieldOperator(frame, 0);

        new JButtonOperator(frame, "π").push();

        assertEquals(String.valueOf(Math.PI), txt.getText());
    }

    // decimales
    @Test
    public void testDecimal() {
        JTextFieldOperator txt = new JTextFieldOperator(frame, 0);

        new JButtonOperator(frame, "1").push();
        new JButtonOperator(frame, ",").push();
        new JButtonOperator(frame, "5").push();

        assertEquals("1.5", txt.getText());
    }

    
    //  añadir texto
    @Test
    public void testErrorNumero() {
        JTextFieldOperator txt = new JTextFieldOperator(frame, 0);

        txt.setText("abc");
        new JButtonOperator(frame, "sen").push();

        assertEquals("Error", txt.getText());
    }
}
