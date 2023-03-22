# Автоматизация тестирования <a href="https://reqres.in" target="_blank"><img src="readme/images/reqres.png" width="100" height="50"></a>

## :scroll: Содержание
- [Технологии и инструменты](#pushpin-технологии-и-инструменты)
- [Запуск тестов](#pushpin-запуск-тестов)
- [Конфигурация тестов](#pushpin-конфигурация-тестов)
- [Описание параметров для сборки](#pushpin-описание-параметров-для-сборки)
- [Задача в Jenkins](#pushpin-задача-в-jenkins)
- [Отчёт Allure](#pushpin-отчёт-allure)
- [Отчёт Telegram](#pushpin-отчёт-telegram)

## :pushpin: Технологии и инструменты
<p align="center">
<a href="https://rest-assured.io/"><img src="readme/images/restassured.png" width="50" height="50" title="IntelliJ Idea" alt="IDEA"/></a>
<a href="https://www.jetbrains.com/idea/"><img src="readme/images/IntelliJ_IDEA_Icon.svg" width="50" height="50" title="IntelliJ Idea" alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="readme/images/java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://gradle.org/"><img src="readme/images/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://github.com/"><img src="readme/images/Github.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="readme/images/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="readme/images/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<img src="readme/images/Telegram.svg" width="50" height="50"  alt="Telegram"/>
</p>

## :pushpin: Запуск тестов
### Локально
<code>gradle clean test -Denv=local</code>

### Удалённо
<code>clean test -Denv=remote</code>

## :pushpin: Конфигурация тестов
### local.properties
- <code>baseUrl</code> - тестируемый ресурс
- <code>basePath</code> - общий путь к API методам

### remote.properties
- <code>baseUrl</code> - тестируемый ресурс
- <code>basePath</code> - общий путь к API методам

## :pushpin: Описание параметров для сборки
- <code>ENV</code> окружение, в котором запускаютя тесты, по умолчанию remote

## :pushpin: Задача в Jenkins
<a href="https://jenkins.autotests.cloud/job/C17-dmikhaylov_ru-restassured19/"><img src="readme/screenshots/jenkins.png" alt="Задача в jenkins"></a>

## :pushpin: Отчёт Allure
<img src="readme/screenshots/allure_overview.png" alt="Allure_overview"/>
<img src="readme/screenshots/allure_suites.png" alt="Allure_suites"/>
<img src="readme/screenshots/allure_graphs.png" alt="Allure_graphs"/>

## :pushpin: Отчёт Telegram
<img src="readme/screenshots/telegram.png" alt="telegram"/>

[Вернуться к содержанию](#scroll-содержание)