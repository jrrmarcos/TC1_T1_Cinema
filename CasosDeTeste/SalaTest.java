import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SalaTest {

    private static Sala s;
    public static ArrayList<Sala> listaSalas;

    @BeforeAll
    public static void criaObjeto(){
        s = new Sala();
        listaSalas = new ArrayList<Sala>();
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    @DisplayName("Teste get/set de código")
    @Order(1)
    void getCodigo(int valor) {
        s.setCodigo(valor);
        assertEquals(valor, s.getCodigo());
    }

    @ParameterizedTest
    @ValueSource(ints = {10,20,30})
    @DisplayName("Teste get/set de capacidade")
    @Order(2)
    void getCapacidade(int valor) {
        s.setCapacidade(valor);
        assertEquals(valor, s.getCapacidade());

    }

    @ParameterizedTest
    @ValueSource(strings = {"Sala Um", "Sala Dois","Sala Três"})
    @DisplayName("Teste get/set do nome")
    @Order(3)
    void getNome(String nome) {
        s.setNome(nome);
        assertEquals(nome, s.getNome());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2D", "3D"})
    @DisplayName("Teste get/set do tipo de exibição")
    @Order(4)
    void getTipo_de_exibicao(String tipo) {
        s.setTipo_de_exibicao(tipo);
        assertEquals(tipo, s.getTipo_de_exibicao());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1699999999"})
    @DisplayName("Teste get/set de telefone")
    @Order(5)
    void getTelefone_sala(String tel) {
        s.setTelefone_sala(tel);
        assertEquals("(16)9999-9999", s.getTelefone_sala());
        s.setTelefone_sala("1699998888");
        assertEquals("(16)9999-8888", s.getTelefone_sala());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @DisplayName("Teste get/set de acessível")
    @Order(6)
    void isAcessivel(boolean b) {
        s.setAcessivel(b);
        assertEquals(b, s.isAcessivel());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    @DisplayName("Testa carrega salas")
    @Order(8)
    void carregaSalas(int posicao) {
        listaSalas = s.carregaSalas(listaSalas);

        ArrayList<Sala> salasPadrao = new ArrayList<>();

        salasPadrao.add(new Sala(1,30,"Sala 1","3D","1633115520",true));
        salasPadrao.add(new Sala(2,15,"Sala 2","2D","1633115590",true));
        salasPadrao.add(new Sala(3,50,"Sala 3","2D","1633115580", false));
        salasPadrao.add(new Sala(4,60,"Sala 4","2D","1633115570",true));
        salasPadrao.add(new Sala(5,45,"Sala 5","3D","1633115510", false));
        salasPadrao.add(new Sala(6,42,"Sala 6","4D","1633115530", true));
        salasPadrao.add(new Sala(7,55,"Sala 7","2D","1633115540", false));

        System.out.println(posicao);
        System.out.println(listaSalas.get(posicao).getNome());
        System.out.println(salasPadrao.get(posicao).getNome());

        assertAll("Teste carregar salas",
                () -> assertEquals(listaSalas.get(posicao).getCodigo(),salasPadrao.get(posicao).getCodigo()),
                () -> assertEquals(listaSalas.get(posicao).getCapacidade(),salasPadrao.get(posicao).getCapacidade()),
                () -> assertEquals(listaSalas.get(posicao).getNome(),salasPadrao.get(posicao).getNome()),
                () -> assertEquals(listaSalas.get(posicao).getTipo_de_exibicao(),salasPadrao.get(posicao).getTipo_de_exibicao()),
                () -> assertEquals(listaSalas.get(posicao).getTelefone_sala(),salasPadrao.get(posicao).getTelefone_sala()),
                () -> assertEquals(listaSalas.get(posicao).isAcessivel(),salasPadrao.get(posicao).isAcessivel())
        );
    }

    @Test
    @DisplayName("Teste buscar sala")
    @Order(9)
    void buscarSala() {
        ArrayList<Sala> salas = new ArrayList<>();
        Sala sala1 = new Sala(1,30,"Sala 1","3D","1633115520",true);

        salas = s.carregaSalas(salas);
        Sala sala2 = s.buscarSala(salas,1);

        assertAll("Teste buscar Filmes",
                () -> assertEquals(sala1.getCodigo(), sala2.getCodigo()),
                () -> assertEquals(sala1.getCapacidade(), sala2.getCapacidade()),
                () -> assertEquals(sala1.getNome(), sala2.getNome()),
                () -> assertEquals(sala1.getTipo_de_exibicao(), sala2.getTipo_de_exibicao()),
                () -> assertEquals(sala1.getTelefone_sala(), sala2.getTelefone_sala()),
                () -> assertEquals(sala1.isAcessivel(), sala2.isAcessivel())
        );
    }

    @ParameterizedTest
    @DisplayName("Teste deletar sala")
    @ValueSource(ints = {0,1,2,3,4,5})
    @Order(10)
    void deletaSala(int posicao) {
        ArrayList<Sala> salasPadrao = new ArrayList<>();

        listaSalas = s.carregaSalas(listaSalas);
        listaSalas = s.deletaSala(listaSalas, 3);

        salasPadrao.add(new Sala(1,30,"Sala 1","3D","1633115520",true));
        salasPadrao.add(new Sala(2,15,"Sala 2","2D","1633115590",true));
        //salasPadrao.add(new Sala(3,50,"Sala 3","2D","33115580", false));
        salasPadrao.add(new Sala(4,60,"Sala 4","2D","1633115570",true));
        salasPadrao.add(new Sala(5,45,"Sala 5","3D","1633115510", false));
        salasPadrao.add(new Sala(6,42,"Sala 6","4D","1633115530", true));
        salasPadrao.add(new Sala(7,55,"Sala 7","2D","1633115540", false));

        assertAll("Teste deletar salas",
                () -> assertEquals(listaSalas.get(posicao).getCodigo(),salasPadrao.get(posicao).getCodigo()),
                () -> assertEquals(listaSalas.get(posicao).getCapacidade(),salasPadrao.get(posicao).getCapacidade()),
                () -> assertEquals(listaSalas.get(posicao).getNome(),salasPadrao.get(posicao).getNome()),
                () -> assertEquals(listaSalas.get(posicao).getTipo_de_exibicao(),salasPadrao.get(posicao).getTipo_de_exibicao()),
                () -> assertEquals(listaSalas.get(posicao).getTelefone_sala(),salasPadrao.get(posicao).getTelefone_sala()),
                () -> assertEquals(listaSalas.get(posicao).isAcessivel(),salasPadrao.get(posicao).isAcessivel())
        );
    }

    @Test
    @DisplayName("Teste incluir sala")
    @Order(7)
    void incluirSala() {
        ArrayList<Sala> salas = new ArrayList<>();
        ArrayList<Sala> salaPadrao = new ArrayList<>();
        Sala s = new Sala(8,20,"Sala 8","3D","1633765432",true);

        s.incluirSala(salas,s);
        salaPadrao.add(s);

        assertArrayEquals(salas.toArray(), salaPadrao.toArray());
    }

    @Test
    @DisplayName("Teste alterar sala")
    @Order(11)
    void alterarSala() {
        ArrayList<Sala> salaPadrao = new ArrayList<>();
        Sala newSala= new Sala(7,100,"Sala oito","2D","16999999999",false);

        listaSalas = s.carregaSalas(listaSalas);
        s.alterarSala(listaSalas,newSala);

        assertAll("Teste alterar sala",
                () -> assertEquals(listaSalas.get(6).getCodigo(),newSala.getCodigo()),
                () -> assertEquals(listaSalas.get(6).getCapacidade(),newSala.getCapacidade()),
                () -> assertEquals(listaSalas.get(6).getNome(),newSala.getNome()),
                () -> assertEquals(listaSalas.get(6).getTipo_de_exibicao(),newSala.getTipo_de_exibicao()),
                () -> assertEquals(listaSalas.get(6).getTelefone_sala(),newSala.getTelefone_sala()),
                () -> assertEquals(listaSalas.get(6).isAcessivel(),newSala.isAcessivel())
        );
    }
}