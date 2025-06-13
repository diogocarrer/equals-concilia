export default function TransactionsTable({ data }) {
  if (!data || data.length === 0) {
    return <p>Nenhuma transação encontrada.</p>
  }

  return (
    <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: 20 }}>
      <thead>
        <tr>
          <th>Data</th>
          <th>Hora</th>
          <th>Total</th>
          <th>Líquido</th>
        </tr>
      </thead>
      <tbody>
        {data.map(tx => (
          <tr key={tx.codigoTransacao}>
            <td>{tx.dataEvento}</td>
            <td>{tx.horaEvento}</td>
            <td>{tx.valorTotal.toFixed(2)}</td>
            <td>{tx.valorLiquido.toFixed(2)}</td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}