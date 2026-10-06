# LearnHub - платформа онлайн-курсов

Прикладной Java-сервис для управления каталогом курсов, студентами,
рейтингами и траекториями обучения. Реализованы все семь бизнес-требований.

## Запуск

Через Maven:

В терминале:
mvn clean package
java -cp target/classes com.example.learnhub.Main

Или из IDE: запустить класс src/main/java/Main

## Требования

Java 17+
 Maven 3.8+

 ## Тесты

В терминале: mvn test

Или в IDE：запустить пакет src/test/com.example.learnhub

## Таблица "тема -> класс -> сложность"

BR1 Linked List, Stack -> CustomLinkedList, CustomStack, BR1Journal -> О(1)

BR2 Binary Search Tree -> CustomBST, BR2Registry -> O(log n)

BR3 Сортировки, бинарный поиск -> Sorting, Searching, BR3Rating -> O(n log n)

BR4 Графы: BFS, DFS, циклы, Дейкстра -> CustomGraph, BR4Prerequisites -> O(V+E) у BFS/DFS, O((V + E) log V) у Дейкстры

BR5 Рюкзак 0/1, выбор интервалов -> Knapsack, IntervalScheduling, BR5SemesterPlan -> O(n · W) у точного расчёта, O(n log n) у жадного

BR6 Скользящее окно, two pointers -> SlidingWindow, TwoSum, BR6Analytics -> O(n) у окна, O(n log n) у поиска пары

BR7 Рекурсия, мемоизация -> Trajectories, BR7Trajectories -> O(3^n) у наивной рекурсии, O(n) с мемоизацией

## Сквозной сценарий

1. Регистрируем студента (BR2) и начисляем баллы (BR3).
2. Добавляем курсы и пререквизиты (BR2, BR4).
3. Строим план семестра для студента (BR5).
4. Проверяем, какие курсы стали доступны (BR4).
5. Смотрим возможные траектории обучения (BR7).
6. Все изменения фиксируются в журнале и могут быть откачены (BR1).

## Основная структура проекта

src/main/java/com/example/learnhub/
                          Main.java
                          model/ Student, Course, Action
                          structures/ CustomLinkedList, CustomStack, CustomBST, CustomGraph
                          algorithms/ Sorting, Searching, Knapsack, IntervalScheduling, SlidingWindow, TwoSum, Trajectories
                          br/ BR1Journal - BR7Trajectories
                          util/ DataGenerator, Timer
