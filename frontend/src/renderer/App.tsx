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
import ListarFuncionario from './pages/funcionario/funcionario.listar';
import EditarFuncionario from './pages/funcionario/funcionario.editar';
import CadastrarFuncionario from './pages/funcionario/funcionario.cadastrar';
import CadastrarDivisao from './pages/divisao/divisao.cadastrar';
import ListarDivisao from './pages/divisao/divisao.listar';
import CadastrarSecao from './pages/secao/secao.cadastrar';
import ListarSecao from './pages/secao/secao.listar';
import ListarPecas from './pages/peca/peca.listar';
import CadastrarPeca from './pages/peca/peca.cadastrar';
import EditarSecao from './pages/secao/secao.editar';

export default function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/home" element={<Home />} />
          <Route path="/funcionarios" element={<ListarFuncionario />} />
          <Route
            path="/funcionarios/cadastrar"
            element={<CadastrarFuncionario />}
          />
          <Route
            path="/funcionarios/:id/editar"
            element={<EditarFuncionario />}
          />
          <Route path="/divisao" element={<ListarDivisao />} />
          <Route path="/divisao/cadastrar" element={<CadastrarDivisao />} />
          <Route path="/secao" element={<ListarSecao />} />
          <Route path="/secao/cadastrar" element={<CadastrarSecao />} />
          <Route path="/secao/:id/editar" element={<EditarSecao />} />
          <Route path="/pecas" element={<ListarPecas />} />
          <Route path="/pecas/cadastrar" element={<CadastrarPeca />} />
          <Route path="*" element={<Home />} />
        </Routes>
      </Router>
      <ToastContainer />
    </>
  );
}
