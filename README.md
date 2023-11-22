This is the assignment given by MoEngage. For this assignment, I used `httpURLConnection` to fetch json response from the API. Then I deserialized the response using `Gson` library. 
I used repository pattern to implement SSOT (single source of truth). The app fetch data from the api and store it in room database. The UI layer observe the room database and update its UI accordingly.  

# Screenshots
|                   Home Screen                   |                   Article Screen                    |
|:-----------------------------------------------:|:---------------------------------------------------:|
|     ![home](screenshots/home.png?raw=true)      |     ![result](screenshots/article.png?raw=true)      |
|                Home Screen Dark                 |                     Article Screen Dark                     |
| ![home_dark](screenshots/homeDark.png?raw=true) | ![result_dark](screenshots/articleDark.png?raw=true) |
