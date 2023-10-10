import { IpcRenderer, ipcRenderer } from 'electron';
import * as path from 'path';

import Swal from 'sweetalert2';

const conteudo = document.getElementById('conteudo');

const inputUsernameElement = document.querySelector(
  '#username',
) as HTMLSelectElement;
const inputPasswordElement = document.querySelector(
  '#password',
) as HTMLSelectElement;
const showPasswordElement = document.querySelector('#show-password');
const imgShowPasswordElement = document.querySelector('#img-showpassword');
const submitButton = document.querySelector('#submit');

const errorMessage = document.querySelector('#msg') as HTMLDivElement;

showPasswordElement.addEventListener('click', () => {
  switch (inputPasswordElement.getAttribute('type')) {
    case 'text':
      inputPasswordElement.setAttribute('type', 'password');
      imgShowPasswordElement.setAttribute('src', 'images/icons/icon-eye.png');
      break;
    default:
      inputPasswordElement.setAttribute('type', 'text');
      imgShowPasswordElement.setAttribute(
        'src',
        'images/icons/icon-eyeclosed.png',
      );
  }
});

submitButton.addEventListener('click', async (e) => {
  e.preventDefault();

  const usernameValue = inputUsernameElement.value;
  const passwordValue = inputPasswordElement.value;
  console.log(usernameValue, passwordValue);

  if (usernameValue === '' || passwordValue === '') {
    errorMessage.textContent = 'É necessário preencher os campos!';
    errorMessage.classList.add('error-msg');

    setTimeout(() => {
      errorMessage.textContent = '';
      errorMessage.classList.remove();
    }, 3000);
    return;
  }

  await postData('http://localhost:8080/login', {
    login: usernameValue,
    senha: passwordValue,
  })
    .then((data) => {
      localStorage.setItem('token', data.token);
      exibirSweetAlert();

      // alert('Login realizado com sucesso');
      // ipcRenderer.send('trocar-conteudo', '../src/pages/home/home.html');
      // ipcRenderer.on('conteudo-trocado', (event, novoConteudo) => {
      //   conteudo.innerHTML = novoConteudo;
      // });
    })
    .catch((err) => {
      errorMessage.textContent = 'Email ou senha inválidos!';
      errorMessage.classList.add('error-msg');

      setTimeout(() => {
        errorMessage.textContent = '';
        errorMessage.classList.remove();
      }, 3000);
    });
});

async function postData(url = '', data = {}) {
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });
  return response.json();
}

async function exibirSweetAlert() {
  await Swal.fire({
    position: 'center',
    icon: 'success',
    title: 'Login realizado com sucesso',
    showConfirmButton: false,
    timer: 1600,
    heightAuto: false,
  });

  await Swal.close();

  ipcRenderer.send(
    'trocar-conteudo',
    '../src/pages/funcionario/listFuncionario/listFuncionario.html',
  );
  ipcRenderer.on('conteudo-trocado', (event, novoConteudo) => {
    conteudo.innerHTML = novoConteudo;
  });
}
