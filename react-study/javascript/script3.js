//spread
let person = {name:"noona", age:12}

let person2 = {...person} // 깊은 복사 : 새 객체에 값을 복사해감
let person3 = person // 얕은 복사 : 주소값만 가져감
let person4 = {...person, address:"Seoul"}
let person5 = {...person, name:"Jimin"}
console.log(person2)
console.log(person3)

console.log(person == person2)
console.log(person == person3)
console.log(person4)
console.log(person5)

let a = [1, 2]
let b = [...a, 3]
console.log(b)

let c = [...a, ...b]
console.log(c)