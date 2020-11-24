# Java Project

This repository contains the Java code for building REST api for CRUD and search operation


## Software / Tools dependency.

* Mysql database
* Java (java 15.0.1)
* Springboot 4.0.0
* Eclipse
* JUnit
* Mockito


## How to execute program

1. Clone project from repository.
      `git clone https://github.com/tarika30/javaproject.git`

2. Install mysql database and restore schema from path javaproject/database.

3. Build Project and run application as springboot application.

4. Open postman/web browser and run following api's.
     - To get all data(list of all movies) from the table.
         GET http://localhost:9090/movie/getall

     - To get particular data(specific movie) from database.
         GET http://localhost:9090/movie/getmovie/{moviename}        ## enter desired movie name at {moviename}

     - To save a data(movie)
         POST http://localhost:9090/movie/savemovie
      body example
       {
        "name": "Inception",
        "director": "Christopher Nolan",
        "rating": 9.0
       }

     - To update the data(movie) already present in database         ## Will save movie if not present in DB
           PUT http://localhost:9090/movie/updatemovie
        body example
         {
          "name": "The Dark Knight",
          "director": "Christopher Nolan",
          "rating": 8.0
         }

     -  For Search functionality
         i. On basis of directory only                           ## Will return all movies of provided director
            GET http://localhost:9090/movie/searchmovie/director/{directorname}   ## enter director name


         ii. On basis of director and rating            ## Will return all movies of provided director and
                                                           movies with rating above given rating
         GET http://localhost:9090/movie/searchmovie/{directorname}/{rating}  ## enter director name and rating

        iii. On basis of rating only                           ## Will return all movies above rating provided
           GET http://localhost:9090/movie/searchmovie/rating/{rating}   ## enter rating

     -  To delete movie from database
           DELETE http://localhost:9090/movie/deletemovie/{moviename}   ## enter movie name
