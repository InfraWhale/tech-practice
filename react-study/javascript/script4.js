//삼항연산자
//let person = {name:"noona", age:17}
let person = null

if(person) {
    console.log(person.name)
} else {
    console.log("there is no person")
}

console.log(person ? person.name : "there is no person")