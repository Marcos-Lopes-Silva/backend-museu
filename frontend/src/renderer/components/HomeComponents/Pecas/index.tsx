import React from 'react';
import Button from '../../Button';
import { useNavigate } from 'react-router-dom';
import PecaImage from 'assets/predios.jpg';

export default function Pecas() {
  const navegar = useNavigate();
  const handleVerMais = () => {
    navegar('/pecas');
  };

  return (
    <div className="predios">
      <img className="img-predios" src={PecaImage} alt="Predios" />
      <div className="predios-first-rec">
        <div className="predios-second-rec"></div>
        <div className="predios-content">
          <div className="predios-tittles">
            <div className="predios-tittles-primary">Peças</div>
            <div className="predios-tittles-secondary">
              Peças existentes dentro do Museu Pampa
            </div>
          </div>
          <div className="button-predios-ver-mais">
            <div className="button-predios">
              <div className="button-divisoes">
                <Button
                  children="Ver mais"
                  className="button-predios-content-text"
                  onClick={handleVerMais}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
