package pe.edu.utp.apf1backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crear_debeRetornar400_cuandoNombreEsVacio() throws Exception {
        String body = "{ \"nombre\": \"\", \"precio\": -5}";
        
        mockMvc.perform(post("/api/productos")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscar_debeRetornar404_cuandoIdNoExiste() throws Exception {
        mockMvc.perform(get("/api/productos/999"))
                .andExpect(status().isNotFound());
    }
}