# Example: Creating a simple genetic algorithm runner

This example shows how to usilise the genetic algorithm for a simple example of evolving a target string.

## Part 1: Define a fitness function

We define a fitness function which counts the number of a's within a string.

```
// A fitness based on the 'a' count of a string.
private static FitnessFunction<String> stringScore = new FitnessFunction<String>() {
    @Override
    public double calculateFitness(String individual, Population<String> population) {
        double score = 0;
        for (int i = 0; i < individual.length(); i++) {
            if (individual.substring(i, i + 1).equals("a")) {
                score++;
            }
        }
        return score;
    }
};
```

## Part 2: Use GA builder to create the algorithm runner.

We first create the genetic algorithm builder:

```
// Create the genetic algorithm builder that configures the GA.
GeneticAlgorithmBuilder<String> builder = new GeneticAlgorithmBuilder<String>();
```

We assign a single population model. This defines how the populations of individuals within the GA are handled.
In this case we use a single group of 1000 individuals.

```
// Set the population model. We use a single population of 1000 individuals for this model.
builder.populatorModel().useSinglePopulationModel(stringScore, 1000);
```

The next stage of the algorithm involves assigning the round strategy which controls how rounds within the GA are executed.
This will require knowledge about the selection strategy, mutation operation operator and crossover operator.
These are used within a generational round strategy, which replaces the entire population with children each cycle.

```
// Create a random number generator.
Random random = new Random();
// Sets the round strategy. In this case, a generational strategy is used.
Selector<String> selection = new FitnessProportionateSelector<>(random, true);
Mutator<String> mutation = new StringMutator(random, 1.0);
Combiner<String> combination = new StringUniformCrossover(random);
// Set the round strategy to be generational with the configured mutation operators.
builder.roundStrategy().useGenerational(selection, mutation, combination);
```

Before individuals can be selected, we must create the initial population.
This is done using a Populator object, which is used to generate new individuals.

```
// The length of the strings we evolve.
final int stringLength = 10;

// Creates the populator for generating the initial population.
builder.populator().use(
    new StringPopulator(random, stringLength)
);
```

Additionally, the iterative GA algorithm will require a termination condition in order to halt.
In this example our target is to evolve a string comprised of of A's. As such we halt once the fitness reaches the number of A's using a fitness threshold.


```
// Creates the termination condition, in this case termination when fitness target is reached.
builder.terminationCondition().useFitnessThreshold(stringLength);
```

## Part 3: Tracking the algorithms progress.

It is useful to be able to track the progress of the algorithm as it executes.
The Interactor object is designed to allow handling of updates after each round of the GA.

// Create a interactor for showing us the best individual as the run progresses.
builder.interactors().add(new Interactor() {
    @Override
    public void interact(RunData observed) {
        IndividualFitness<String> best = observed.get(new SinglePopulationModel.OutputOptimum<>());
        System.out.println(best.getFitness() + " ~ " + best.getIndividual());
    }
});

In order to allow for extensions, all results are stored in a RunData object.
This maps output labels to their corresponding objects, and can be queried by passing in the relevent output object.

In this case ```SinglePopulationModel.OutputOptimum``` fetches the optimum individual within the single population model.
This returns an Individual Fitness object, which stores information about an individual and their evalutated fitness.

## Part 4: Putting it all together.

Finally, the algorithm can be created by calling build on the GA builder context.

```
// Finally create the GA object.
GeneticAlgorithm<String> ga = builder.build();
```

The returned object will allow the genetic algorithm to be run, as shown below:

```
RunData runData = ga.run();
```

As mentioned before, run data will store all algorithm outputs, both during and after the algorithm.
This data can be queried after completion in order to print some useful data concerning the execution.

```
// Extract data fromt he results.
int iterations = runData.get(new GeneticAlgorithm.OutputPeriodNumber<>());
Period periodType = runData.get(new GeneticAlgorithm.OutputPeriodType<>());
IndividualFitness<String> best = runData.get(new SinglePopulationModel.OutputOptimum<>());


System.out.println("GA terminated.");
System.out.println("GA ran for " + iterations + " " + periodType + "s");
System.out.println("Best found individual '" + best.getIndividual() + "' with fitness " + best.getFitness());
```

As before we query the run data by looking up values associated with Output objects.
We query the number of rounds that have been run by querying with key ```GeneticAlgorithm.OutputPeriodType```.
Additionally, we query the type of rounds by querying with key ```GeneticAlgorithm.OutputPeriodType``` (e.g. generations in this case).

### Example Output

```
3.0 ~ D|w2a,aaCc
4.0 ~ Dcwak,Yaaa
6.0 ~ aaaacaIa
6.0 ~ aaaaaUa'B
7.0 ~ axraaaaata
7.0 ~ aaaaaaaeK
7.0 ~ aaaaa1aa
8.0 ~ a!aaaaaaa
9.0 ~ aaaaaaaaa
8.0 ~ aadaaaaaa
8.0 ~ {'aaaaaaaa
9.0 ~ aaaaaa!aaa
9.0 ~ aaaMaaaaaa
9.0 ~ aaaaaaaaa
9.0 ~ awaaaaaaaa
9.0 ~ aaaaaaaaa
8.0 ~ aaaaa#aaa
8.0 ~ aaaaaaaa
9.0 ~ aaaaaaaaa
9.0 ~ aaaaaaaaa
9.0 ~ aaaaaaaAaa
9.0 ~ aaaaaXaaaa
9.0 ~ aaaaaaaa`a
9.0 ~ aaaaaaaaa
9.0 ~ aaaaaaaaa
9.0 ~ a$aaaaaaaa
10.0 ~ aaaaaaaaaa
GA terminated.
GA ran for 27 GENERATIONSs
Best found individual 'aaaaaaaaaa' with fitness 10.0
```


