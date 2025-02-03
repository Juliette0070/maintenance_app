const conway = require('../src/conway');

test('Pour n allant --> 2', () => {
    expect(conway(2)).toEqual(["1", "11"]);
});

test('Pour n allant --> 4', () => {
    expect(conway(4)).toEqual(["1", "11", "21", "1211"]);
});