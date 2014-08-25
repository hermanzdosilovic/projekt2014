Projekt2014
===========

Solution of project assignment written in *Java* for class [*Discrete Mathematics 1*](http://en.wikipedia.org/wiki/Discrete_mathematics) of my second semester on [Faculty of Electrical Engineering and Computing (FER)](http://www.fer.unizg.hr/en) in [Zagreb](http://en.wikipedia.org/wiki/Zagreb).

Problem description
-------------------
In a array of digits `1, 2, 3, 4, 5, 6, 7, 8, 9` you need set operators (8 of them) between digits so that result always equals `2014`. Operations of same rank are evaluated from left to right.

###Operations that you can use
* `+` - additon
* `-` - substraction
* `*` - multiplication
* `/` - division
* `^` - exponentiation
* `|` - concatanation

	__Concatanation example:__
	* `1` `|` `2` can become `12`
	* `4` `|` `5` `|` `6` can become `456`

###Examples

For `2010`, `2011` and `2012` we can have something like:
	
	12*34*5+6*7-8*9 = 2010
	1+2345*6/7+8-9 = 2010
	1/2*3*4*5*67-8+9 = 2011
	1x2/3*45*67-8+9 = 2011
	1+2+3*(4*(5+6)*(7+8)+9) = 2010
	1+(2+34)*56-7-8+9 = 2011
	-12*3+4^5*(-6+7-8+9) = 2012
	
###Extra operation
* `_` - you can write numbers in differet base

For `2010` we can have something like:

	1+(2+3)*(4+56_78)+9 = 2010
	because 56_78 = 5*78^1 + 6*78^0 = 396

###Important Notes
* Different representations that depend on position of sign in multiplication are considered identical.
	* `1-2*(3-4)` is the same as `1-2*(-3+4)`
* Expressions based on properties of `1` are considered different.
	* `1^23` is the same as `1^(2*3)` and is the same as `1^(2/3)` and `1^(2^3)`.
* You are allowed to use *base operation* to *lose* digits.
	* `4_5` and `4_56` equals `4`
	* `(4_5)_6` is considered identical as `4_56`
	
About my solution
-----------------
I found `5768` expressions (check *output.txt* file) that equal to `2014`. But I made few changes in problem definition.
###Concatanation `|`
Concatanation operation has one additional property. It can concat two or more expressions.
	
	(1+2)(3*4) = 312
	(-1-2)(3+4) = -37
	
First member can be positive or negative, others need to be positive.

So you can't have something like:
	
	(1+2+3)(4-5*6)(7-8/9)
	
###Identical expressions
Recognition of identical expressions is not implemented. Identical expressions are for example:

	2-(3+4) and 2-3-4
	
###Negative sign before number `1`
Number `1` cannot have negative sign, so you cannot have something like:
	
	-1+2*3

###Extra base operation `_`
Base operation is not implemented.
	
How to run program
------------------
**You need to have Java installed on you machine to run this program.**

	java -cp bin hr.fer.zpm.dismat1.projekt2014.Projekt2014 <arguments>
	
`<arguments>` are optional. You can have at most 2 arguments.

* First argument is always a path to output file i.e. file where all expressions will be written.
* Second argument is number of operators to use in calculating solution of problem.
	
###Examples

	java -cp bin hr.fer.zpm.dismat1.projekt2014.Projekt2014
	
This command will use all `8` operators i.e. all 9 digits. And output will be written in file `output.txt` (this is default file)

	java -cp bin hr.fer.zpm.dismat1.projekt2014.Projekt2014 myfile.txt 5

Will use digits `1 2 3 4 5 6` (5 operators between them) and output will be written in file `myfile.txt`

	java -cp bin hr.fer.zpm.dismat1.projekt2014.Projekt2014 hisfile.txt

Program will use all digits and output will be written in file `hisfile.txt`

###Final Thoughts
It took my *Intel Core 2 Duo T8300 2.4 GHz* machine `1 hour 32 minutes and 3 seconds` to find solution for 8 operators i.e. all 9 digits.

And `1 hour and 7 minutes` on my *2,4 GHz Intel Core i5* machine. 

This version is not multithreaded solution, but next will be.

Enjoy in solving this problem. I would love to see your solution. :)

More detailed description about my algorithm will be written soon.

License
-------
The MIT License (MIT)

Copyright (c) 2014 Herman Zvonimir Došilović

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.