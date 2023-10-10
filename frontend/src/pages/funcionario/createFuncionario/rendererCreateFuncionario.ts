import { IpcRenderer, ipcRenderer } from 'electron';
import * as path from 'path';

import Swal from 'sweetalert2';

// const Swal = require('sweetalert2');

const conteudo = document.getElementById('conteudo');

const inputFuncionarioName = document.querySelector(
  '#name',
) as HTMLSelectElement;
const inputFuncionarioEmail = document.querySelector(
  '#email',
) as HTMLSelectElement;
const inputFuncionarioSenha = document.querySelector(
  '#senha',
) as HTMLSelectElement;
const inputFuncionarioCpf = document.querySelector('#cpf') as HTMLSelectElement;
const inputFuncionarioRG = document.querySelector('#rg') as HTMLSelectElement;
const inputFuncionarioTelefone = document.querySelector(
  '#telefone',
) as HTMLSelectElement;
const inputFuncionarioSalario = document.querySelector(
  '#salario',
) as HTMLSelectElement;
const inputFuncionarioCargo = document.querySelector(
  '#cargo',
) as HTMLSelectElement;
const inputFuncionarioEnderecoRua = document.querySelector(
  '#rua',
) as HTMLSelectElement;
const inputFuncionarioEnderecoNumero = document.querySelector(
  '#numero',
) as HTMLSelectElement;
const inputFuncionarioBairro = document.querySelector(
  '#bairro',
) as HTMLSelectElement;
const inputFuncionarioCidade = document.querySelector(
  '#cidade',
) as HTMLSelectElement;
const inputFuncionarioEstado = document.querySelector(
  '#estado',
) as HTMLSelectElement;
const inputFuncionarioCEP = document.querySelector('#cep') as HTMLSelectElement;

const submitButton = document.querySelector('#submit');

const errorMessage = document.querySelector('#msg') as HTMLDivElement;

submitButton.addEventListener('click', async (e) => {
  e.preventDefault();

  const funcionarioName = inputFuncionarioName.value;
  const funcionarioEmail = inputFuncionarioEmail.value;
  const funcionarioSenha = inputFuncionarioSenha.value;
  const funcionarioCPF = inputFuncionarioCpf.value;
  const funcionarioRG = inputFuncionarioRG.value;
  const funcionarioTelefone = inputFuncionarioTelefone.value;
  const funcionarioSalario = inputFuncionarioSalario.value;
  const funcionarioCargo = inputFuncionarioCargo.value;
  const funcionarioEnderecoRua = inputFuncionarioEnderecoRua.value;
  const funcionarioEnderecoNumero = inputFuncionarioEnderecoNumero.value;
  const funcionarioBairro = inputFuncionarioBairro.value;
  const funcionarioCidade = inputFuncionarioCidade.value;
  const funcionarioEstado = inputFuncionarioEstado.value;
  const funcionarioCEP = inputFuncionarioCEP.value;

  if (
    funcionarioName === '' ||
    funcionarioEmail === '' ||
    funcionarioSenha === '' ||
    funcionarioCPF === '' ||
    funcionarioRG === '' ||
    funcionarioTelefone === '' ||
    funcionarioSalario === '' ||
    funcionarioCargo === '' ||
    funcionarioEnderecoRua === '' ||
    funcionarioEnderecoNumero === '' ||
    funcionarioBairro === '' ||
    funcionarioCidade === '' ||
    funcionarioEstado === '' ||
    funcionarioCEP === ''
  ) {
    errorMessage.textContent = 'É necessário preencher todos os campos!';
    errorMessage.classList.add('error-msg');

    setTimeout(() => {
      errorMessage.textContent = '';
      errorMessage.classList.remove();
    }, 3000);
    return;
  } else if (funcionarioCPF.length < 11) {
    errorMessage.textContent = 'CPF inválido';
    errorMessage.classList.add('error-msg');

    setTimeout(() => {
      errorMessage.textContent = '';
      errorMessage.classList.remove();
    }, 3000);
    return;
  } else if (!/@.*\.com/.test(funcionarioEmail)) {
    errorMessage.textContent = 'Email inválido';
    errorMessage.classList.add('error-msg');

    setTimeout(() => {
      errorMessage.textContent = '';
      errorMessage.classList.remove();
    }, 3000);
    return;
  }

  await postData('http://localhost:8080/funcionarios/novo', {
    nome: funcionarioName,
    email: funcionarioEmail,
    senha: funcionarioSenha,
    cpf: funcionarioCPF,
    rg: funcionarioRG,
    telefone: funcionarioTelefone,
    role: funcionarioCargo,
    rua: funcionarioEnderecoRua,
    numero: funcionarioEnderecoNumero,
    bairro: funcionarioBairro,
    cidade: funcionarioCidade,
    estado: funcionarioEstado,
    cep: funcionarioCEP,
  })
    .then((data) => {
      exibirSweetAlert();
    })
    .catch((err) => {
      Swal.fire({
        icon: 'error',
        title: 'Erro ao cadastrar funcionário',
        text: 'Usuário já existente',
      });
    });
});

async function postData(url = '', data = {}) {
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
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
    title: 'Funcionário cadastrado com sucesso',
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
