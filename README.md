# Phone shop
Онлайн магазин покупки телефонов

## Build
Создаём базу данных PostgreSQL
```
CREATE DATABASE phone_shop;
```

Запустить скрипт [create_table.sql](sql%2Fcreate_table.sql) и [insert.sql](sql%2Finsert.sql)

В [application.properties](src%2Fmain%2Fresources%2Fapplication.properties) задаём 
параметр `server.port`, можно оставить по стандарту.
И также задаём параметры базы данных `spring.datasource.username` и 
`spring.datasource.password`. И порт, если он не стандартный, 
`spring.datasource.url`.

Собираем jar
```
.\gradlew bootJar
```
Запускаем jar:
```
java -jar .\build\libs\PhoneShop-0.0.1-SNAPSHOT.jar
```
И переходим по ссылке http://localhost:8081/

## База данных
![erd_db.png](sql%2Ferd_db.png)

## Тестирование
Тесты выполняются НЕ на отдельной базе данных, поэтому рекомендуется перед тестами выполнить 
скрипты [create_table.sql](sql%2Fcreate_table.sql) и [insert.sql](sql%2Finsert.sql)