Мое решение основывается на алгоритме Левенштейна(Сложность алгоритма O(n*m), где n и m - количество запросов пользователя 
и количество возможных поисковых ответов соответственно), то есть я сначала считываю информацию из двух файлов,

затем я для каждого запроса перебираю все варианты ответов на запрос и при помощи данного алгоритма, 

дополнительно используя приоритетную очередь, для того чтобы строки ответов располагались в порядке реливантности,

а сам порядок реливантности достигается путем приоритизации ответов на запрос по наименьшему отличию от запроса

(алгоритм Левенштейна), но конкретнее его суть заключается в том, что мы считаем количество операций, заполняя двумерный массив,

и в итоге получаем в низней крайней справа клетке двумерного массива минимальное количество опеараций, чтобы получить

из ответа на запрос сам запрос. Таким образом выводятся первые 5 ответов(точнее их GUID в Json в файле) по самому высокому приоритету.