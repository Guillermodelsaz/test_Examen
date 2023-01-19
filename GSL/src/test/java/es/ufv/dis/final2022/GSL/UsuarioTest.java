package es.ufv.dis.final2022.GSL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class UsuarioTest {

    @Test
    void eliminado(){
        UsuarioController testo = new UsuarioController();
        int valor_esperado = testo.users().size()-1;

        int valor = testo.delete_user(2).size();
        Assertions.assertEquals(valor_esperado, valor);
    }
}
