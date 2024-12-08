=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: reiderm
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D ARRAY: The game state is modeled using a 2D char array, which is the obvious choice
  for a maze. P represents the player, T represents a trapdoor, E represents an exit,
  # represents a wall, and . represents open space.

  2. FILE I/0: All game files are saved and a user can also save/load a file if desired.
  So both file input and output is handled in the game.

  3. RECURSION: SolvabilityChecker uses recursion to navigate through .txt maze files
  looking for an exit to determine if they are solvable. If the maze isn't solvable the
  user will be alerted when the game is first opened.

  4. JUNIT TESTABLE COMPONENT: All the game state classes are tested comprehensively,
  to ensure the behavior is as expected. Per the rubric, the testing was handled
  directly with the logic classes, not by having the tests navigate the GUI.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

GameLogic: Handles all the game state logic and decision-making.

Maze: Handles the maze state itself and parses new mazes.

MazePanel: Handles the drawing of the maze panel GUI.

RunMazeRunner: Handles user input for running the game.

SolvabilityChecker: Checks to see if a maze is solvable
(an exit can be reached) using recursion.

StatusPanel: Handles the buttons for Save, Load, Reset
and text for indicating status.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

Getting the reset button working properly was a challenge.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

Overall the functionality is pretty well separated and the game was made using
the MVC framework which makes it fairly portable. Encapsulation is handled
consistently as well. If I had more time I'd like to work on the save/load/reset
feature by allowing users to select new maps from a dropdown and also have a
dedicated save menu.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

Java GUI: Full Course ☕ by Bro Code on YouTube

