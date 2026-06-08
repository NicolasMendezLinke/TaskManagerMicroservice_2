package org.example.userservice;

import org.example.userservice.model.Usuario;
import org.example.userservice.repository.HistoricoUsuarioRepository;
import org.example.userservice.repository.UsuarioRepository;
import org.example.userservice.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private HistoricoUsuarioRepository historicoRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveSalvarUsuario() {

        Usuario usuario = new Usuario();
        usuario.setNome("Nick");

        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuario);

        Usuario resultado =
                usuarioService.salvar(usuario);

        assertNotNull(resultado);
        assertEquals("Nick", resultado.getNome());

        verify(usuarioRepository, times(1))
                .save(usuario);

        verify(historicoRepository, times(1))
                .save(any());
    }
}
