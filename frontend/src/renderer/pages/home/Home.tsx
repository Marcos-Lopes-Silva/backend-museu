import { useNavigate } from 'react-router-dom';
import Button from '../../components/ButtonPassword';

export default function Home() {
  const navegar = useNavigate();

  const handleClick = () => {
    localStorage.removeItem('token');
    navegar('/');
  };

  return (
    <section className="h-screen w-full bg-black">
      <div className="grid grid-rows-[64px,1fr]">
        <div className="flex items-center bg-green-700">
          <div className="flex items-center justify-between gap-1 w-full px-5">
            <span>Hello</span>
            <Button onClick={handleClick}>Deslogar</Button>
          </div>
        </div>
        <div className="w-full h-screen bg-blue-700"></div>
      </div>
    </section>
  );
}
