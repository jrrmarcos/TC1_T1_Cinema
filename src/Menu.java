import java.util.Scanner;

public class Menu {

    public int menuPrincipal(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "1. Submenu de Filmes" + "\n" +
                "2. Submenu de Salas" + "\n" +
                "3. Sair");
        return scanner.nextInt();
    }

    public int submenuFilmes(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "1. Listar Todos os Filmes" + "\n" +
                "2. Buscar Filme" + "\n" +
                "3. Incluir Novo" + "\n" +
                "4. Alterar Filme" + "\n" +
                "5. Excluir Filme" + "\n" +
                "6. Voltar ao Menu principal");
        return scanner.nextInt();
    }

    public int submenuSalas(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "1. Listar Todas as Salas" + "\n" +
                "2. Buscar Sala" + "\n" +
                "3. Incluir Nova" + "\n" +
                "4. Alterar Sala" + "\n" +
                "5. Excluir Sala" + "\n" +
                "6. Voltar ao Menu principal");
        return scanner.nextInt();
    }
}
