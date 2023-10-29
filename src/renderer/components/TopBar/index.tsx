import { ChangeEvent, FormEvent, useState } from 'react';
import iconUnipampa from 'assets/icons/Museum.png';
import './index.css';

export default function TopBar() {
  return (
    <div className="top-nav">
      <div className="top-nav-left-content">
        <div>
          <a href="" className="a" id="paginitial">
            Página Inicial
          </a>
        </div>
        <div>
          <a href="" className="a">
            Gerenciar Museu
          </a>
        </div>
      </div>
      <div>
        <img src={iconUnipampa} alt="Unipampa" className="unipampa-logo" />
      </div>
      <div className="top-nav-right-content">
        <div>
          <a href="" className="a">
            Configurações
          </a>
        </div>
        <div>
          <a href="" className="a">
            Perfil
          </a>
        </div>
      </div>
    </div>
  );
}
