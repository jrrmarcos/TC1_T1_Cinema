import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FilmeTest {

    private static Filme f;
    public static ArrayList<Filme> listaFilmes;


    @BeforeAll
    public static void criaObjeto(){
        f = new Filme();
    }
    public static void criaArrayList(){
        listaFilmes = new ArrayList<>();
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    @DisplayName("Teste get/set de código")
    @Order(1)
    void getCodigo(int valor) {
        f.setCodigo(valor);
        assertEquals(valor, f.getCodigo());
    }

    @ParameterizedTest
    @ValueSource(ints = {1997,2004,2013})
    @DisplayName("Teste get/set do ano de lançamento")
    @Order(2)
    void getAno_de_lancamento(int ano) {
        f.setAno_de_lancamento(ano);
        assertEquals(ano, f.getAno_de_lancamento());
    }

    @ParameterizedTest
    @ValueSource(strings = {"O Máskara", "Deadpool","Titanic"})
    @DisplayName("Teste get/set de nome")
    @Order(3)
    void getNome(String nome) {
        f.setNome(nome);
        assertEquals(nome, f.getNome());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Marcos", "Aurélio","Pereira","Júnior"})
    @DisplayName("Teste get/set de diretor")
    @Order(4)
    void getDiretor(String diretor) {
        f.setDiretor(diretor);
        assertEquals(diretor, f.getDiretor());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Marcos", "Aurélio","Pereira","Júnior"})
    @DisplayName("Teste get/set de ator")
    @Order(5)
    void getAtor(String ator) {
        f.setAtor(ator);
        assertEquals(ator, f.getAtor());
    }

    @Test
    @DisplayName("Teste inclusão de filmes")
    @Order(6)
    void testIncluirFilme() {
        ArrayList<Filme> filmes = new ArrayList<>();
        ArrayList<Filme> filmesPadrao = new ArrayList<>();
        Filme filme = new Filme(8,2021,"Velozes e Furiosos 9","Justin Lin","Vin Diesel");

        f.incluirFilme(filmes,filme);
        filmesPadrao.add(filme);

        assertArrayEquals(filmes.toArray(), filmesPadrao.toArray());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    @DisplayName("Testa carrega filmes")
    @Order(8)
    void testCarregaFilmes(int posicao) {
        ArrayList<Filme> filmesPadrao = new ArrayList<>();

        listaFilmes = f.carregaFilmes(listaFilmes);

        filmesPadrao.add(new Filme(1,1997,"Titanic","James Cameron","Leonardo DiCaprio"));
        filmesPadrao.add(new Filme(2,2016,"Deadpool"," Tim Miller","Ryan Reynolds"));
        filmesPadrao.add(new Filme(3,2013,"Invocação do Mal","James Wan","Vera Farmiga"));
        filmesPadrao.add(new Filme(4,2013,"Truque de Mestre","Louis Leterrier","Mark Ruffalo"));
        filmesPadrao.add(new Filme(5,2017,"It: A Coisa","Andy Muschietti","Bill Skarsgård"));
        filmesPadrao.add(new Filme(6,1994,"O Máskara","Chuck Russell","Jim Carrey"));
        filmesPadrao.add(new Filme(7,2019,"It – Capítulo Dois","Andy Muschietti","Bill Skarsgård"));

        //TESTES DE DEBUG
        //System.out.println(posicao);
        //System.out.println(listaFilmes.get(posicao).getNome());
        //System.out.println(filmesPadrao.get(posicao).getNome());

        assertAll("Teste carregar Filmes",
                () -> assertEquals(listaFilmes.get(posicao).getCodigo(),filmesPadrao.get(posicao).getCodigo()),
                () -> assertEquals(listaFilmes.get(posicao).getAno_de_lancamento(),filmesPadrao.get(posicao).getAno_de_lancamento()),
                () -> assertEquals(listaFilmes.get(posicao).getNome(),filmesPadrao.get(posicao).getNome()),
                () -> assertEquals(listaFilmes.get(posicao).getDiretor(),filmesPadrao.get(posicao).getDiretor()),
                () -> assertEquals(listaFilmes.get(posicao).getAtor(),filmesPadrao.get(posicao).getAtor())
        );
    }

    @Test
    @DisplayName("Teste buscar filmes")
    @Order(7)
    void buscarFilme() {

        Filme filme2 = new Filme(6,1994,"O Máskara","Chuck Russell","Jim Carrey");

        ArrayList<Filme> filmes = new ArrayList<>();
        filmes = f.carregaFilmes(filmes);

        Filme filme1 = f.buscarFilme(filmes,6);

        assertAll("Teste buscar Filmes",
                () -> assertEquals(filme1.getCodigo(),filme2.getCodigo()),
                () -> assertEquals(filme1.getAno_de_lancamento(),filme2.getAno_de_lancamento()),
                () -> assertEquals(filme1.getNome(),filme2.getNome()),
                () -> assertEquals(filme1.getDiretor(),filme2.getDiretor()),
                () -> assertEquals(filme1.getAtor(),filme2.getAtor())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    @DisplayName("Teste deleta filme")
    @Order(10)
    void deletaFilme(int posicao) {
        ArrayList<Filme> filmesPadrao = new ArrayList<>();

        listaFilmes = f.carregaFilmes(listaFilmes);
        f.deletaFilme(listaFilmes,2);

        //TESTES DE DEBUG
        //System.out.println(posicao);
        //System.out.println(listaFilmes.get(posicao).getNome());

        filmesPadrao.add(new Filme(1,1997,"Titanic","James Cameron","Leonardo DiCaprio"));
        //filmesPadrao.add(new Filme(2,2016,"Deadpool"," Tim Miller","Ryan Reynolds"));
        filmesPadrao.add(new Filme(3,2013,"Invocação do Mal","James Wan","Vera Farmiga"));
        filmesPadrao.add(new Filme(4,2013,"Truque de Mestre","Louis Leterrier","Mark Ruffalo"));
        filmesPadrao.add(new Filme(5,2017,"It: A Coisa","Andy Muschietti","Bill Skarsgård"));
        filmesPadrao.add(new Filme(6,1994,"O Máskara","Chuck Russell","Jim Carrey"));
        filmesPadrao.add(new Filme(7,2019,"It – Capítulo Dois","Andy Muschietti","Bill Skarsgård"));

        System.out.println(filmesPadrao.get(posicao).getNome());

        assertAll("Teste deleta Filmes",
                () -> assertEquals(listaFilmes.get(posicao).getCodigo(),filmesPadrao.get(posicao).getCodigo()),
                () -> assertEquals(listaFilmes.get(posicao).getAno_de_lancamento(),filmesPadrao.get(posicao).getAno_de_lancamento()),
                () -> assertEquals(listaFilmes.get(posicao).getNome(),filmesPadrao.get(posicao).getNome()),
                () -> assertEquals(listaFilmes.get(posicao).getDiretor(),filmesPadrao.get(posicao).getDiretor()),
                () -> assertEquals(listaFilmes.get(posicao).getAtor(),filmesPadrao.get(posicao).getAtor())
        );
    }

    @Test
    @DisplayName("Teste altera filme")
    @Order(9)
    void alteraFilme() {
        ArrayList<Filme> filmesPadrao = new ArrayList<>();
        Filme newFilme = new Filme(5,2021,"Teste Altera Filme","Marcos","Júnior");

        listaFilmes = f.carregaFilmes(listaFilmes);
        f.alteraFilme(listaFilmes,newFilme);

        assertAll("Teste altera Filmes",
                () -> assertEquals(listaFilmes.get(4).getCodigo(),newFilme.getCodigo()),
                () -> assertEquals(listaFilmes.get(4).getAno_de_lancamento(),newFilme.getAno_de_lancamento()),
                () -> assertEquals(listaFilmes.get(4).getNome(),newFilme.getNome()),
                () -> assertEquals(listaFilmes.get(4).getDiretor(),newFilme.getDiretor()),
                () -> assertEquals(listaFilmes.get(4).getAtor(),newFilme.getAtor())
        );

    }
}