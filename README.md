# Hedgehog Solution

---

## 1. The Hedgehog Problem

### Conditions

Imagine a rectangular garden sized `M` x `N`. The garden consists of square
zones with one apple-tree in each zone. There can be several apples under
each tree.

There is a hedgehog in the upper left square of the garden. It moves to the
lower right square with some restrictions:

 * The hedgehog only can move to the next square to the right or to the next
   lowest square.

What is the maximum number of apples the hedgehog can collect on its way?

### Tech Conditions

 * The garden plan will be given as a table (`apples`) witch consists of `M`
   rows and `N` columns.
 * Table element `apples[i,j]` indicates the number of apples under the tree
   with coordinates `(i, j)`.
 * The input file (`input.txt`) will be structured in this way:
    * The first row will consist of numbers `M` and `N` separated by a
      space.
    * Each of the next `M` rows will consist of `N` values of `apples[i,j]`
      separated with spaces.
 * The output must be in the form of a file (`output.txt`) which must
   contain one natural number (the result of the calculation).

#### File Samples

##### Input File: `input.txt`

```
3 3
1 2 3
1 2 3
1 2 3
```

##### Output File: `output.txt`

```
12
```

---

## 2. The Hedgehog Solution

### Running the Application

The application can be run via the `main` method in the class
`hedgehog.Main`. The method will accept two arguments, the location on the
classpath of the input file, and the desired location of the output file.
These will default to `/input.txt` and `./output.txt` respectively if not
supplied.

### The Solution

The application works by virtue of the fact that all paths the hedgehog can
take will result in the same number of moves (`M + N - 2`). Also, at each
point on the path the hedgehog has a choice of two possible moves (right or
down). This lends itself well to the use of binary numbers (e.g. `0101`)
where zero could mean 'move right' and one could mean 'move down'.

Any binary number is a valid path so long as it has the correct number of
digits (i.e. the number of moves, `M + N - 2`), the number of right moves is
one less than the width of the garden (`M - 1`), and the number of down
moves is one less than the height of the garden (`N - 1`).

Therefore, all possible paths can be found by iterating through all numbers
less than or equal to the maximum number that can be defined using
`M + N - 2` binary digits (e.g. `4` digits > `1111` = `15`) and by filtering
out any invalid paths using the conditions above.

Once all possible paths have been determined, the maximum number
of apples the hedgehog can collect can be calculated by finding the sum of
apples on each path and then by selecting the highest value.
