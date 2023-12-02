REM Check if the container already exists
docker inspect spring-micro-course-keycloak > nul 2>&1
if %errorlevel% equ 0 (
    REM Container exists, start it
    docker start spring-micro-course-keycloak
) else (
    REM Container does not exist, run it
    docker run -p 8181:8080 ^
    --name spring-micro-course-keycloak ^
    -e KEYCLOAK_ADMIN=admin ^
    -e KEYCLOAK_ADMIN_PASSWORD=admin ^
    -v .\keycloak_data:/opt/keycloak/data/h2:rw quay.io/keycloak/keycloak:23.0.0 start-dev
)
