# Fase de construcción: utiliza una imagen de OpenJDK con herramientas de compilación
FROM openjdk:8-jdk-alpine as builder

WORKDIR /usr/app

# Copia el código fuente
COPY src/ ./src/

# Compila la aplicación (ajusta según la estructura de tu proyecto)
RUN javac -d out src/*.java

# Fase de ejecución: utiliza una imagen ligera de OpenJDK
FROM openjdk:8-jre-alpine

WORKDIR /usr/app

# Copia solo los archivos necesarios desde la fase de construcción
COPY --from=builder /usr/app/out /usr/app

# Copia la biblioteca MySQL Connector
COPY lib/mysql-connector-java-8.1.0.jar /usr/app/lib/

# Comando para ejecutar la aplicación
CMD ["java", "-cp", ".", "-Djava.library.path=/usr/app/lib", "Main"]
