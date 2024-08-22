# Game: Rock, Paper, Scissors
It's a web application that you can run locally or deploy wherever you want.

Please execute command to run it locally:
   ```bash
   ./gradlew build bootRun
   ```
To store statistics it is using H2 in memory DB.
### How to play
1. Create a game
    ```
   curl --location --request POST 'localhost:8080/api/v1/game'
    ```
2. Make move
    ```
   curl --location 'localhost:8080/api/v1/game/1/play' \
    --header 'Content-Type: text/plain' \
    --data 'PAPER'
    ```
3. Complete game
    ```
   curl --location --request POST 'localhost:8080/api/v1/game/1/complete'
    ```
4. Get statistics
    ```
   curl --location 'localhost:8080/api/v1/game/1'
    ```
### Resources
You can use [postman collection](testgame.postman_collection) to play
