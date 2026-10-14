# LearnHub - платформа онлайн-курсов

Прикладной Java-сервис для управления каталогом курсов, студентами, рейтингами и траекториями обучения. Реализованы все семь бизнес-требований.

## Запуск

Через Maven:

В терминале: mvn clean package
java -cp target/classes com.example.learnhub.Main

Или из IDE: запустить класс src/main/java/com/example/learnhub/Main.java

## Требования

Java 21+
Maven 3.8+

## Тесты

В терминале: mvn test

Или в IDE: запустить пакет src/test/java/com/example/learnhub

## Таблица "тема -> класс -> сложность"
```
| BR | Тема | Классы | Сложность |
| BR1 | Linked List, Stack | CustomLinkedList, CustomStack, BR1Journal | O(1) |
| BR2 | Binary Search Tree | CustomBST, BR2Registry | O(log n) в среднем, O(n) в худшем |
| BR3 | Сортировки, бинарный поиск | Sorting, Searching, BR3Rating | O(n log n) у сортировки, O(log n) у поиска |
| BR4 | Графы: BFS, DFS, циклы, Дейкстра | CustomGraph, BR4Prerequisites | O(V+E) у BFS/DFS, O((V+E) log V) у Дейкстры |
| BR5 | Рюкзак 0/1, выбор интервалов | Knapsack, IntervalScheduling, BR5SemesterPlan | O(n·W) у точного расчёта, O(n log n) у жадного |
| BR6 | Скользящее окно, two pointers | SlidingWindow, TwoSum, BR6Analytics | O(n) у окна, O(n log n) у поиска пары |
| BR7 | Рекурсия, мемоизация | Trajectories, BR7Trajectories | O(2^n) у наивной рекурсии, O(n) с мемоизацией |
```

## Сквозной сценарий

1. Регистрируем студентов в реестре (BR2).
2. Строим рейтинг из того же реестра, а не из отдельных данных (BR3).
3. Добавляем курсы и пререквизиты в граф (BR4).
4. Составляем план семестра из курсов, доступных по BR4 (BR5).
5. Смотрим возможные траектории обучения (BR7).
6. Ведём историю навигации по урокам и возвращаемся к предыдущему (BR1).

## Основная структура проекта

    src/main/java/com/example/learnhub/
    -Main.java
    -model/ 
    --Student, Course, Lesson
    -structures/ 
    --CustomLinkedList, CustomStack, CustomBST, CustomGraph
    -algorithms/ 
    --Sorting, Searching, Knapsack, IntervalScheduling, SlidingWindow, TwoSum, Trajectories
    -br/ 
    --BR1Journal - BR7Trajectories
    -utils/
    --DataGenerator, InputValidator, Timer