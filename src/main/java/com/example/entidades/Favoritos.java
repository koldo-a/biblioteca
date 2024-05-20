package com.example.entidades;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component("favoritosEntidades")
@SessionScope
public class Favoritos {
    private List<Long> favoritos = new ArrayList<>();

    public List<Long> getFavoritos() {
        return favoritos;
    }

    public void addFavorito(Long id) {
        if (!favoritos.contains(id)) {
            favoritos.add(id);
        }
    }

    public void removeFavorito(Long id) {
        favoritos.remove(id);
    }

    public boolean isFavorito(Long id) {
        return favoritos.contains(id);
    }
}

