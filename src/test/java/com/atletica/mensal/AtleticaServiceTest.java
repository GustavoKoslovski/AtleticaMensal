package com.atletica.mensal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Repository.AtleticaRepository;
import com.atletica.mensal.Service.AtleticaService;

@SpringBootTest
public class AtleticaServiceTest {


    @Mock
    private AtleticaRepository atleticaRepository;

    @InjectMocks
    private AtleticaService atleticaService;

    public AtleticaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarAtletica() {
        AtleticaEntity atletica = new AtleticaEntity();
        atletica.setNome("Atlética Ursao");
        when(atleticaRepository.save(any(AtleticaEntity.class))).thenReturn(atletica);

        AtleticaEntity resultado = atleticaService.salvarAtletica(atletica);

        assertNotNull(resultado);
        assertEquals("Atlética Ursao", resultado.getNome());
    }

    @Test
    void testBuscarAtleticaPorId() {
        AtleticaEntity atletica = new AtleticaEntity();
        atletica.setId(1L);
        atletica.setNome("Atlética Panda");

        when(atleticaRepository.findById(1L)).thenReturn(Optional.of(atletica));

        Optional<AtleticaEntity> resultado = atleticaService.buscarAtleticaPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Atlética Panda", resultado.get().getNome());
    }


    @Test
    void testDeletarAtletica() {
        AtleticaEntity atletica = new AtleticaEntity();
        atletica.setId(1L);
        atletica.setNome("Atlética Tucanos");

        when(atleticaRepository.findById(1L)).thenReturn(Optional.of(atletica));
        doNothing().when(atleticaRepository).delete(atletica);

        atleticaService.deletarAtletica(1L);

        verify(atleticaRepository, times(1)).delete(atletica);
    }
}
