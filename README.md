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

## Spring Guides
Das Getting-Started endet hier bereits, allerdings scheint man bei Spring erkannt zu haben, wie wertvoll HowTo-Guides sind. Neben der Dokumentation gibt es einen separaten Bereich für [Guides](https://spring.io/guides/), die auf den ersten Blick sehr interessant aussehen.

## ...
