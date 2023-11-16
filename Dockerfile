# Dockerfile

# Usa la imagen oficial de OpenJDK 17 como base
FROM openjdk:11-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de tu proyecto al contenedor
COPY . /app

# Compila los archivos fuente
RUN javac -d out src/*.java

# Empaqueta los archivos compilados en un archivo JAR
RUN jar cfe myapp.jar Main -C out .

# Exponer el puerto si tu aplicación lo requiere
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "myapp.jar"]
