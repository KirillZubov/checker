# checker
Scala command line checklist checker

## Run
```
$sbt
$run "arg"
```
## template

Country: *Idetifier*={USA, Great Britain, Canada}, Count of day: *num*, temperature: *num* Cels|Fahr

  
## example
**input:** 
run "country: USA, count of day: 5, temperature: 33 Fahr"

**output:**
checklist:
  - american flag
  - 600 USD
  - pasport
  clothes:
    - 6 pairs of socks
    - sweater
    - all-weather jacket
    
    
**input:** 
run "country: Canada, count of day: 3, temperature: -3 Cels"
    
**output:**    
checklist:
  - Canadian flag
  - pasport
  - 340 canadian dollar
  clothes:
    - 4 pairs of socks
    - sweater
    - winter jacket
    - winter hat    


