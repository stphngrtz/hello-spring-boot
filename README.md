# hello-spring-boot
Spring ist für mich quasi Neuland. Ja, Spring, nicht nur Spring Boot. Es gab vor Jahren mal einen Moment, bei dem dem Spring auf mich einen *"iiih, nee das möchte nicht"* Effekt ausgelöst hat. Genauer kann ich mich nicht erinnern, deshalb will und kann ich das nicht näher erläutern - Fakt ist aber: Spring ist für mich Neuland. Von Spring Boot hört man aber viel Gutes, daher ist es jetzt an der Zeit, mit diesem Projekt in die Spring-Welt einzutauchen!

Wie auch schon bei den letzten *hello-* Repositories will ich die Readme dafür nutzen, ein paar Sachen für mich selbst zu dokumentieren. Wenn Dritte davon profitieren: Bitteschön!

## Starters
Starters sind interessant. Man definiert einen solchen als Maven Parent bzw. Dependency und es werden, laut Spring Dokumentation, *"useful"* Defaults sowie *"blessed"* Dependencies übernommen.

```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.4.3.RELEASE</version>
</parent>

...

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

Wie man sieht, `<version>` entfällt, dank sei den *"blessed"* Dependencies ;)

**Offizielle** Starter fangen übrigens immer mit `spring-boot-starter-*` an. Third Party Starter sollen, laut Dokumentation, einen Prefix bekommen, zB. `stphngrtz-spring-boot-starter-*`.

## Hello World
Für ein Hello-World-Beispiel reicht eine `pom.xml` nicht aus, wir brauchen noch Code!

```
@RestController
@EnableAutoConfiguration
public class App {

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

In `spring-boot-starter-parent` ist ein sog. `run` Goal definiert, mit dem sich die Anwendung von der Konsole aus starten lässt.

```
> mvn spring-boot:run
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building hello-spring-boot 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------

...

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.4.3.RELEASE)

...

2017-01-19 15:03:55.408  INFO 3048 --- [main] de.stphngrtz.App: Started App in 1.879 seconds (JVM running for 132.574)
```

## Executable JAR
Auch hier kommt das das Spring Ecosystem zu Hilfe. Eine Mini-Build-Konfiguration in die `pom.xml` hinzufügen und schon erstellt `mvn package` ein executable JAR.

```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

Anschließend lässt sich das Hello-World-Beispiel via `java -jar target/hello-spring-boot-1.0-SNAPSHOT.jar` starten.

## Java 8 bzw. "Wie überschreibt man Defaults aus dem Starter?"
Der Starter des 1.4.3 Release setzt die Java Version auf 1.6. Ich möchte die 7er und 8er Features aber nicht missen, deshalb muss das dringend geändert werden.

```
<properties>
    <java.version>1.8</java.version>
</properties>
```

Anschließend lassen sich auch Java 8 Features nutzen. Zum Beispiel, um einen absoluten Blödsinn zu machen ;)

```
@RequestMapping("/")
String home() {
    return ((Function<String, String>) x -> "Hello " + x).apply("World");
}
```

## Spring Guides
Das Getting-Started endet hier bereits, allerdings scheint man bei Spring erkannt zu haben, wie wertvoll HowTo-Guides sind. Neben der Dokumentation gibt es einen separaten Bereich für [Guides](https://spring.io/guides/), die auf den ersten Blick sehr interessant aussehen.

## Building an Application with Spring Boot
https://spring.io/guides/gs/spring-boot/

Das ist der erste Guide, den ich mir genauer angesehen habe. Grundsätzlich geht er nicht viel weiter, als das Getting-Started Beispiel. Zwei Punkte sind allerdings neu - und nicht weniger spannend!

- Tests! Die Helferlein von Spring erleichtern das Schreiben von Tests ungemein, finde ich. Details hierzu kommen in einem separaten Guide.
  - [Unit-Test](https://github.com/stphngrtz/hello-spring-boot/tree/master/src/test/java/de/stphngrtz/controllers/GreetingsControllerTest.java) mit provisioniertem Application Context
  - [Integrationstest](https://github.com/stphngrtz/hello-spring-boot/tree/master/src/test/java/de/stphngrtz/controllers/GreetingsControllerIT.java) für die vollständige Anwendung
- [Actuator](http://docs.spring.io/spring-boot/docs/1.4.3.RELEASE/reference/htmlsingle/#production-ready)! Unter dem Titel *"production-ready features"* kommt über den Actuator ein sehr interessantes Feature-Set zum Monitoring bzw. Verwalten in die Anwendung. Auch hierzu gibt es einen separaten Guide.

## Building a RESTful Web Service
https://spring.io/guides/gs/rest-service/

Der RESTful Web Service Guide bringt einen nicht sonderlich viel weiter als der Getting Started Guide. Neu sind lediglich die Parameter im `@RequestMapping` sowie `@RequestParam`.

```
@RequestMapping(value = "/greeting", method = RequestMethod.GET)
public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
}
```

## Enabling Cross Origin Requests for a RESTful Web Service
https://spring.io/guides/gs/rest-service-cors/

`@CrossOrigin` ist alles was man braucht um CORS zu aktivieren. Standardmäßig sind alle *"Origins"*, alle Header und die in `@RequestMapping` definierte HTTP Method erlaubt. Über Parameter der Annotation lässt sich das konfigurieren.

```
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = "/greeting", method = RequestMethod.GET)
public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
}
```

Will man CORS nicht für jeden Request einzeln aktivieren, dann geht das wie folgt.

```
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("http://localhost:9000");
        }
    };
}
```

## Testing the Web Layer
https://spring.io/guides/gs/testing-web/

Der Inhalt dieses Guides ist fast vollständig durch den Getting Started Guide abgedeckt. Siehe  [GreetingsControllerTest.java](src/test/java/de/stphngrtz/controllers/GreetingsControllerTest.java) und [GreetingsControllerIT.java](src/test/java/de/stphngrtz/controllers/GreetingsControllerIT.java).

Neu ist die `@WebMvcTest` Annotation. Wird diese Annotation anstatt `@AutoConfigureMockMvc` genutzt, werden Klassen mit `@Component`, `@Service` oder `@Repository` nicht geladen. Ob das so sinnvoll ist weiß ich (noch) nicht.

## Building a Hypermedia-Driven RESTful Web Service
https://spring.io/guides/gs/rest-hateoas/

Spring hat einen Starter für HATEOAS an Bord.

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```

Die wesentlichen Inhalte der Guides sind relativ schnell erklärt. Zuerst müssen Entities von einer Basisklasse ableiten.

```
public class GreetingWithHATEOAS extends ResourceSupport {
    ...
}
```

Anschließend können, mit Unterstützung diverser statischer Hilfsmethoden, Links auf die Entity hinzugefügt werden. Im Guide wird das mit einer Referenz auf die Entity selbst dargestellt.

```
public HttpEntity<GreetingWithHATEOAS> greetingWithHATEOAS(@RequestParam String name) {
    GreetingWithHATEOAS greeting = new GreetingWithHATEOAS(String.format("Hello, %s!", name));
    greeting.add(linkTo(methodOn(GreetingsController.class).greetingWithHATEOAS(name)).withSelfRel());
    ...
}
```

## Building a RESTful Web Service with Spring Boot Actuator
https://spring.io/guides/gs/actuator-service/

Der Guide geht leider so gut wie gar nicht auf Actuator ein. Es wird lediglich erwähnt, wie man den Actuator ins Projekt einbindet und wie man den Businesscode und Actuator-Funktionalität auf unterschiedlichen Ports laufen lassen kann. Hierzu muss in der `application.properties` folgendes Eintragen:

```
server.port: 9000
management.port: 9001
```

Wie man eine solche Port-Konfiguration dynamisch in Tests ausliest wird auch beschrieben. Siehe dafür [GreetingsControllerIT](src/test/java/de/stphngrtz/controllers/GreetingsControllerIT.java).

Details zu Actuator findet man in der [Dokumentation](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready). Diese beginnt mit einer Liste der Endpoints, welche einen guten Eindruck davon vermitteln, was der Actuator alles an Features mitbringt. `/health` kennen wir aus den Guides schon, `/metrics` habe ich im Folgenden noch grob angesehen.

In den Metrics werden automatisch diverse Systemmetriken ausgegeben. Dabei handelt es sich anscheinend immer um den dann aktuellen Stand. Eigene Metriken lassen sich sehr einfach hinzufügen.

```
@Autowired
private CounterService counterService;

@RequestMapping(value = "/greeting", method = RequestMethod.GET)
public Greeting greeting(@RequestParam String name) {
    counterService.increment("greeting");
    ...
}
```

## Consuming a RESTful Web Service
https://spring.io/guides/gs/consuming-rest/

Aus Java einen RESTful Web Service aufzurufen ist unnötig kompliziert. Normalerweise. Glücklicherweise gibt es die ein oder andere Library, die einem das Leben erleichtert. Mein bisheriger Favorit war [Feign](https://github.com/OpenFeign/feign). Spring bringt mit `RestTemplate` auch sowas mit.

```
Quote quote = new RestTemplate().getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
```

Das war es schon. Schön einfach. `POST`, `PUT`, etc. sieht ähnlich aus. Will man das Template einmalig konfigurieren, bietet es sich an, den `RestTemplateBuilder` in Kombination mit `@Bean` zu nutzen. Anschließend steht `RestTemplate` für Injection zur Verfügung.

```
@Bean
public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
}
```

## TODO
- https://spring.io/guides/gs/centralized-configuration/
- https://spring.io/guides/gs/scheduling-tasks/
- https://spring.io/guides/gs/securing-web/
- https://spring.io/guides/gs/routing-and-filtering/
- https://spring.io/guides/gs/accessing-mongodb-data-rest/
- https://spring.io/guides/gs/spring-boot-docker/
- https://spring.io/guides/gs/client-side-load-balancing/
- https://spring.io/guides/gs/service-registration-and-discovery/
- https://spring.io/guides/gs/circuit-breaker/
- https://spring.io/guides/tutorials/bookmarks/
