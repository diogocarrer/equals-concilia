import React from 'react';
import {
  useReactTable,
  getCoreRowModel,
  flexRender
} from '@tanstack/react-table';
import { parseISO, format } from 'date-fns';
import styles from '../styles/TransactionsTable.module.css';

export default function TransactionsTable({ data }) {
  const columns = React.useMemo(() => [
    {
      header: 'Estabelecimento',
      accessorKey: 'estabelecimento'
    },
    {
      header: 'Data Inicial',
      accessorKey: 'dataInicial',
      cell: info => format(parseISO(info.getValue()), 'dd/MM/yyyy')
    },
    {
      header: 'Data Evento',
      accessorKey: 'dataEvento',
      cell: info => format(parseISO(info.getValue()), 'dd/MM/yyyy')
    },
    {
      header: 'Previsão Pgto',
      accessorKey: 'dataPrevistaPagamento',
      cell: info => format(parseISO(info.getValue()), 'dd/MM/yyyy')
    },
    {
      header: 'Hora',
      accessorKey: 'horaEvento'
    },
    {
      header: 'Total (R$)',
      accessorKey: 'valorTotal',
      cell: info =>
        Number(info.getValue()).toLocaleString('pt-BR', { minimumFractionDigits: 2 })
    },
    {
      header: 'Líquido (R$)',
      accessorKey: 'valorLiquido',
      cell: info =>
        Number(info.getValue()).toLocaleString('pt-BR', { minimumFractionDigits: 2 })
    },
    {
      header: 'Parcelas',
      accessorKey: 'quantidadeParcelas'
    },    
    {
      header: 'Bandeira',
      accessorKey: 'instituicaoBandeira'
    },
  ], []);

  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel()
  });

  if (!data || data.length === 0) {
    return <p className={styles.noData}>Nenhuma transação encontrada.</p>;
  }

  return (
    <div className={styles.tableContainer}>
      <table className={styles.table}>
        <thead>
          {table.getHeaderGroups().map(headerGroup => (
            <tr key={headerGroup.id}>
              {headerGroup.headers.map(header => (
                <th key={header.id}>
                  {flexRender(header.column.columnDef.header, header.getContext())}
                </th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody>
          {table.getRowModel().rows.map(row => (
            <tr key={row.id}>
              {row.getVisibleCells().map(cell => (
                <td key={cell.id}>
                  {flexRender(cell.column.columnDef.cell, cell.getContext())}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
