This program simulates the spread of Covid-19 in a terminal based simulator. The simulation runs on a rectangular grid, with each spot either being occupied by a person (0 or 1 or 2) or nothing (filler). Healthy people without the virus are marked with a "0", infected people are marked with a "1", and people who have beaten Covid-19 are marked with a “2”. The simulation runs in iterative "cycles". With each cycle, people will randomly move in one direction. If an infected person physically contacts a healthy person, the healthy person is infected. Over various periods of time, infected people will become immune and beat Covid-19.

A simulation can be started with the following constructor parameters...

* **width** - The horizontal length of the board where people interact
* **height** - The vertical length of the board where people interact
* **popSize** - The number of people on the table at any given time (this is static)
* **startingRate** - The % of people who will start the simulation with Covid-19. Must be a value between 0 and 1.
* **immunityRate** - The metric that determines the difficulty to overcome Covid-19. Ideally a value between 1-10, where 10 is hardest to overcome Covid-19.
* **filler** - The string/character used to fill board spaces where no person currently is. Ideally a single character to keep monospace with people, but any String is supported.

Once a simulation has been created, there are two main ways to use one...

* **printCycle()** - Runs one cycle and prints it to the console.
* **completeSimulation()** - Runs multiple cycles until everyone is immune or the virus disappears. This occurs behind the scenes, so each cycle is not printed to the console. Once completed, a list of numbers is printed, which represented the number of people infected at each cycle. At the end of the list, the total number of cycles is also printed. Additionally, an "infection score" is also printed, which is the sum of the list of people infected at each cycle (this is essentially the area under the curve representing people infected).

Noticeably, you may also run runCycle(), which is used by both methods above. runCycle() allows a cycle to be ran without printing the table.

Simulations themselves are implemented as a simulation object in the ```Simulation.java``` file. Simulation has an aggregate relationship with an array of persons, with the person object implementation being found in the ```Person.java``` file.

To display a working simulation with examples, a ```Driver.java``` main class has been included for convenience. It shows many more powerful implementations of the simulator worth checking out. The Driver works with the following console interface options below…

* **1) run cycle** - Executes printCycle()
* **2) complete simulation** - Executes completeSimulation()
* **3) exports simulations** - Runs a given number of simulations and exports the number of people infected during each cycle to a csv file. This feature is great for Microsoft Excel analysis, as shown below.
* **4) modify parameters** - Enables the original command arguments to be changed. Noticeably, the delimiter is ```,``` not ``` ```  like traditional Java command arguments (this will be changed in the future, sorry).
* **5) manual mode** - Runs option 4 then option 3, allowing clients to define unique parameters before exporting data to a csv.
* **6) quit** - Safely exits the application.

Since Driver runs in the terminal, Driver runs with command line arguments. The command line arguments are verbatim the Simulator construction parameters mentioned above. The Driver class supports partial input of command line arguments as long as parameters are in order.  


![Covid-19 Growth Graph](https://i.imgur.com/ILHAIMo.png)
