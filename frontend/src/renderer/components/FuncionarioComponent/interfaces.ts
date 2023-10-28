interface Endereco {
  rua: string;
  bairro: string;
  cep: string;
  cidade: string;
  estado: string;
  numero: number;
}

interface Funcionario {
  nome: string;
  email: string;
  senha: string;
  cpf: string;
  telefone: string;
  cargo: string;
  role: string;
  endereco: Endereco;
}
