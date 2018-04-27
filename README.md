## checker
Scala command line checklist checker

### Run
```
$sbt
$run "arg"
```

### шаблон    
```
## Список в поездку
-> Страна: Строка: страна
  ${страна == США}
    $Переходник(американские)
    Америкаский флаг
  ${страна == Великобритания}
  	Флаг Великобритании
    Фунты, если есть
  ${страна == Канада}
  	Канадский флаг
    Фунты, если есть
  Количество дней: Число: дни
  	деньги = ${дни * суточная_норма($Страна) + 100}
# Одежда
  Носки, ${дни + 1} пар
  -> Погода, приблизительная температура: Число: градусы ${Цельсия|Фарингейта} 
  	$(Фарингейта)  
  	  градусы = ${(градусы-32) / 1.8}
    ${градусы < 0}
      Свитер
      Зимняя куртка
      Зимняя шапка
     ${градусы >= 0 && градусы < 15}
       Свитер
       Всесезонная куртка
     $
       Летняя куртка
# Документы
  Паспорт
```

### pattern for args
Country: *Idetifier*={USA, Great Britain, Canada}, Count of day: *num*, temperature: *num* Cels|Fahr

  
### example
**input:** 
run "country: USA, count of day: 5, temperature: 33 Fahr"

**output:**
```
checklist:
  - american flag
  - 600 USD
  - pasport
  clothes:
    - 6 pairs of socks
    - sweater
    - all-weather jacket
```    
    
**input:** 
run "country: Canada, count of day: 3, temperature: -3 Cels"
    
**output:**   
```
checklist:
  - Canadian flag
  - pasport
  - 340 canadian dollar
  clothes:
    - 4 pairs of socks
    - sweater
    - winter jacket
    - winter hat    
 ```   
