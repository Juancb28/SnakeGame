# Snake Game

> Autores
    · Arroyo B. Juan
    · Benavides E. Jake
    · Casa C. Stalin
    · Casa T. Antonela
    · Chimarro B. Marlon
    · Yupanqui V. Niurka

Este proyecto es una implementación del clásico juego Snake utilizando Java. El proyecto se gestiona con Maven y utiliza JavaFX para la interfaz gráfica. Originalmente, se intentó utilizar el repositorio externo [Jamepad](https://github.com/williamahartman/Jamepad.git) para la integración del controlador, pero debido a problemas, se optó por utilizar otro método.

Recomendación, para utilizar un controlador externo, ya sea, un control de PS3, PS4 o PS5. Descargue de manera oficial la aplicación **DS4Windows** en la página oficial: https://ds4-windows.com/

## Requisitos

- Java 11 o superior
- Maven 3.6.0 o superior

## Configuración del Proyecto

Para configurar y ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio del proyecto:
    ```sh
    git clone https://github.com/tuusuario/snakegame.git
    cd snakegame
    ```

2. Construye el proyecto utilizando Maven:
    ```sh
    mvn clean install
    ```

3. Ejecuta la aplicación:
    ```sh
    mvn javafx:run
    ```

## Dependencias

El proyecto utiliza las siguientes dependencias:

- **SLF4J API**: Para la abstracción del logging.
- **Logback Classic**: Implementación concreta de logging.
- **JavaFX**: Para la interfaz gráfica del juego.
- **SQLite JDBC**: Para la conexión a la base de datos SQLite.
- **JUnit 5**: Para pruebas unitarias.
- **JInput**: Para la integración de controladores de entrada.

En el archivo `pom.xml`, estas dependencias están especificadas de la siguiente manera:

```xml
<dependencies>
    <!-- SLF4J API -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.13</version>
    </dependency>

    <!-- Logback Classic -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.5.6</version>
    </dependency>

    <!-- JavaFX Media -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-media</artifactId>
        <version>17.0.11</version>
    </dependency>

    <!-- SQLite JDBC -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.46.0.0</version>
    </dependency>

    <!-- JavaFX Controls -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>22-ea+11</version>
    </dependency>

    <!-- JavaFX FXML -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>22-ea+11</version>
    </dependency>

    <!-- JUnit Jupiter API -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>

    <!-- JUnit Jupiter Engine -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>

    <!-- JInput -->
    <dependency>
        <groupId>net.java.jinput</groupId>
        <artifactId>jinput</artifactId>
        <version>2.0.10</version>
    </dependency>
    <dependency>
        <groupId>net.java.jinput</groupId>
        <artifactId>jinput</artifactId>
        <version>2.0.10</version>
        <classifier>natives-all</classifier>
    </dependency>
</dependencies>
````

#### Problemas Conocidos
Inicialmente, se intentó integrar el controlador utilizando la biblioteca Jamepad. Sin embargo, surgieron problemas durante la implementación, lo que llevó a cambiar a otra solución para asegurar una experiencia de usuario fluida y sin errores.

