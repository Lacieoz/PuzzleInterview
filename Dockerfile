# syntax=docker/dockerfile:1

# First stage: complete build environment
FROM maven:3.8-eclipse-temurin-18 AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn --no-transfer-progress clean package

# Second stage: minimal runtime environment
FROM openjdk:18-jdk-oracle

# copy jar and libs from the first stage
COPY --from=builder target ./
