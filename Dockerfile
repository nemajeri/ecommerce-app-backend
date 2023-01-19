FROM openjdk:17-oracle
LABEL maintainer = "nemanja"
LABEL version="0.0.1-SNAPSHOT"
LABEL description="Backend for my Ecommerce Application"
COPY target/ecommerce-app-0.0.1-SNAPSHOT.jar ecommerce-backend-docker.jar
ENTRYPOINT ["java", "-jar", "ecommerce-backend-docker.jar"]
EXPOSE 8080
CMD ["java", "-jar", "ecommerce-backend-docker.jar", "--spring.profiles.active=prod"]
HEALTHCHECK --interval=10s --timeout=3s --start-period=5s --retries=3 CMD curl --fail http://localhost:8080/health || exit 1