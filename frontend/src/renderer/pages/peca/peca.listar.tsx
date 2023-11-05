import React, { useEffect, useState } from 'react';
import TopBar from '../../components/TopBar';
import { api } from '../../api/api';
import { useNavigate } from 'react-router-dom';
import Button from '../../components/Button';
import PecaComponent from '../../components/PecaComponent';

export default function ListarPecas() {
  // Inicializa o estado com um objeto contendo uma propriedade 'content' que é um array de Funcionario
  const [data, setData] = useState<{ content: Peca[] }>({ content: [] });

  const navegar = useNavigate();

  const handleCadastrar = () => {
    navegar('/pecas/cadastrar');
  };

  useEffect(() => {
    // Faz uma chamada para a API para obter dados de funcionários
    api
      .get('/pecas')
      .then((response) => {
        // Atualiza o estado com os dados recebidos da API
        setData(response.data);
        console.log(data);
      })
      .catch((error) => {
        console.error('Erro ao buscar dados:', error);
      });
  }, []);

  return (
    <div>
      <TopBar />

      <div className="div-btn">
        <Button
          className="btn-adc-funcionario"
          children="Adicionar Peça"
          onClick={handleCadastrar}
        ></Button>
      </div>

      <div>
        <div id="funcionarios" className="list-funcionarios">
          {data.content.map((peca, index) => (
            <PecaComponent key={index} peca={peca} />
          ))}
        </div>
      </div>
    </div>
  );
}
