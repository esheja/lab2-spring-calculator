package seg3502.calculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.ui.ExtendedModelMap
import org.springframework.ui.Model

class WebControllerTest {

    private val controller = WebController()

    private fun model(): Model = ExtendedModelMap()

    @Test
    fun home_returns_calculator_view() {
        assertEquals("calculator", controller.home())
    }

    @Test
    fun addition_test() {
        val m = model()
        val view = controller.calculer("2", "3", "+", m)
        assertEquals("calculator", view)
        assertEquals("5", m.getAttribute("resultat"))
    }

    @Test
    fun soustraction_test() {
        val m = model()
        controller.calculer("10", "4", "-", m)
        assertEquals("6", m.getAttribute("resultat"))
    }

    @Test
    fun multiplication_test() {
        val m = model()
        controller.calculer("6", "7", "*", m)
        assertEquals("42", m.getAttribute("resultat"))
    }

    @Test
    fun division_test() {
        val m = model()
        controller.calculer("15", "4", "/", m)
        assertEquals("3.75", m.getAttribute("resultat"))
    }

    @Test
    fun division_par_zero_test() {
        val m = model()
        controller.calculer("5", "0", "/", m)
        assertEquals("Erreur : division par zéro", m.getAttribute("messageErreur"))
        assertEquals("", m.getAttribute("resultat"))
    }

    @Test
    fun invalid_number_test() {
        val m = model()
        controller.calculer("abc", "3", "+", m)
        assertEquals("Erreur : valeurs invalides", m.getAttribute("messageErreur"))
    }
}
