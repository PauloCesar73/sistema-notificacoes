Configurar banco application.properties:

spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

Configurar Token Firebase application.properties:

firebase.config.json=

Configurar banco Docker compose:
SPRING_DATASOURCE_URL:
SPRING_DATASOURCE_USERNAME:
SPRING_DATASOURCE_PASSWORD:

POSTGRES_DB:
POSTGRES_USER:
POSTGRES_PASSWORD:

Configurar Token Firebase:

firebase.config.json=

Ajustar Docker Compose prometheus para rota de sua api
targets: ['8080']

Gerar container prometheus
docker run -d --name=prometheus --network=monitoring -p 9090:9090 -v "seu caminho ex:\sistema-notificacoes\sistema-notificacoes\prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus"

Gerar container grafana
docker run -d --name=grafana --network=monitoring -p 3000:3000 grafana/grafana

Gerar imagem projeto
docker build -t notficationimage .

Iniciar aplicação
docker-compose up -d
