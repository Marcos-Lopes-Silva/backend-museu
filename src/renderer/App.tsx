import {
  MemoryRouter as Router,
  Routes,
  Route,
  useNavigate,
} from 'react-router-dom';
import './App.css';
import Login from './pages/login/Login';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Home from './pages/home/Home';
import ListarFuncionario from './pages/funcionario/listar/listarFuncionario';

export default function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<ListarFuncionario />} />
          <Route path="/login" element={<Login />} />
          <Route path="/home" element={<Home />} />
          <Route path="/listarfuncionarios" element={<ListarFuncionario />} />
          <Route path="*" element={<Home />} />
        </Routes>
      </Router>
      <ToastContainer />
    </>
  );
}
