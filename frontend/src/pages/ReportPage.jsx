import { useState, useEffect } from 'react';
import { fetchTransactions }      from '../api/transactions';
import DateFilter                 from '../components/DateFilter';
import TransactionsTable          from '../components/TransactionsTable';
import styles                     from './ReportPage.module.css';

export default function ReportPage() {
  const [startDate, setStartDate]       = useState('');
  const [endDate,   setEndDate]         = useState('');
  const [transactions, setTransactions] = useState([]);
  const [error, setError]               = useState('');
  const [loading, setLoading]           = useState(false);

  // 1) Carrega tudo assim que o componente monta
  useEffect(() => {
    (async () => {
      setLoading(true);
      try {
        const all = await fetchTransactions();
        setTransactions(all);
      } catch (error) {
        console.error(error);
        setError('Não foi possível carregar as transações');
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  // 2) Filtra por data quando o usuário submeter
  const handleSearch = async () => {
    setError('');
    setLoading(true);
    try {
      const list = await fetchTransactions(startDate, endDate);
      setTransactions(list);
    } catch (error) {
      console.error(error);
      setError('Erro ao filtrar transações');
      setTransactions([]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Relatório de Vendas</h1>

      <DateFilter
        start={startDate}
        end={endDate}
        onStartChange={setStartDate}
        onEndChange={setEndDate}
        onSearch={handleSearch}
      />

      {loading && <p className={styles.loading}>Carregando...</p>}
      {error   && <p className={styles.error}>{error}</p>}

      {!loading && !error && (
        <TransactionsTable data={transactions} />
      )}
    </div>
  );
}
