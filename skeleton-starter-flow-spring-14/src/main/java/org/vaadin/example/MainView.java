package org.vaadin.example;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Period;
import java.time.LocalDate;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    private static final String api = "http://localhost:8888/users/%s";
    VerticalLayout option1Cont;
    VerticalLayout option2Cont;
    HttpRequest request;
    HttpClient client = HttpClient.newBuilder().build();
    HttpResponse<String> response;


    private String getZona() {
        try {
            String resource = "http://localhost:8888/users";
            //System.out.println(resource);
            request = HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //System.out.println(response.body());
        } catch (URISyntaxException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }
    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        String response = getZona();
        Usuario[] zonas = gson.fromJson(response, Usuario[].class);

        option1Cont = new VerticalLayout();
        TextField nombre = new TextField("Nombre");
        TextField categoria = new TextField("Categoría");
        TextField precio = new TextField("Precio");
        TextField ean13 = new TextField("EAN13");

        Button submit = new Button("Submit");

        option1Cont.add(nombre, categoria, precio, ean13, submit);
        submit.addClickListener(event -> {
            String nombreValue = nombre.getValue();
            String categoriaValue = categoria.getValue();
            String precioValue = precio.getValue();
            String ean13Value = ean13.getValue();
            // Do something with the values
            // for example, print them
            System.out.println("Nombre: " + nombreValue);
            System.out.println("Categoría: " + categoriaValue);
            System.out.println("Precio: " + precioValue);
            System.out.println("EAN13: " + ean13Value);
        });







        option1Cont.add("hola");


        option2Cont = new VerticalLayout();
        int i = 0;
        String fechatemp;
        LocalDate today = LocalDate.now();
        for (i=0; i< zonas.length;i++){
            option2Cont.add(zonas[i].getTweet());
            option2Cont.add(new Html("<br>"));
            option2Cont.add("--"+zonas[i].getUsuario());
            fechatemp =zonas[i].getFecha();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(fechatemp, formatter);
            Period period = Period.between(today, date);
            int y = period.getYears();
            int m = period.getMonths();
            int d = period.getDays();
            if (y!=0){
                option2Cont.add(", "+ y+" years");


        } else if (m !=0) {

            option2Cont.add(", "+ m +" months");

            } else if (d!=0) {
                option2Cont.add(", "+ d +" days");
            }
            option2Cont.add(" ago");
            option2Cont.add(new Html("<br>"));}



        option2Cont.setVisible(false);

        Tab option1 = new Tab("General");
        Tab option2 = new Tab("Mayores de 59 años");


        Tabs tabs = new Tabs(option1, option2);
        tabs.addSelectedChangeListener(event -> {
                    this.hideContainers();
                    Tab selectedTab = event.getSelectedTab();
                    if (selectedTab == option1){
                        option1Cont.setVisible(true);
                    }
                    if (selectedTab == option2){
                        option2Cont.setVisible(true);
                    }
                }
                //           setContent(event.getSelectedTab())
        );
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(false);
        //setContent(tabs.getSelectedTab());

        add(tabs, content,option1Cont,option2Cont );


    }
    private void hideContainers(){
        option1Cont.setVisible(false);
        option2Cont.setVisible(false);

    }

}

        // Use TextField for standard text input





