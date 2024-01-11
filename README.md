[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/yw5p-AaX)
# tema-2-poo-2023
Tema data este un program Java conceput pentru a facilita gestionarea informațiilor despre studenți și cursuri în cadrul unui secretariat. Programul include funcții precum adăugarea de studenți, crearea de cursuri, gestionarea notelor, gestionarea preferințelor cursurilor studenților, gestionarea contestațiilor. 

Programul este împărțit în mai multe clase:
Student: reprezintă un student cu atribute precum numele, media, programul de studiu și lista de preferințe de curs.
Curs: O clasă generală reprezentând un curs cu atribute precum numele, capacitatea maximă, programul de studii de care apartine, lista de studenți înscriși și capacitatea actuala. Este parametrizat cu un tip care extinde clasa Student.  
Secretariat: Aceasta este clasa principală care coordonează actiunile. Aceasta include baza de date a studenților (sDataBase), 
programul de master (masterCDatabase), programul de licență (bachelorCDatabase) și baza de date combinată a programelor 
de studii (cDataBase). Această clasă implementează diverse operațiuni pentru gestionarea studenților și a cursurilor. 
Baza de date pentru studenți  (sDataBase): Implementată ca  ArrayList. Acest lucru oferă flexibilitate pentru gestionarea 
dinamică a studenților și permite adăugari și ștergeri ușoare. Baze de date de curs (masterCDatabase, bachelorCDatabase, 
cDataBase): Implementat ca ArrayList. Această alegere permite înscrierea și gestionarea eficientă a studenților în cadrul unui 
curs, cu redimensionarea dinamică. Student Preferences (Student Preferences): Reprezentat ca  ArrayList în clasa Student. Acest lucru vă oferă flexibilitatea de 
a gestiona un număr diferit de setări pentru fiecare student. 

Programul citește comenzile și parametrii separați prin spații din fișier. Exemple de comenzi acceptate includ „add_student”, „add_course”, etc. 
Programul gestionează excepții precum InvalidCommandException, UnknownCommandException și StudentAlreadyExistent și oferă mesaje de eroare informative atunci când ceva nu merge bine. 
