# Max-nJ &nbsp; ![DEVELOPMENT STATUS: version 0.1](https://badgen.net/badge/DEVELOPMENT%20STATUS/version%200.1/green)

**Max-n** in **J**ava
<br><br>
An abstract implementation of the Max-n algorithm that takes away the hard work, and lets focus on the evaluation function.

## What is the Max-n algorithm?
Max-n algorithm is an algorithm that can be used to find the best move in a game with n players. It does so by recursively evaluating the possible moves that each player can make, and then selecting the move that maximizes the score of the player that is currently playing. Which is effectively the same as predicting the moves that the other players will make, assuming that they are playing rationally, and are aiming only to maximize their own score.
<br>
If you are already familiar with the [Minimax](https://en.wikipedia.org/wiki/Minimax) algorithm, then you can think of Max-n as a generalization of Minimax that can be used with n players instead of just two. Minimax happens to just be a special case of Max-n with n=2, and where the first players' score is the negative of the second players' score.


## Important notice:
An important feature that is yet to be added is pruning.

## Example:
Check out the TicTacToe *test* for an example of how to use it.

## How-to:
After cloning the repository, install the project locally like so:
```bash
mvn clean install
```

And then you can use it in your other projects by adding it as a dependency in your `pom.xml` file:
```xml
<dependency>
    <groupId>telos-matter</groupId>
    <artifactId>max-nj</artifactId>
    <version>0.1</version>
</dependency>
```