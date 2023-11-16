# Dockerfile

# Usa la imagen oficial de OpenJDK 17 como base
FROM openjdk:11-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de tu proyecto al contenedor
COPY . /app

# Exponer el puerto si tu aplicación lo requiere
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "your-application.jar"]

