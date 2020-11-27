package application;

import rest.PesquisaSatisfacaoResource;
import conexao.ConexaoJavaDb;
import dao.ContratadoDao;
import dao.ContratanteDao;
import dao.PesquisaSatisfacaoDao;
import javafx.application.Application;
import javax.security.auth.login.Configuration;
import rest.ContratadoResource;
import rest.ContratanteResource;

public class Program extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new Program().run(new String[] { "server" });
    }
    //#f9f9f9
    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/resources/html", "/site", "index.html"));
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        try {
            ConexaoJavaDb conexao;
            conexao = new ConexaoJavaDb();

            ContratadoDao lojasDao = new ContratadoDao(conexao);
            environment.jersey().register(new ContratadoResource(lojasDao));
            environment.jersey().setUrlPattern("/api/*");

            ContratanteDao carroDao = new ContratanteDao(conexao);
            environment.jersey().register(new ContratanteResource(carroDao));
            environment.jersey().setUrlPattern("/api/*");

            PesquisaSatisfacaoDao timesDao = new PesquisaSatisfacaoDao(conexao);
            environment.jersey().register(new PesquisaSatisfacaoResource(timesDao));
            environment.jersey().setUrlPattern("/api/*");

         

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}