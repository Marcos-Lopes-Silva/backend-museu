import React from 'react';
import './PecaComponent.style.css';
import iconCargo from 'assets/icons/cargo.png';
import iconTelefone from 'assets/icons/telefone.png';
import iconEndereco from 'assets/icons/endereco.png';
import { Link } from 'react-router-dom';
import TopBar from '../TopBar';
import { data } from 'autoprefixer';

type PecaComponentProps = {
  peca: Peca;
};

function PecaComponent({ peca }: PecaComponentProps) {
  console.log(peca);
  return (
    <div>
      <div className="funcionario-box">
        <div className="funcionario-content">
          <div className="info-funcionario">
            <div className="name-funcionario">{peca.nome}</div>
            <div className="div-dados">Descrição: {peca.descricao}</div>
            <div className="div-dados">
              <span>Autor: {peca.autor}</span>
            </div>
            <div className="div-dados">
              <span>Curador: {peca.curador}</span>
            </div>
            {/* <div className="div-dados">
              <span>Data de Aquisição: {peca.data_adquirida}</span>
            </div> */}
            {/* <div className="div-dados">
              <span>ID da Seção: {peca.secao}</span>
            </div>
            <div className="div-dados">
              <span>ID da Peça: {peca.id}</span>
            </div> */}
            <div className="div-dados">
              <span>Estado de conservação: {peca.estado_conservacao}</span>
            </div>
          </div>
          <div className="horizontal-bar">
            <hr />
          </div>
          <div>
            <Link to={`/secao/${peca.id}/editar`}>
              <button className="btn-editar">Editar informações</button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PecaComponent;
