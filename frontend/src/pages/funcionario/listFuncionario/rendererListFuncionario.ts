// getData('http://localhost:8080/funcionarios')
//   .then((data) => {
//     console.log(data.content);
//     data.content.array.forEach(funcionario => {

//     });
//   })
//   .catch((error) => {
//     console.error('Erro:', error);
//   });

// async function getData(url = '') {
//   const response = await fetch(url, {
//     method: 'GET', // Altere para GET
//     headers: {
//       Authorization: `Bearer ${localStorage.getItem('token')}`,
//       'Content-Type': 'application/json',
//     },
//     // Não inclua o corpo em uma solicitação GET
//   });
//   return response.json();
// }
