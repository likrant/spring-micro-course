docker run -p 8181:8080 ^
--name spring-micro-course-keycloak ^
-e KEYCLOAK_ADMIN=admin ^
-e KEYCLOAK_ADMIN_PASSWORD=admin ^
-v .\keycloak_data:/opt/keycloak/data/h2:rw quay.io/keycloak/keycloak:23.0.0 start-dev
