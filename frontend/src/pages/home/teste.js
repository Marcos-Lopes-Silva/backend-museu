console.log('hello')

const inputElement = document.getElementById('password')
const showPasswordElement = document.getElementById('show-password')

console.log(inputElement)
console.log(showPasswordElement)

showPasswordElement.addEventListener('click', () => {
    inputElement.setAttribute('type', 'text')
})