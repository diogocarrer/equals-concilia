import styles from '../styles/Filter.module.css';

export default function Filter({
  start, end, onStartChange, onEndChange,
  valorTotalMin, valorTotalMax, onValorTotalMinChange, onValorTotalMaxChange,
  dataPagamento, onDataPagamentoChange,
  bandeira, onBandeiraChange, bandeiras,
  onSearch, onClear 
}) {
  return (
    <form className={styles.form} onSubmit={e => { e.preventDefault(); onSearch(); }}>
      <div className={styles.row}>
        <label className={styles.label}>
          De:
          <input className={styles.input} type="date" value={start} onChange={e => onStartChange(e.target.value)} />
        </label>
        <label className={styles.label}>
          Até:
          <input className={styles.input} type="date" value={end} onChange={e => onEndChange(e.target.value)} />
        </label>
        <label className={styles.label}>
          Data de Pagamento:
          <input className={styles.input} type="date" value={dataPagamento} onChange={e => onDataPagamentoChange(e.target.value)} />
        </label>
      </div>

      <div className={styles.row}>
        <label className={styles.label}>
          Valor Total (Min):
          <input className={styles.input} type="text" placeholder="Ex: 100,50" value={valorTotalMin} onChange={e => onValorTotalMinChange(e.target.value)} />
        </label>
        <label className={styles.label}>
          Valor Total (Max):
          <input className={styles.input} type="text" placeholder="Ex: 100,50" value={valorTotalMax} onChange={e => onValorTotalMaxChange(e.target.value)} />
        </label>
        <label className={styles.label}>
          Bandeira:
          <select className={styles.input} value={bandeira} onChange={e => onBandeiraChange(e.target.value)}>
            <option value="">Todas</option>
            {bandeiras.map((b, i) => (
              <option key={i} value={b}>{b}</option>
            ))}
          </select>
        </label>
      </div>

      <div className={styles.buttonContainer}>
        <button type="submit" className={styles.button}>Filtrar</button>
        <button type="button" className={styles.buttonClear} onClick={onClear}>Limpar Filtros</button>
      </div>
    </form>
  );
}