import axios from 'axios';

const api = axios.create({
  baseURL: '/api',
  timeout: 5000
});

export async function fetchTransactions(filters = {}) {
  const { data } = await api.get('/transacoes/filtradas', { params: filters });
  return data;
}