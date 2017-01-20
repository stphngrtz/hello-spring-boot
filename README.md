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

## TODO
- https://spring.io/guides/gs/rest-service/
- https://spring.io/guides/gs/rest-service-cors/
- https://spring.io/guides/gs/actuator-service/
- https://spring.io/guides/gs/testing-web/
- https://spring.io/guides/gs/consuming-rest/
- https://spring.io/guides/gs/rest-hateoas/
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
