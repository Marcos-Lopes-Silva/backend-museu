import React from 'react';
import 'src/renderer/components/FuncionarioComponent/funcionarioComponent.style.css';
import iconCargo from 'assets/icons/cargo.png';
import iconTelefone from 'assets/icons/telefone.png';
import iconEndereco from 'assets/icons/endereco.png';
import iconDestino from 'assets/icons/destino.png';
import iconObjetivo from 'assets/icons/objetivo.png';
import iconDataInicio from 'assets/icons/data_inicio.png';
import iconDataFim from 'assets/icons/data_fim.png';
import iconCusto from 'assets/icons/custo.png';
import { Link } from 'react-router-dom';

type ViagensPesquisaComponentProps = {
  viagensPesquisa: ViagensPesquisa;
};

export default function ViagensPesquisaComponent({
  viagensPesquisa,
}: ViagensPesquisaComponentProps) {
  function formatarData(dataString: string): string {
    const data = new Date(dataString);

    const dia = String(data.getDate()).padStart(2, '0');
    const mes = String(data.getMonth() + 1).padStart(2, '0'); // Note que os meses são baseados em zero
    const ano = data.getFullYear();

    return `${dia}/${mes}/${ano}`;
  }

  return (
    <div className="funcionario-box">
      <div className="img-funcionario"></div>
      <div className="funcionario-content">
        <div className="info-funcionario">
          <div className="name-funcionario">{viagensPesquisa.pesquisador}</div>

          <div className="div-dados">
            <img src={iconObjetivo} alt="" id="img-endereco" />
            <span>{viagensPesquisa.objetivo}</span>
          </div>
          <div className="div-dados">
            <img src={iconDestino} alt="" id="img-endereco" />
            <span>{`${viagensPesquisa.destino}`}</span>
          </div>
          <div className="div-dados">
            <img src={iconCusto} alt="" id="img-endereco" />
            <span>{`${viagensPesquisa.custos},00`}</span>
          </div>
          <div className="div-dados">
            <img src={iconDataInicio} alt="" id="img-endereco" />
            <span>{`Data ida: ${formatarData(
              viagensPesquisa.data_inicio,
            )}`}</span>
          </div>
          <div className="div-dados">
            <img src={iconDataFim} alt="" id="img-endereco" />
            <span>{`Data volta: ${formatarData(
              viagensPesquisa.data_fim,
            )}`}</span>
          </div>
        </div>
        <div className="horizontal-bar">
          <hr />
        </div>
        <div>
          <Link
            to={`/viagenspesquisador/${viagensPesquisa.idPesquisador}/aprovar`}
          >
            <button className="btn-editar" disabled={viagensPesquisa.aprovada}>
              {viagensPesquisa.aprovada ? 'Viagem Aprovada' : 'Aprovar Viagem'}
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
}
