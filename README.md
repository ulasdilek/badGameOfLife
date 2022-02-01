# Bad Game of Life
Conway's Game Of Life, but made by me in a very ugly and inefficient way. Still, I am proud!
I want to share my appreciation to cellular automata with anyone who comes across this repo. If you are intrigued, you check these sources for more info and some fun:

[Inventing Game of Life (John Conway) - Numberphile](https://www.youtube.com/watch?v=R9Plq-D1gEk)

[Play John Conway's Game of Life](https://playgameoflife.com/)

![GUI](https://user-images.githubusercontent.com/91346067/152045706-03db93e2-87b3-455f-8e48-5f2346f8b520.png)

## Rules
- Each cell is either **DEAD** or **ALIVE**
- A cell's neighbours are horizontally, vertically and diagonally adjacent to the cell in question
- Each cell is affected by its neighbours in each iteration:
  - For a *living* cell:
    - The cell dies if less than 2 of its neighbours are alive. This mimics loneliness
    - The cell survives if 2 or 3 of its neighbours are alive. This mimics a balanced population
    - The cell dies if more than 3 of its neightbours are alive. This mimics overpopulation
  - For a *dead* cell:
    - The cell becomes alive if exactly 3 of its neighbours are alive. This mimics birth
    - The cell remins dead otherwise

## Why is it interesting?
I find it interesting to see finite systems with very basic rules creating very complex results that mimic life. It is in a way how our own bodies work with fundamental physical properties of dead particles. It drives me to think about the emergence of life, intelligence and conciousness.

The best thing about Game of Life is its popularity, which makes it quite accessible. But it is not the only cellular automaton. There are also other cellular automata with different rules that can give rise to more interesting results. Some of them can even create basic virtual ecosystems. So I think studying cellular automata can help us underpin the most fundamental properties of life, death, how they come to being and how they interact with both each other and the environment.

## Remarks
I will work on this to improve the user interface and add the essential functionality of the play/pause button some time. I am also planning to develop a more capable application in which the user can adjust the rules and see for themselves how different cellular automata work, submit newly-discovered interesting structures to a database for everyone else to access. But it would be too difficult for me right now, so I will wait for the appropriate time. I hope I was able to spark an interest in you for cellular automata.
***ENJOY!***

![doru](https://user-images.githubusercontent.com/91346067/146987214-deb9d78d-5a61-42e9-9505-720361232255.png#gh-light-mode-only)![doruDark](https://user-images.githubusercontent.com/91346067/146987215-7ed34029-44dd-4893-9859-7055e37825c7.png#gh-dark-mode-only)

@ulasdilek
