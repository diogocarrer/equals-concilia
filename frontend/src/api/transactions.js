export async function fetchTransactions(startDate, endDate) {
  const res = await fetch(
    `/api/transacoes?startDate=${startDate}&endDate=${endDate}`
  )
  if (!res.ok) {
    const text = await res.text()
    throw new Error(`Erro ${res.status}: ${text}`)
  }
  return res.json()
}