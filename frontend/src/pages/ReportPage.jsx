import { useState, useEffect } from 'react'
import { fetchTransactions } from '../api/transactions'
import DateFilter                from '../components/DateFilter'
import TransactionsTable         from '../components/TransactionsTable'

export default function ReportPage() {
  const today = new Date().toISOString().slice(0, 10)

  const [startDate, setStartDate]       = useState(today)
  const [endDate,   setEndDate]         = useState(today)
  const [transactions, setTransactions] = useState([])
  const [error, setError]               = useState('')

  const handleSearch = async () => {
    try {
      setError('')
      const list = await fetchTransactions(startDate, endDate)
      setTransactions(list)
    } catch (err) {
      setError(err.message)
      setTransactions([])
    }
  }

  useEffect(() => {
    handleSearch()
  }, [])

  return (
    <div style={{ padding: 20 }}>
      <h1>Relatório de Vendas</h1>

      <DateFilter
        start={startDate}
        end={endDate}
        onStartChange={setStartDate}
        onEndChange={setEndDate}
        onSearch={handleSearch}
      />

      {error && <p style={{ color: 'red' }}>{error}</p>}

      <TransactionsTable data={transactions} />
    </div>
  )
}