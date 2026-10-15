# LearnHub - платформа онлайн-курсов

Прикладной Java-сервис для управления каталогом курсов, студентами, рейтингами и траекториями обучения. Реализованы все семь бизнес-требований.

## Запуск

Через Maven:

В терминале:
```
mvn clean package
java -cp target/classes com.example.learnhub.Main
```

Или из IDE: запустить класс src/main/java/com/example/learnhub/Main.java

## Требования

Java 21+
Maven 3.8+

## Тесты

В терминале: mvn test

Или в IDE: запустить пакет src/test/java/com/example/learnhub

## Таблица "тема -> класс -> сложность"

| BR | Тема | Классы | Сложность |
|----|------|--------|-----------|
| BR1 | Стек (LIFO), связный список | LinkedList, Stack, NavigationHistoryService | O(1) у push/pop/peek |
| BR2 | Бинарное дерево поиска | BinarySearchTree, StudentRegistryService | O(log n) в среднем, O(n) в худшем |
| BR3 | Сортировки, бинарный поиск | Sorter, BinarySearch, RatingService | O(n log n) у сортировки, O(log n) у поиска |
| BR4 | Графы: BFS, DFS, циклы, Дейкстра | Graph, PrerequisiteService | O(V+E) у BFS/DFS, O((V+E) log V) у Дейкстры |
| BR5 | Рюкзак 0/1, выбор интервалов | Knapsack, IntervalScheduling, SemesterPlanningService | O(n·W) у ДП, O(n log n) у жадного |
| BR6 | Скользящее окно, два указателя | SlidingWindow, ClosestPairFinder, ActivityAnalyticsService | O(n) у окна, O(n log n) у поиска пары |
| BR7 | Рекурсия с мемоизацией | TrajectoryCounter, TrajectoryService | O(2^n) у наивной рекурсии, O(n) с мемоизацией |

## Сквозной сценарий

1. Регистрируем студентов в реестре (BR-2).
2. Строим рейтинг из того же реестра, а не из отдельных данных (BR-3).
3. Добавляем курсы и пререквизиты в граф (BR-4).
4. Составляем план семестра из курсов, доступных по BR-4 (BR-5).
5. Смотрим возможные траектории обучения (BR-7).
6. Ведём историю навигации по урокам и возвращаемся к предыдущему (BR-1).

## Основная структура проекта

    src/main/java/com/example/learnhub/
    -Main.java
    -model/
    --Student, Course, Lesson
    -structures/
    --LinkedList, Stack, BinarySearchTree, Graph
    -algorithms/
    --Sorter, BinarySearch, Knapsack, IntervalScheduling, SlidingWindow, ClosestPairFinder, TrajectoryCounter
    -services/
    --NavigationHistoryService, StudentRegistryService, RatingService, PrerequisiteService, SemesterPlanningService, ActivityAnalyticsService, TrajectoryService
    -utils/
    --DataGenerator, InputValidator, Timer