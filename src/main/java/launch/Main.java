package launch;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

    public static void main(String[] args) throws Exception {

        // Web-sovelluksen julkisten tiedostojen sijainti:
        String webappDirPath = new File("src/main/webapp/").getAbsolutePath();

        // Tomcat-palvelin, joka huolehtii HTTP-liikenteest�:
        Tomcat tomcat = new Tomcat();

        // Asetetaan Tomcatin HTTP-portti. Jos "PORT" l�ytyy ymp�rist�muuttujista,
        // k�ytet��n sit�. Muussa tapauksessa k�ytet��n porttia 8080:
        String httpPort = System.getenv().getOrDefault("PORT", "8080");
        tomcat.setPort(Integer.valueOf(httpPort));

        // Luodaan Connector-olio, joka kuuntelee asettamaamme porttia:
        tomcat.getConnector();

        // Lis�t��n oma sovelluksemme Tomcatiin palvelimen juureen:
        Context webApp = tomcat.addWebapp("/", webappDirPath);

        // HUOM! Jos haluat, ett� palvelin k�ynnist�� itsens� uudelleen muutettuasi
        // tiedostoja, poista kommentti seuraavalta rivilt�:
        webApp.setReloadable(true);

        // M��ritell��n sovelluksemme resurssien sijainnit:
        WebResourceRoot resources = new StandardRoot(webApp);
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
        webApp.setResources(resources);

        // Asetetaan UTF-8 -merkist� HTTP-pyynt�ihin ja -vastauksiin:
        webApp.setRequestCharacterEncoding("utf-8");
        webApp.setResponseCharacterEncoding("utf-8");

        // K�ynnistet��n palvelin ja odotetaan yhteyksi�:
        tomcat.start();
        tomcat.getServer().await();
    }


}
