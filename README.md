# Air-Tickets
Программа, которая прочитает файл tickets.json (src/main/resources/tickets.json) и рассчитает: 
- среднее время полета между городами Владивосток и Тель-Авив
- 90-й процентиль времени полета между городами Владивосток и Тель-Авив

## Запуск и компиляция
###  Компиляция
```
mvn clean install assembly:single
```

### Запуск (Вар. 1)
```
java -jar biocad-json-parser.jar
```
