import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { format, parseISO } from 'date-fns';

export function exportRelatorioCompleto({ transacoes }) {
  const doc = new jsPDF();
  const dataAtual = format(new Date(), 'dd/MM/yyyy HH:mm');

  doc.setFontSize(14);
  doc.text('Relatório de Transações', 14, 15);
  doc.setFontSize(10);
  doc.text(`Gerado em: ${dataAtual}`, 14, 22);

  autoTable(doc, {
    startY: 30,
    head: [[
      'Estabelecimento', 'Data Inicial', 'Data Evento', 'Previsão Pgto', 'Hora',
      'Total (R$)', 'Líquido (R$)', 'Parcelas', 'Bandeira'
    ]],
    body: transacoes.map(tx => [
      tx.estabelecimento,
      format(parseISO(tx.dataInicial), 'dd/MM/yyyy'),
      format(parseISO(tx.dataEvento), 'dd/MM/yyyy'),
      format(parseISO(tx.dataPrevistaPagamento), 'dd/MM/yyyy'),
      tx.horaEvento,
      Number(tx.valorTotal).toLocaleString('pt-BR', { minimumFractionDigits: 2 }),
      Number(tx.valorLiquido).toLocaleString('pt-BR', { minimumFractionDigits: 2 }),
      tx.quantidadeParcelas,
      tx.instituicaoBandeira
    ]),
  });

  doc.save('relatorio-transacoes.pdf');
}
