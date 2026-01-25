FROM openjdk:17-ea-17-jdk-slim

ENV LANG     C.UTF-8
ENV LC_ALL   C.UTF-8
ENV LC_CTYPE C.UTF-8

COPY jcp-2.0.41789.zip /distrib/
WORKDIR /distrib

RUN echo "deb http://security.debian.org/debian-security bullseye-security main contrib non-free" > /etc/apt/sources.list
RUN apt-get update
RUN apt-get install unzip

RUN unzip -q jcp-2.0.41789.zip
WORKDIR jcp-2.0.41789

ARG JCP_LICENSE
ARG JCP_COMPANY_NAME

#RUN java -cp .:*: ru.CryptoPro.Install.VariantTwo -force -install -jcp -jcryptop -JCPxml -seria $JCP_LICENSE 

RUN mkdir -p /opt/jcp/lib/
RUN cp -r * /opt/jcp/lib/
ENV CLASSPATH="/opt/jcp/lib/*"
COPY java.security /opt/cryptopro/java.security
ENV JAVA_TOOL_OPTIONS="-Djava.security.properties=/opt/cryptopro/java.security"

RUN java ru.CryptoPro.JCP.tools.License -serial $JCP_LICENSE -store

ADD . /app/
WORKDIR /app/

RUN ls -l

RUN bash gradlew -Dorg.gradle.daemon=false -b build.gradle clean build -x test --refresh-dependencies

RUN cp starterapp/build/libs/dmdk-node.jar /app/starterapp.jar

ENTRYPOINT java -Djdk.http.auth.tunneling.disabledSchemes="" -jar /app/starterapp.jar
