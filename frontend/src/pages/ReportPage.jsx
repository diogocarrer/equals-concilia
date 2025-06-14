import { useState, useEffect } from 'react';
import { fetchTransactions } from '../api/transactions';
import Filter from '../components/Filter';
import TransactionsTable from '../components/TransactionsTable';
import styles from './ReportPage.module.css';

export default function ReportPage() {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [valorTotalMin, setValorTotalMin]     = useState('');
  const [valorTotalMax, setValorTotalMax]     = useState('');
  const [valorLiquidoMin, setValorLiquidoMin] = useState('');
  const [valorLiquidoMax, setValorLiquidoMax] = useState('');
  const [bandeiras, setBandeiras] = useState([]);
  const [bandeira, setBandeira] = useState([]);
  const [transactions, setTransactions] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    (async () => {
      setLoading(true);
      try {
        const all = await fetchTransactions();
        setTransactions(all);
        const únicas = [...new Set(all.map(tx => tx.instituicaoBandeira).filter(Boolean))];
        setBandeiras(únicas.sort());
      } catch (error) {
        console.error(error);
        setError('Não foi possível carregar as transações');
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  const handleSearch = async () => {
    setError('');
    setLoading(true);

    const parseToNumber = (value) => {
      const parsed = value?.replace(',', '.');
      const num = parseFloat(parsed);
      return isNaN(num) ? undefined : num;
    };

    try {
      const list = await fetchTransactions({
        startDate,
        endDate,
        valorTotalMin: parseToNumber(valorTotalMin),
        valorTotalMax: parseToNumber(valorTotalMax),
        valorLiquidoMin: parseToNumber(valorLiquidoMin),
        valorLiquidoMax: parseToNumber(valorLiquidoMax),
        bandeira
      });

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

      <Filter
        start={startDate}
        end={endDate}
        onStartChange={setStartDate}
        onEndChange={setEndDate}
        valorTotalMin={valorTotalMin}
        valorTotalMax={valorTotalMax}
        onValorTotalMinChange={setValorTotalMin}
        onValorTotalMaxChange={setValorTotalMax}
        valorLiquidoMin={valorLiquidoMin}
        valorLiquidoMax={valorLiquidoMax}
        onValorLiquidoMinChange={setValorLiquidoMin}
        onValorLiquidoMaxChange={setValorLiquidoMax}
        bandeira={bandeira}
        onBandeiraChange={setBandeira}
        bandeiras={bandeiras}
        onSearch={handleSearch}
      />

      {loading && <p className={styles.loading}>Carregando...</p>}
      {error && <p className={styles.error}>{error}</p>}

      {!loading && !error && (
        <TransactionsTable data={transactions} />
      )}
    </div>
  );
}
