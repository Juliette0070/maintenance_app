const fizzBuzz = require('../src/fizzbuzz');

test('Pour n allant --> 8', () => {
    expect(fizzBuzz(8)).toEqual([1, 2, 'Fizz', 4, 'Buzz', 'Fizz', 7, 8]);
});

test('Pour n allant --> 15', () => {
    expect(fizzBuzz(15)).toEqual([1, 2, 'Fizz', 4, 'Buzz', 'Fizz', 7, 8, 'Fizz', 'Buzz', 11, 'Fizz', 13, 14, 'FizzBuzz']);
});