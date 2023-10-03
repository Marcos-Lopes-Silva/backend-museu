import { IpcRenderer, ipcRenderer } from 'electron';
import * as path from 'path';

const conteudo = document.getElementById('conteudo');

const inputFuncionarioName = document.querySelector(
  '#name',
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

const submitButton = document.querySelector('#submit');

const errorMessage = document.querySelector('#msg') as HTMLDivElement;

submitButton.addEventListener('click', async (e) => {
  e.preventDefault();

  const funcionarioName = inputFuncionarioName.value;
  const funcionarioCPF = inputFuncionarioCpf.value;
  const funcionarioRG = inputFuncionarioRG.value;
  const funcionarioTelefone = inputFuncionarioTelefone.value;
  const funcionarioSalario = inputFuncionarioSalario.value;
  const funcionarioCargo = inputFuncionarioCargo.value;

  if (
    funcionarioName === '' ||
    funcionarioCPF === '' ||
    funcionarioRG === '' ||
    funcionarioTelefone === '' ||
    funcionarioSalario === '' ||
    funcionarioCargo === ''
  ) {
    errorMessage.textContent = 'É necessário preencher todos os campos!';
    errorMessage.classList.add('error-msg');

    setTimeout(() => {
      errorMessage.textContent = '';
      errorMessage.classList.remove();
    }, 3000);
    return;
  }

  await postData('http://localhost:8080/funcionarios/novo', {
    nome: funcionarioName,
    // email: ,
    cpf: funcionarioCPF,
    rg: funcionarioRG,
    telefone: funcionarioTelefone,
    cargo: funcionarioCargo,
  })
    .then((data) => {})
    .catch((err) => {});
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
