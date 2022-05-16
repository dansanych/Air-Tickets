# Air-Tickets
Программа, которая прочитает файл tickets.json (src/main/resources/tickets.json) и рассчитает: 
- среднее время полета между городами Владивосток и Тель-Авив
- 90-й процентиль времени полета между городами Владивосток и Тель-Авив

## Запуск и компиляция
###  Компиляция
```
mvn clean install assembly:single
```

### Запуск Вариант 1 (default)
```
java -jar Air-Tickets.jar
```
Использует стандартные значения из условия

### Запуск Вариант 2 (custom)
```
java -jar Air-Tickets.jar departureCity arrivalCity percentile
```
Использует заданные значения, где
Город 1: departureCity;
Город 2: arrivalCity;
Желаемый процентиль: percentile; 
