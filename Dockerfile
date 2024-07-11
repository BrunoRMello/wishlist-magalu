# Etapa 1: Construir a aplicação usando OpenJDK 20 e Maven
FROM maven:3.9.4-openjdk-20 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o restante dos arquivos do projeto
COPY src ./src

# Compile a aplicação
RUN mvn package -DskipTests

# Etapa 2: Executar a aplicação com OpenJDK 20
FROM openjdk:20-alpine

# Defina o diretório de trabalho
WORKDIR /opt/app

# Copie o JAR da etapa de construção
COPY --from=build /target/wishlist-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta em que a aplicação estará ouvindo
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
