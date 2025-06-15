import { useState, useEffect } from 'react';
import { fetchTransactions } from '../api/transactions';
import Filter from '../components/Filter';
import TransactionsTable from '../components/TransactionsTable';
import styles from '../styles/ReportPage.module.css';
import { exportRelatorioCompleto } from '../utils/exportToPDF';

export default function ReportPage() {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [valorTotalMin, setValorTotalMin]     = useState('');
  const [valorTotalMax, setValorTotalMax]     = useState('');
  const [dataPagamento, setDataPagamento] = useState('');
  const [bandeiras, setBandeiras] = useState([]);
  const [bandeira, setBandeira] = useState('');
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
        dataPagamento,
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

  const handleClearFilters = () => {
    setStartDate('');
    setEndDate('');
    setValorTotalMin('');
    setValorTotalMax('');
    setDataPagamento('');
    setBandeira('');
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Relatório de Vendas</h1>
      <div className={styles.h2linkContainer}>
        <a
          href="http://localhost:8080/h2-console"
          target="_blank"
          rel="noopener noreferrer"
          className={styles.h2link}
        >
          Acessar Banco de Dados
        </a>
        <button
          onClick={() => exportRelatorioCompleto({
            transacoes: transactions
          })}
          className={styles.pdfButton}
        >
          Gerar PDF
        </button>
      </div>

      <Filter
        start={startDate}
        end={endDate}
        onStartChange={setStartDate}
        onEndChange={setEndDate}
        valorTotalMin={valorTotalMin}
        valorTotalMax={valorTotalMax}
        onValorTotalMinChange={setValorTotalMin}
        onValorTotalMaxChange={setValorTotalMax}
        dataPagamento={dataPagamento}
        onDataPagamentoChange={setDataPagamento}
        bandeira={bandeira}
        onBandeiraChange={setBandeira}
        bandeiras={bandeiras}
        onSearch={handleSearch}
        onClear={handleClearFilters}
      />

      {loading && <p className={styles.loading}>Carregando...</p>}
      {error && <p className={styles.error}>{error}</p>}

      {!loading && !error && (
        <TransactionsTable data={transactions} />
      )}
    </div>
  );
}
