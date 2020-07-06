This program simulates the spread of Covid-19 in a terminal based simulator. The simulation runs on a rectangular grid, with each spot either being occupied by a person (0 or 1 or 2) or nothing (filler). Healthy people without the virus are marked with a "0", infected people are marked with a "1", and people who have beaten Covid-19 are marked with a “2”. The simulation runs in iterative "cycles". With each cycle, people will randomly move in one direction. If an infected person physically contacts a healthy person, the healthy person is infected. Over various periods of time, infected people will become immune and beat Covid-19. 

A simulation can be started with the following constructor parameters...

* **width** - the horizontal length of the board where people interact
* **height** - the vertical length of the board where people interact
* **popSize**- the number of people on the table at any given time (this is static)
* **startingRate** - the % of people who will start the simulation with Covid-19. Must be a value between 0 and 1. 
* **immunityRate** - a rating that determines the difficulty to overcome Covid-19. Ideally a value between 1-10. 
* **filler**- the character used to fill board spaces where no person currently is

Once a simulation has been created, there are two main ways to use one...

* **printCycle()** - Runs one cycle and prints it to the console.
* **completeSimulation()** - Runs multiple cycles until all people have been infected or everyone is immune. This occurs behind the scenes, so each cycle is not printed to the console. Once completed, a list of numbers is printed, which represented the number of people infected at each cycle. At the end of the list, the total number of cycles is also printed.
Noticeably, you may also run runCycle(), which is used by both methods above. runCycle() allows a cycle to be ran without printing the table.

Simulations themselves are implemented as a simulation object in the ```Simulation.java``` file. Simulation has an aggregate relationship with an array of persons, with the person object implementation being found in the ```Person.java``` file.

To display a working simulation with examples, a ```Driver.java``` main class has been included for convenience. It shows many more powerful implementations of the simulator worth checking out. The Driver works with the following console interface options below…

* **1) run cycle** - executes runCycle();
* **2) complete simulation** - executes completeSimulation();
* **3) exports simulations**- runs a given number of simulations and returns case count results in a csv file. This feature is great for Microsoft Excel analysis. 
* **4) modify parameters** - enables the original command arguments to be changed. Noticeably, the delimiter is ```,``` not ``` ``` like traditional Java command arguments (this will be changed in the future, sorry).
* **5) manual mode** - runs 4 then 3, allowing clients to pick exact parameters before exporting data to a csv. 
* **6) quit**- safely exits the application. 

Since Driver runs in the terminal, Driver runs with command arguments. The command arguments are verbatim the Simulator construction parameters mentioned above. The Driver class supports partial input of command arguments as long as parameters are in order.  


