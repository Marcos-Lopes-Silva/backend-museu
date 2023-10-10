const { ipcRenderer } = require('electron');
const path = require('path');

const btnAdicionarFuncionario = document.querySelector('#btn-adc-funcionario');
btnAdicionarFuncionario.addEventListener('click', () => {
  openModal(
    'C:' +
      '\\' +
      'Users' +
      '\\' +
      'lucas' +
      '\\' +
      'OneDrive' +
      '\\' +
      'Área de Trabalho' +
      '\\' +
      'Git Lucas RPIV' +
      '\\' +
      'Sistema-de-Controle-de-Museu' +
      '\\' +
      'frontend' +
      '\\' +
      'src' +
      '\\' +
      'pages' +
      '\\' +
      'funcionario' +
      '\\' +
      'createFuncionario' +
      '\\' +
      'createFuncionario.html',
  );
});

function createFuncionarioElements(funcionario: any) {
  const funcionarioBox = document.createElement('div');
  funcionarioBox.classList.add('funcionario-box');

  const imgFuncionario = document.createElement('div');
  imgFuncionario.classList.add('img-funcionario');

  const funcionarioContent = document.createElement('div');
  funcionarioContent.classList.add('funcionario-content');

  funcionarioBox.appendChild(imgFuncionario);
  funcionarioBox.appendChild(funcionarioContent);

  const infoFuncionario = document.createElement('div');
  infoFuncionario.classList.add('info-funcionario');

  funcionarioContent.appendChild(infoFuncionario);

  const nameFuncionario = document.createElement('div');
  nameFuncionario.classList.add('name-funcionario');
  nameFuncionario.textContent = funcionario.nome;

  const cargoFuncionario = document.createElement('div');
  const textoCargo = document.createElement('span');

  if (funcionario.role) {
    textoCargo.textContent = ' ' + funcionario.role;
  } else {
    textoCargo.textContent = ' Cargo não informado';
  }
  const imgCargo = document.createElement('img');
  imgCargo.src =
    'C:' +
    '\\' +
    'Users' +
    '\\' +
    'lucas' +
    '\\' +
    'OneDrive' +
    '\\' +
    'Área de Trabalho' +
    '\\' +
    'Git Lucas RPIV' +
    '\\' +
    'Sistema-de-Controle-de-Museu' +
    '\\' +
    'frontend' +
    '\\' +
    'src' +
    '\\' +
    'pages' +
    '\\' +
    'funcionario' +
    '\\' +
    'listFuncionario' +
    '\\' +
    'images' +
    '\\' +
    'icons' +
    '\\' +
    'cargo.png';
  imgCargo.alt = '';
  imgCargo.id = 'img-endereco';

  const telefoneFuncionario = document.createElement('div');
  const textoTelefone = document.createElement('span');
  textoTelefone.textContent = ' ' + funcionario.telefone;
  const imgTelefone = document.createElement('img');
  imgTelefone.src =
    'C:' +
    '\\' +
    'Users' +
    '\\' +
    'lucas' +
    '\\' +
    'OneDrive' +
    '\\' +
    'Área de Trabalho' +
    '\\' +
    'Git Lucas RPIV' +
    '\\' +
    'Sistema-de-Controle-de-Museu' +
    '\\' +
    'frontend' +
    '\\' +
    'src' +
    '\\' +
    'pages' +
    '\\' +
    'funcionario' +
    '\\' +
    'listFuncionario' +
    '\\' +
    'images' +
    '\\' +
    'icons' +
    '\\' +
    'telefone.png';
  imgTelefone.alt = '';
  imgTelefone.id = 'img-endereco';

  const enderecoFuncionario = document.createElement('div');
  const imgEndereco = document.createElement('img');
  imgEndereco.src =
    'C:' +
    '\\' +
    'Users' +
    '\\' +
    'lucas' +
    '\\' +
    'OneDrive' +
    '\\' +
    'Área de Trabalho' +
    '\\' +
    'Git Lucas RPIV' +
    '\\' +
    'Sistema-de-Controle-de-Museu' +
    '\\' +
    'frontend' +
    '\\' +
    'src' +
    '\\' +
    'pages' +
    '\\' +
    'funcionario' +
    '\\' +
    'listFuncionario' +
    '\\' +
    'images' +
    '\\' +
    'icons' +
    '\\' +
    'endereco.png';
  imgEndereco.alt = '';
  imgEndereco.id = 'img-endereco';

  const textoEndereco = document.createElement('span');
  textoEndereco.textContent =
    ' ' +
    funcionario.endereco.rua +
    ' No.' +
    funcionario.endereco.numero +
    ' ' +
    funcionario.endereco.bairro +
    ', ' +
    funcionario.endereco.cidade;

  infoFuncionario.appendChild(nameFuncionario);
  cargoFuncionario.appendChild(imgCargo);
  cargoFuncionario.appendChild(textoCargo);
  infoFuncionario.appendChild(cargoFuncionario);
  telefoneFuncionario.appendChild(imgTelefone);
  telefoneFuncionario.appendChild(textoTelefone);
  infoFuncionario.appendChild(telefoneFuncionario);
  enderecoFuncionario.appendChild(imgEndereco);
  enderecoFuncionario.appendChild(textoEndereco);
  infoFuncionario.appendChild(enderecoFuncionario);

  const barraHorizontal = document.createElement('div');
  barraHorizontal.classList.add('horizontal-bar');
  barraHorizontal.innerHTML = '<hr>';
  funcionarioContent.appendChild(barraHorizontal);

  const btnEditar = document.createElement('div');
  funcionarioContent.appendChild(btnEditar);

  const buttonEditar = document.createElement('button');
  buttonEditar.classList.add('btn-editar');
  buttonEditar.textContent = 'Editar informações';
  btnEditar.appendChild(buttonEditar);

  return funcionarioBox;
}

// Use fetch para carregar o arquivo JSON
getData('http://localhost:8080/funcionarios')
  .then((data) => {
    const container = document.getElementById('funcionarios');

    if (!container) {
      throw new Error('Elemento "container" não encontrado no DOM');
    }

    container.innerHTML = '';

    data.content.forEach((funcionario: any, index: number) => {
      const funcionarioElement = createFuncionarioElements(funcionario);
      container.appendChild(funcionarioElement);
    });

    if (typeof data.numberOfElements !== 'undefined') {
      const quantidadeFuncionario = document.querySelector(
        '#qnt-funcionarios',
      ) as HTMLElement;
      quantidadeFuncionario.textContent =
        data.numberOfElements + ' funcionários';
    } else {
      console.warn('data.numberOfElements não está definido.');
    }
  })
  .catch((error) => {
    console.error('Erro:', error);
  });

async function getData(url = '') {
  const response = await fetch(url, {
    method: 'GET', // Altere para GET
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
      'Content-Type': 'application/json',
    },
    // Não inclua o corpo em uma solicitação GET
  });
  return response.json();
}

function openModal(filePath: string) {
  ipcRenderer.send('open-modal', filePath);
}
