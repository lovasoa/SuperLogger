# SuperLogger
Log system information using logstash, store the information on ElasticSearch, and visualize it using Kibana.

## Explanation in russian
### Архитектура
#### Агент
Агент реализован на языке java. Он состоит из двух файла:
 - [`SystemInfo.java`](superlogger/src/main/java/SystemInfo.java) Простой класс который умеет собирать нужные данные.
 - [`TCPServer.java`](superlogger/src/main/java/TCPServer.java) очен простой сервер. При соединении клиента, он начинает отправлять ему
 каждую секунду имя ПК, IP-адрес, загрузку ЦП и загрузку ОЗУ, в формате JSON.

#### Посредник
Посредник - logstash. Он запускается контайнере docker, и читает свою конфигурацию в файле [`tcp2elastic.conf`](logstash/pipeline/tcp2elastic.conf).

При запуске, он соединяется с агентом и со сервером. Потом, каждую секунду, он получает данные от агента, добавляет в них `timestamp` и другие информации, и отправляет их на сервер.

#### Сервер хранения и обработки данных
Сервер - ElasticSearch. Он храняет данные, создает идексы, и отвечает на запросы интерфайса через протокол HTTP на формате JSON.

#### Интерфайс
Интерфайс - Kibana. В нём мы построили несколко полезных визуализации наших данных.

## Screenshot
#### The dashboard
[![dashboard screenshot](images/kibana-screenshot.png)](https://bf58148f7062c023c525264bb67a1816.eu-west-1.aws.found.io/app/kibana#/dashboard/7241e5a0-26ca-11e7-9884-9d94c5a74ce7?_g=(refreshInterval%3A(display%3AOff%2Cpause%3A!f%2Cvalue%3A0)%2Ctime%3A(from%3Anow-1h%2Cmode%3Aquick%2Cto%3Anow)))

## Architecture
![architecture schema](images/architecture-schema.png)

## Class diagram
![class diagram](images/class-diagram.png)
