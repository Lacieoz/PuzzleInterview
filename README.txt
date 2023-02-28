
GENERAL INFORMATION

The software has been built following TDD methodology, using Java (v18) as coding language,
using Maven as project management tool and using Docker.

Software inputs are 3 files, contained in the folder "inputs" situated in the root folder.
Those filese must have following name and contents :
    - "map.json" -> json containing the map;
    - "idStart.txt" -> must contains a not empty row with a number, otherwise an exception
                       is given;
    - "objectsToFind.txt" -> must contains a not empty row with the object names to be found,
                             the names must be divided by a comma ",".

In the root folder there is the "scripts" folder that contains the scripts requested in the
instructions given, even though I chose to only create the run.sh script.
I made this decision because the build and the tests are managed by Maven and are done
building the Docker image.

Tests are done with JUnit and are present in the folder "src/test/java".
In "src/test/resources" and "src/test/resourcesEmpty" are present the files used for the
tests of input.

Application.java is the class with the main method and Solver.java is the main class used for
solving the problem given.
Both are in the folder "src/main/java/rrpuzzle" which contains all the codebase.

----------------------------------------------------------------------------------------------

SOFTWARE INSTRUCTIONS (Docker commands)

// to build the docker image, build the software and making the tests
docker build -t mytest .

// to run the software with the inputs inserted in the folder "inputs"
docker run -v $(pwd):/mnt -w /mnt mytest ./scripts/run.sh
