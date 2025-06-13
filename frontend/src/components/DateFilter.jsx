export default function DateFilter({ start, end, onStartChange, onEndChange, onSearch }) {
  return (
    <form onSubmit={e => { e.preventDefault(); onSearch() }}>
      <label>
        De:&nbsp;
        <input
          type="date"
          value={start}
          onChange={e => onStartChange(e.target.value)}
          required
        />
      </label>
      <label style={{ marginLeft: 10 }}>
        Até:&nbsp;
        <input
          type="date"
          value={end}
          onChange={e => onEndChange(e.target.value)}
          required
        />
      </label>
      <button type="submit" style={{ marginLeft: 10 }}>Filtrar</button>
    </form>
  )
}