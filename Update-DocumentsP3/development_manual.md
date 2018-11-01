## Jungle Game - Development Manual

### Setup
Before developing on this project, make sure you have installed the latest versions of:

- [Eclipse for Java Enterprise Edition](http://www.eclipse.org/downloads/packages/release/2018-09/r/eclipse-ide-java-ee-developers)
- [Java SDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/#installation)

In Eclipse, open the Git Repositories View by going to Window -> Show View -> Other... -> Git -> Git Repositories.

Press the "Clone a New Repository" Button ![](../.pastes/2018-11-01-06-27-59.png) and select GitHub.

Clone the repository through its URL https://github.com/cs414-C-Team/cs414-f18-001-C-Team.git or by searching "cs414-f18-001-C-Team". Choose a directory to store the project and Finish.

![](../.pastes/2018-11-01-06-32-47.png)

In the Package Explorer, ensure that Eclipse has placed the `main` and `test` packages on the project's build path. To do this, right click on any directory and select Build Path -> Configure Build Path.

![](../.pastes/2018-11-01-06-36-40.png)

Add folders `cteam/src/main/java` and `cteam/test/main/java` to the build path if they aren't included already. Under "Libraries", also ensure that java-11-openjdk is selected as the project's JRE. 

### Packages
Inside the `src` directory are our `main` and `test` folders. In main is the actual code for the game, while test contains test cases for the main code. In each, the package structure for our project is `edu.colostate.cs.cs414.cteam.p3`. The project has 3 packages `model`, `view`, and `controller`. Model contains code representing data, View contains code for displaying the data to the user, and Controller contains code that links the system and the user.

![](../.pastes/2018-11-01-06-50-08.png)

### Running the Game

To run the main GUI version of the game as it currently developed, open `GameLauncher` in `main.java.edu.colostate.cs.cs414.cteam.p3.view.` Run this file in Eclipse to open a JFrame window where the game can be played. Once starting a game, click a piece (on the current player's side) then click another square to move that piece

![](../.pastes/2018-11-01-07-26-57.png)

### Testing

To run all current tests for all packages, simply run the test suite `AllTests` in `test.java.edu.colostate.cs.cs414.cteam.p3` in Eclipse. Access the test cases for various classes in the `model`, `view`, and `controller` packages.

