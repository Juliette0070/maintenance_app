function conway(n) {
    const result = [];
    let current = '1';
    for (let i = 1; i <= n; i++) {
        result.push(current);
        current = next(current);
    }
    return result;
}

function next(current) {
    let result = '';
    let count = 1;
    for (let i = 0; i < current.length; i++) {
        if (current[i] === current[i + 1]) {
            count++;
        } else {
            result += count + current[i];
            count = 1;
        }
    }
    return result;
}

module.exports = conway;