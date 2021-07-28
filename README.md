# BT_Challenge
Solutie pentru Challenge-ul tehnic

Solutia este scrisa in Java, iar pentru baza de date am folosit PostgreSQL. Proiectul este unul maven si foloseste Spring.
Am clonat si modificat un proiect demo la care am lucrat in facultate pentru a nu pierde timpul cu setupul de environment.

Metodele pentru rezolvarea taskurilor se gasesc in clasa ApplicationLogic din pachetul Services.
Pachetul Entities contine modelele pentru baza de date.
Pachetul Repositories contine clasele pentru interogarile pe baza de date cu hibernate.
Pachetul DTOs contine clase pentru a manipula mai usor entitatile, iar clasele builder sunt folosite pentru transformarea din clasa model in clasa DTO si invers.
Pachetul services contine clase cu metode ce implementeaza CRUD operations si logica aditionala.
