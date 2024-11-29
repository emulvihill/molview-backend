FROM eclipse-temurin:21
LABEL authors="eric_mulvihill"

# Default port for the graphql apis and static web content
EXPOSE 5000

# Set environment variables
ENV APP_HOME=/opt/app
ENV APP_ENV=production

RUN mkdir $APP_HOME
COPY molview-backend-0.0.1-SNAPSHOT.jar /opt/app/molview-backend.jar
CMD ["java", "-jar", "/opt/app/molview-backend.jar"]
