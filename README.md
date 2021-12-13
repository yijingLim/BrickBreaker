##### Name : Lim Yi Jing
##### OWA : hcyyl9

## Refactoring
Organised the content of the codes into different package and rename the neccessary classes
- Implementation of MVC Design Pattern
  -  Brick, Player and ball class are arrange and separate into Model, Controller and View classes
  -  For example, brick class is the model, brickView class is the view and BrickController is the controller
- Basic Maintainance
  - Larger classes are broken down by extracting the subclass
    - Crack class is removed from brick class
    - makeSingleleveltype and makechestboardlevel is extracted from wall class into level class
  - Remove uneccessary classes
    - SetSpeed is removed as setSpeedX and setSpeedY is sufficient
    - RadiusA and radiusB is converted to radius only as the radius of ball is same
  - Factory Method Design
     - Brick Factory is created using a getbricktype method. When a new brick class is created, it will refer to brickfactory class to get the type
 -  This enum class is used in brickcontroller class that help to store the constant direction of the brick 
- Junit testing on ball, brick, player, sorthighscore and wall class in the Test folder
- Build Files (Maven) is added
 

## Addition
 - Instruction Page
    - An instruction page is introduce from the home menu to give guidelines on how to play the game
    - The instruction menu consists of start button that can set user to start playing the game
 - A Level bar on the game to display the levels, remaining brick and ball count is located as the bottom of the wall for easy user interface for user.
 - A Pause button is introduced to the system and hence the user able to access to pause menu using both escape key and button.
 - In the Pause Menu, a main menu function is add to allow the user to return to the main page
 - A Power up block is added into the game. The power up block will drop down from the brick after hitted by the ball within the specific probability
 - When power up block hit with the player bar, 2 power up functionality which is extra ball and increase player paddle width is introduced
    - Only the original player ball can initialize the power up function
 - HighScore Leaderboard
    - When the user loses the game or finish the level, a pop up window will display to allow user to enter their username.
    - The new value is compare with the 3 top scorers in the previous game and rearrange them according to descending order.
    - The new leaderboard is writen into the highscore text file and displayed on the screen
 - A new titanum brick is added into the game
    -  It has stronger brick strength as compared to other brick. A new level of a mixture of titanum brick and cement brick is created

## Documentation
 - Javadocs is added into the codes by commenting the method and classes 

## Git Used
- Git is a version control system help to track and save the changes along the project
- Commit history is used to track the old commits
- Meaningful commit messages are used to track the commits made










