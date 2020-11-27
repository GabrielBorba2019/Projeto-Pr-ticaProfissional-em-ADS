package application;

import conexao.ConexaoJavaDb;
import dao.ContratadoDao;
import dao.ContratanteDao;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;
import rest.ContratadoResource;
import rest.ContratanteResource;



public class Program extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new Program().run(new String[] { "server" });
    }
    //#f9f9f9
    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/resources/html", "/home", "home.html"));
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

           

         

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}