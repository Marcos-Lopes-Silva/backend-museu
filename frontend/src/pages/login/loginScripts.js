
const inputElement = document.getElementById('password')
const showPasswordElement = document.getElementById('show-password')
const imgShowPasswordElement = document.getElementById('img-showpassword')

console.log(imgShowPasswordElement)


showPasswordElement.addEventListener('click', () => {
    switch (inputElement.type) {
        case 'text':
            inputElement.setAttribute('type', 'password')
            imgShowPasswordElement.setAttribute('src', 'images/icons/icon-eye.png')
            break
        default:
            inputElement.setAttribute('type', 'text')
            imgShowPasswordElement.setAttribute('src', 'images/icons/icon-eyeclosed.png')
    }
})