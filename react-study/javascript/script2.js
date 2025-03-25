// destructuring
let person = {
    name : "noona",
    age : 20
}

// let name = person.name
// let age = person['age']

let {name, age} = person

console.log(name, age)

let array = [1, 2, 3, 4]
let [a, b] = array
let [c, d, ...rest] = array

console.log(a, b)
console.log(rest)