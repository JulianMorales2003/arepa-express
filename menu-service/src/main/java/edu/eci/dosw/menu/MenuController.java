package edu.eci.dosw.menu;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    public record Arepa(int id, String nombre, int precio) {}

    @GetMapping("/menu")
    public List<Arepa> menu() {
        return List.of(
            new Arepa(1, "Arepa de choclo con queso", 9000),
            new Arepa(2, "Arepa rellena de pollo", 11000),
            new Arepa(3, "Arepa de huevo", 7000));
    }
}
