Автотест написан на языке программирования Java и выпонен в соответсвии с тестовым заданием:
1) Перейти на внешний ресурс: http://stackoverflow.com/
2) В строку поиска ввести значение «webdriver».
3) Проверить, что в каждом результате представлено слово WebDriver.
4) Войти в каждое обсуждения из выборки и убедиться, что перешли именно в эту тему (проверить заголовок обсуждения).
5) Перейти в раздел Tags
6) В строку поиска ввести значение – webdriver. Убедиться, что в результате присутствуют элементы содержащее слово webdriver.
7) Найти в результатах тэг по точному совпадению поискового запроса и кликнуть по нему, проверить, что после перехода отображаются обсуждения помеченные тэгом (уточнение имеется ввиду поисковый тег, а не HTML) webdriver.

Автотесты запускаются в браузере Google Chrome
Если при запуске тестов возникнет ошибка несоответствия версии driver и chrome, 
то в папку src\test\resources\driver положить подходящий к вашему браузеру chromedriver

Из-за большого количества страниц, на которых необходимо выполнить соответсвующие проверки, время выполнения автотеста длится приблизительно 3 часа 20 минут

Для формирования отчета по выполненному автотесту используется фреймворк Allure

Запуск автотеста производится через cmd.

Для запуска теста - команда:
mvn clean test

Для формирования отчёта Allure - команда:
mvn allure:serve
