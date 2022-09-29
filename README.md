# FIFA-Ticket app. 
## A small project made with Spring boot / Maven and integrated SQL database using java persistence (JPA)

## Functionalities:
- Login (admin and users)
- Security
- SQL database connected
- Rest controller that provides details about a certain contest.


## How to run it?

 1) Run as -> Maven Build... -> Goals = clean install
 2) Maven -> Update Project
 3) Run as -> Spring Boot App


## Before you can run it: 
1) Create a new database scheme inside your sql workbench and call it: "fifaworldcup".
2) Inside the JPA Config make sure that hibernate is set to "Create" (line 59).
3) Run the app
4) Load the .sql file from package util, in your newly created database.
5) Inside the JPA Config make sure that hibernate is set to "Validate" (line 60).
6) Run the app
