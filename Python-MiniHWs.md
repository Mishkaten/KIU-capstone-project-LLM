-Ex1-
Write a program, which reads number N from console and prints first N numbers of Fibonacci sequence. Fibonacci sequence starts with 0 and 1. Every other number is the sum of the two preceding ones.
First 12 numbers of Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89.
------

-Ex2-
Write a function 'abs' which takes a number as an argument and return it's absolute value.
Ex.: -3 -> 3
        1 -> 1
------        

-Ex3-
Write a function 'arg_sum' which takes arbitrary many arguments (*args). Assume that all arguments are numbers and return their sum.
Ex.: arg_sum(1) -> 1
      arg_sum(1,2,10) -> 13
      arg_sum() -> 0
------        

-Ex4-
Write a function 'isSecondBitOn' which takes a number as an argument and checks if it's second bit (corresponding to 4). Function should return True if the bit is on or False otherwise.
Ex. 6 -> 1
     10 -> 0
------        

-Ex5-
Write a function 'oddCounter' which takes a list of numbers as an argument and counts how many odd numbers are in it.
You can only use lambda functions and HOFs: takewhile, dropwhile, zip, filter, map, reduce, enumerate, any, all, sum.
You are not allowed to use for and while loops.
Ex.: `[1,2,3,11,12,5,5,101, 200] -> 6`
------        

-Ex6-
Instructions
Write a class Car. Car should have following attributes:
  make - Hidden attribute for make (brand) of the car.
  model - Hidden attribute for model of the car.
  fuel - Hidden attribute, fuel left in tank in litres.
  litresPer100km - Fuel consumption per 100 kilometres.

Car class should have following methods:
  constructor - It should take make and model as arguments and initialize attributes. Fuel's default value should be 0 and litresPer100km's - 8.0.
  getMake, getModel, getFuel - getter functions which return current make, model and fuel of the car.
  addFuel - method takes one argument, fuel in litres, and adds it into the tank (fuel attribute). You can assume that tank can hold infinite amount of fuel.
  drive - this method is passed number of kilometres driven by the car and it should decrease amount of fuel accordingly. For example if fuel consumption (litresPer100km) is equal to 8.0 and distance is 150 kms, than car will consume 12.0 litres of fuel and 12.0 should be subtracted from fuel. You can assume that there always is enough fuel.
  distanceToE - this method should return how many kms left until fuel tank becomes empty. For example, if fuel consumption is 6.0 and there still is 8.4 litres left in the car than fuel is enough to drive 140 kms until tank becomes empty.
------        

-Ex7-
Write 3 classes - FirstBase, SecondBase and Derived. 

FirstBase class should have property 'X' (not attribute), X should have
getter and setter methods. X's setter should check that X is always assigned positive number, in case of invalid argument do not modify X at all. FirstBase should also have constructor that will initialise X with 0. Furthermore FirstBase should have two methods 'printX' and 'printName'. 'printX' should print X in console and printName should print  "FirstBase". 

SecondBase class should have property 'Y' (not attribute), Y should have getter and setter methods. Y's setter should check that Y is always assigned positive number, in case of invalid argument do not modify Y at all. SecondBase should also have constructor that will initialise Y with 1. Furthermore SecondBase should have two methods 'printY' and 'printName'. 'printY' should print Y in console and printName should print  "SecondBase".

Derived class should inherit from both FirstBase and SecondBase classes (in that order). Derived should have 2 methods: 'printXYSum' and 'printName'. 'printXYSum' should print sum of X and Y to console and 'printName' should print 'Derived'. 

Example for testing 'Derived' class:
~~~
  d = Derived()
  
  d.printName() # Derived
  d.X = 5 
  d.Y = 3
  d.printX() # 5
  d.printY() # 3
  d.printXYSum() # 8
  d.X = -3
  d.Y = 1
  d.printX() # 5
  d.printY() # 1
  d.printXYSum() # 6
~~~
------        
 
-Ex8-
Write a small pygame application, that will draw a square in the center of the screen (You can use pygame.draw.rect or pygame.draw.line functions for that). Player should be able to move square using AWSD keys. Check pressed keys in the update method and if A is pressed decrease square's X coordinate by 5 pixels, if D is pressed increase it by 5. If W or S is pressed increase or decrease Y coordinate of the square.  
------        

-Ex9-
"""
This assignment is a quizz. You should answer
questions given below. Each question has exactly
one correct answer. You get 1 points if everything
is correct or 0 otherwise. No partial scoring for
Mini-Homework.

You should mark your choice with 'x'.

Example:
~~~
Question?
[ ] Not selected
[X] Selected

NOTE: If you mark more than one answer, delete or
modify part of the question, you will get 0 for that
question.
"""

# Q1

def fun(N, M, K):
    count = 0
    for x in range(N):
        for y in range(M):
            if x // y == K:
                count += 1
            if y // x == K:
                count -= 1

"""
What is the time complexity of the given function?

[ ] O(N*M+K)
[ ] O(N+M)
[ ] O(N*M)
[ ] O(2*N*M)
"""

# Q2

def fun(lst):
    sm = 0
    for e in lst:
		sm += e
    return sm

"""
What is the time complexity of the given function, if `lst` contains N elements?

[ ] O(N*(N-1)/2)
[ ] O(N)
[ ] O(1)
[ ] O(2*N)
"""
# Q3

def fun(lst):
    ans = [x for x in lst if x % 2 ==0 ]
    return ans

"""
What is the time complexity of the given function, if `lst` contains N elements?

[ ] O(N*N)
[ ] O(N)
[ ] O(1)
[ ] O(N*log2(N))
"""
------



-Ex10-
Write a function, which takes a list of grades (a grade can be upper case English letter from 'A' to 'F') and plots the data with bar chart. Each letter should have a column and height should be based on its frequency.
------













