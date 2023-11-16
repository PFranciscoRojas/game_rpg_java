# Usa la imagen oficial de OpenJDK 17 como base
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de tu proyecto al contenedor
COPY . /app

# Copia el conector MySQL al directorio de la aplicación
COPY mysql-connector-java.jar /app

# Compila los archivos fuente
RUN javac -d out src/*.java

# Empaqueta los archivos compilados en un archivo JAR
RUN jar cfe myapp.jar Main -C out .

# Exponer el puerto si tu aplicación lo requiere
EXPOSE 8080

# Command to run the LibGDX application
CMD ["java", "-cp", "myapp.jar:mysql-connector-java.jar", "Main"]
