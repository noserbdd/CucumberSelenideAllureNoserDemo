# Selenium Stack

Basierend auf einer Kundenanfrage wurde Selenide (Framework um Selenium) mittels einer kleinen Demo vorbereitet
Zum Einsatz kommt ausserdem 

## Komponenten des Selenium Stacks

Hauptbestandteile:
* Fachliches Test Design: [Cucumber mit Gherkin](https://cucumber.io/)
* Technisches Test Design: [Selenium Framework, Flavor - Selenide](https://selenide.org/)
* Test Ausführung: [Selenium Grid](https://jenkins.io/)
* Test Infrastruktur: [localhost :), Docker](https://www.docker.com/)
* Test Reports: [Allure](https://qameta.io/)

Weitere Tools:
* [Apache Maven](https://maven.apache.org/)
* [JUnit 4](https://junit.org/junit4/)
* [SLF4J](https://www.slf4j.org/)


## Application under Test
Als zusätzliche Anwendungsbeispiel wird die Google Suche verwendet.


## Testausführung

* Via Maven
* Via JUnit
**	auf localhost
** auf selenium Grid

Falls man gegen selenium Grid laufen lassen möchte, so können folgende Parameter in der 'Launch Configuration' angegeben werden:
-Dselenide.browser=chrome -Dselenide.remote=http://localhost:4444/wd/hub

## Selenium Grid docker-compose.yml
```
version: "3"
services:

  hub:
    image: selenium/hub
    ports:
      - "4444:4444"

    environment:
      GRID_MAX_SESSION: 16
      GRID_BROWSER_TIMEOUT: 3000
      GRID_TIMEOUT: 3000

  chrome:
    image: selenium/node-chrome-debug
    depends_on:
      - hub
    environment:
      HUB_PORT_4444_TCP_ADDR: hub
      HUB_PORT_4444_TCP_PORT: 4444
      NODE_MAX_SESSION: 4
      NODE_MAX_INSTANCES: 4
    volumes:
      - /dev/shm:/dev/shm
    links:
      - hub
      
    ports:
      - 4577
      - 5900
    expose: 
      - "5900"
```

## Selenium Grid laufen lassen und via VNC zugreifen

* `docker-compose up -d
* `docker-compose scale chrome=3

* `docker ps -a

* `vnc: 127.0.0.1:49155 (zum Beispiel)
* `password: secret

## Auf Selenium Grid zugreifen
* `http://localhost:4444/grid/console
