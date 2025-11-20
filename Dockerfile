# Usa imagem base do Maven com JDK 21 para construir a aplicação
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY pom.xml .

# Baixa as dependências do Maven para o projeto
RUN mvn dependency:go-offline

# Copia o código-fonte da aplicação para o contêiner
COPY src ./src

# Executa o comando Maven para construir a aplicação e empacotá-la
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o arquivo JAR construído do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8081 para acesso externo
EXPOSE 8081
ENTRYPOINT ["sh", "-c", "exec java -jar /app/app.jar"]