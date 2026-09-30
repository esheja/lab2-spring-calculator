package seg3502.calculator

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class WebController {

    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("premierNombre", "")
        model.addAttribute("secondNombre", "")
        model.addAttribute("resultat", "")
        model.addAttribute("messageErreur", "")
    }

    @RequestMapping("/")
    fun home(): String = "calculator"

    @GetMapping("/calculer")
    fun calculer(
        @RequestParam(value = "premierNombre", required = false) premierNombre: String?,
        @RequestParam(value = "secondNombre", required = false) secondNombre: String?,
        @RequestParam(value = "operation", required = false) operation: String?,
        model: Model
    ): String {
        model.addAttribute("premierNombre", premierNombre ?: "")
        model.addAttribute("secondNombre", secondNombre ?: "")

        val a = premierNombre?.toDoubleOrNull()
        val b = secondNombre?.toDoubleOrNull()

        if (a == null || b == null) {
            model.addAttribute("messageErreur", "Erreur : valeurs invalides")
            model.addAttribute("resultat", "")
            return "calculator"
        }

        when (operation) {
            "+" -> model.addAttribute("resultat", formater(a + b))
            "-" -> model.addAttribute("resultat", formater(a - b))
            "*" -> model.addAttribute("resultat", formater(a * b))
            "/" -> {
                if (b == 0.0) {
                    model.addAttribute("messageErreur", "Erreur : division par zéro")
                    model.addAttribute("resultat", "")
                } else {
                    model.addAttribute("resultat", formater(a / b))
                }
            }
            else -> {
                model.addAttribute("messageErreur", "Erreur : opération inconnue")
                model.addAttribute("resultat", "")
            }
        }
        return "calculator"
    }

    private fun formater(v: Double): String {
        return if (v == v.toLong().toDouble()) v.toLong().toString()
        else v.toString()
    }
}
