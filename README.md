This program simulates the spread of Covid-19 in a terminal based simulator. The simulation runs on a rectangular grid, with each spot either being occupied by a person (0 or 1) or nothing (filler). Healthy people without the virus are marked with a "0" while infected people are marked with a "1". The simultion runs in iterative "cycles". With each cycle, people will randomly move in one direction. If an infected person makes contact with a healthy person, the healthy person is infected.

A simulation can be started with the following constructor paramiters...

* **width** - the horizontal length of the board where people interact
* **height** - the verticle length of the board where people interact
* **popSize**- the number of people on the table at any given time (this is static)
* **filler**- the character used to fill board spaces where no person currently is

Once a simulation has been created, there are two main ways to use one...

* **printCycle()** - Runs one cycle and prints it to the console.
* **completeSimulation()** - Runs multiple cycles until all people have been infected. This occurs behind the scenes, so each cycle is not printed to the console. Once completed, a list of numbers is printed, which represented the number of people infected at each cycle. At the end of the list, the total number of cycles is also printed.
Noticably, you may also run runCycle(), which is used by both methods above. runCycle() allows a cycle to be ran without printing the table.

Simulations themselves are implemented as a simulation object in the ```Simulation.java``` file. Simulation has an aggregate relationship with an array of persons, with the person object implementation being found in the ```Person.java``` file.

To display a working simulation with examples, a ```Driver.java``` main class has been included for convenience.
