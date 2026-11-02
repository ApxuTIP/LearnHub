# LearnHub - платформа онлайн-курсов

Прикладной Java-сервис для управления каталогом курсов, студентами, рейтингами и траекториями обучения. Реализованы все семь бизнес-требований (BR-1…BR-7).

## Запуск

Через Maven:

```bash
mvn clean package
java -cp target/classes com.example.learnhub.Main
```

Или из IDE: запустить класс `src/main/java/com/example/learnhub/Main.java`.

## Требования

Java 21+
Maven 3.8+

## Тесты

В терминале: `mvn test`

Или в IDE: запустить пакет `src/test/java/com/example/learnhub`.

## Таблица "тема -> класс -> сложность"

| BR | Тема | Классы | Сложность |
|----|------|--------|-----------|
| BR1 | Стек (LIFO), связный список | `CustomLinkedList`, `CustomStack`, `NavigationHistoryService` | O(1) для push/pop/peek |
| BR2 | Бинарное дерево поиска | `BinarySearchTree`, `StudentRegistryService` | O(log n) в среднем, O(n) в худшем |
| BR3 | Сортировки, бинарный поиск | `Sorter`, `BinarySearch`, `RatingService` | O(n log n) — сортировка, O(log n) — место и диапазон |
| BR4 | Графы: BFS, DFS, циклы, Дейкстра | `Graph`, `PrerequisiteService` | O(V+E) у BFS/DFS, O((V+E) log V) у Дейкстры |
| BR5 | Рюкзак 0/1, выбор интервалов | `Knapsack`, `IntervalScheduling`, `SemesterPlanningService` | O(n·W) у ДП, O(n log n) у жадного |
| BR6 | Скользящее окно, два указателя | `SlidingWindow`, `ClosestPairFinder`, `ActivityAnalyticsService` | O(n) у окна, O(n log n) у поиска пары |
| BR7 | Рекурсия с мемоизацией | `TrajectoryCounter`, `TrajectoryService` | O(2^n) у наивной рекурсии, O(n) с мемоизацией |

## Сквозной сценарий

Модули объединены в единую цепочку: результат одного используется в другом.

1. **BR-2.** Регистрируем студентов в реестре (BST).
2. **BR-3.** Строим рейтинг из того же реестра, а не из отдельных данных.
3. **BR-4.** Добавляем курсы и пререквизиты в граф.
4. **BR-5.** Получаем список курсов, доступных после курса 1 (через `reachableFrom` из BR-4), и составляем из них план семестра.
5. **BR-6.** Анализируем ежедневную активность студента.
6. **BR-7.** Считаем число траекторий прохождения программы.
7. **BR-1.** Ведём историю навигации по урокам и возвращаемся к предыдущему.

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
