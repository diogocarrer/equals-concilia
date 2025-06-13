import styles from './DateFilter.module.css';

export default function DateFilter({ start, end, onStartChange, onEndChange, onSearch }) {
  return (
    <form className={styles.form} onSubmit={e => { e.preventDefault(); onSearch(); }}>
      <label className={styles.label}>
        De:
        <input
          className={styles.input}
          type="date"
          value={start}
          onChange={e => onStartChange(e.target.value)}
          required
        />
      </label>
      <label className={styles.label}>
        Até:
        <input
          className={styles.input}
          type="date"
          value={end}
          onChange={e => onEndChange(e.target.value)}
          required
        />
      </label>
      <button type="submit" className={styles.button}>Filtrar</button>
    </form>
  );
}
