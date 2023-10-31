interface Endereco {
  rua: string;
  bairro: string;
  cep: string;
  cidade: string;
  estado: string;
  numero: string;
}

interface Funcionario {
  id: string;
  nome: string;
  email: string;
  senha: string;
  cpf: string;
  telefone: string;
  cargo: string;
  role: string;
  endereco: Endereco;
}
