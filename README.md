# Java Chess


A simple, interactive 2D chess game built with Java and the Swing framework.

## Motivation

This project was developed with the primary goal of gaining a deeper understanding of Java, its UI properties, and the Swing library. It serves as a practical application of object-oriented programming, grid layouts, and graphical user interface design.

## Current Features

* **Interactive Chessboard:** A fully rendered graphical board using Java Swing components (`JPanel`, `JLabel`, `GridBagLayout`).

* **Move Highlighting:** Clicking on a piece highlights its available movement and attack squares.

* **Playable Backbone:** Functions as a digital physical chessboard, allowing two players to move standard pieces and capture opponents.

## Limitations & Known Issues

* **No Intelligent Illegal Move Checker:** The game acts as a straightforward digital board and does not strictly enforce complex chess rules (e.g., check/checkmate detection, preventing pins). Players must follow the rules manually.

* **No Audio Feedback:** The game currently lacks interactive sound effects for moves and captures.

## How to Run

To run the game, ensure you have the Java Development Kit (JDK) installed on your system.

1. Compile the Java files in the project directory:

   ```
   javac *.java
   ```

2. Execute the compiled start file:

   ```
   java start
   ```

## Project Structure

* `start.java` / `start.class`: The main entry point that organizes and launches the application.

* `board.java`: Handles the graphical user interface, chessboard grid rendering, and loads the image assets.

* `Engine.java`: Manages user interactions, coordinates piece selection, and processes board state clicks.

* `pieces.java`: Handles the logic for individual piece movements and calculates highlighted squares.

* `Assets/`: Directory containing the `.png` image sprites for all chess pieces.

## Author
- Hans Kimaro
