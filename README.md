# spring_boot_app_rulers_and_planets

Тестовое задание
 
3141 год. 
Вселенная исследована и поделена. 
Верховный правитель назначает Повелителей Планет, 
общее количество которых исчисляется миллионами.
Опытные Повелители могут одновременно управлять 
несколькими Планетами. Никакой демократии, поэтому 
одной планетой может править только один Повелитель.
Все это безобразие требует системы учета и надзора.
 
Задание:
Разработать Spring Boot приложение на Java. [DONE] 

Приложение должно иметь API и работать с реляционной БД. [DONE] 

Для простоты отладки это может быть in-memory БД, 
например HSQLDB или иная.
 
Базовые характеристики сущностей.
Повелитель: Имя и Возраст
Планета: Название
Один Повелитель может управлять несколькими Планетами, но одной Планетой может править только один Повелитель.
 
Необходимо разработать структуру таблиц для хранения Повелителей и Планет и связь между ними. [DONE]
 
Поддержать методы API:
- Добавить нового Повелителя [DONE]
- Добавить новую Планету [DONE]
- Назначить Повелителя управлять Планетой [DONE]
- Уничтожить Планету [DONE]
- Найти всех Повелителей бездельников, 
которые прохлаждаются и не управляют никакими Планетами [DONE]
- Отобразить ТОП 10 самых молодых Повелителей [DONE]
 
Написать тесты для этого функционала [DONE]
 
Код расположить в GitHub. [DONE]
 
Дополнительно (будет большим плюсом, но не обязательно): 
- Создать примитивный web интерфейс, в котором будут работать 
все методы API (красота, дизайн, usability оцениваться НЕ будут). [DONE]
 
- Написать тест на Selenium, который будет 
проверять работу интерфейса. [DONE]
