import axios from 'axios';

const api = axios.create({
  baseURL: '/api',
  timeout: 5000
});

export async function fetchTransactions(startDate, endDate) {
  const params = {};
  if (startDate && endDate) {
    params.startDate = startDate;
    params.endDate   = endDate;
  }
  const { data } = await api.get('/transacoes', { params });
  return data;
}
