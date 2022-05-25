# Dott Technical Test
My Solution for the Dott Tech Test

## For this test use:

1 --- ScalikeJDBC - ORM

2 --- PostgreSQL - Database

3 --- And a lot of SQL - :)

### Project structure


    .
    ├── project                                  # Compiled files (alternatively `dist`)
    ├── src                                      # All project source code
        ├── main                                 # Application files core
            ├── db                               # Creations and Inserts in the database
                ├── migrations                   # Create my database structures
                ├── seeding                      # Populates my database
            ├── Functions                        # My general data handling function and parsing function
            ├── Main                             # Calling my functions and inputting values
    ├── .gitignore                               # Files and masses for git to ignore
    ├── build.sbt                                # Project dependencies
    └── README.md                                # Project explanation

## Observation
#### 1 ---  Don't forget to change the database username and password in the files
#### 2 --- Migrations and Seedings must be performed and independently of my main application !
