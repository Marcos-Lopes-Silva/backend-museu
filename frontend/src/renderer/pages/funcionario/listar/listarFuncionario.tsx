import React, { useEffect, useState } from 'react';
import FuncionarioComponent from '../../../components/FuncionarioComponent';
import TopBar from '../../../components/TopBar';
import { api } from '../../../api/api';

export default function ListarFuncionario() {
  // Inicializa o estado com um objeto contendo uma propriedade 'content' que é um array de Funcionario
  const [data, setData] = useState<{ content: Funcionario[] }>({ content: [] });

  useEffect(() => {
    // Faz uma chamada para a API para obter dados de funcionários
    api
      .get('http://localhost:8080/funcionarios')
      .then((response) => {
        // Atualiza o estado com os dados recebidos da API
        setData(response.data);
      })
      .catch((error) => {
        console.error('Erro ao buscar dados:', error);
      });
  }, []);

  return (
    <div>
      <TopBar />

      <div className="options-pages">
        <div>
          <div>Funcionários</div>
          <div id="qnt-funcionarios" className="sub-info">
            127 funcionários
          </div>
        </div>
        <div>
          <div>Viagens Pesquisadores</div>
          <div className="sub-info">18 viagens</div>
        </div>
      </div>

      <div className="div-btn">
        <button id="btn-adc-funcionario" className="btn-adc-funcionario">
          Adicionar funcionário
        </button>
      </div>

      <div id="funcionarios" className="list-funcionarios">
        {data.content.map((funcionario, index) => (
          // Renderiza o componente FuncionarioComponent para cada funcionário
          <FuncionarioComponent key={index} funcionario={funcionario} />
        ))}
      </div>
    </div>
  );
}
