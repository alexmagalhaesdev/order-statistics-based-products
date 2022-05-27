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

## Running an application
#### 1 --- Change username and password in connection pool
#### 2 --- Run Migrations and Seeds in that order respectively
#### 3 --- Run the Main.scala file and enter the input data for the search
#### 4 --- Wait for the result and get success with the application :)
