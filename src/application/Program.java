package application;

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
            conexao = new ConexaoJavaDb("app", "app", "localhost", 1527, "Projeto");


            LojasDao lojasDao = new LojasDao(conexao);
            environment.jersey().register(new LojaResource(lojasDao));
            environment.jersey().setUrlPattern("/api/*");

            CarrosDao carroDao = new CarrosDao(conexao);
            environment.jersey().register(new CarroResource(carroDao));
            environment.jersey().setUrlPattern("/api/*");

            TimeDao timesDao = new TimeDao(conexao);
            environment.jersey().register(new TimeResource(timesDao));
            environment.jersey().setUrlPattern("/api/*");

            CampeonatoDao campeonatosDao = new CampeonatoDao(conexao);
            environment.jersey().register(new CampeonatoResource(campeonatosDao));
            environment.jersey().setUrlPattern("/api/*");

            OrganizacaoDao organizacaoDao = new OrganizacaoDao(conexao);
            environment.jersey().register(new OrganizacaoResource(organizacaoDao));
            environment.jersey().setUrlPattern("/api/*");

            PaisDao paisDao = new PaisDao(conexao);
            environment.jersey().register(new PaisResource(paisDao));
            environment.jersey().setUrlPattern("/api/*");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}