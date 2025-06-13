package com.equals.concilia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transacao {private String codigoRegistro;
    private String estabelecimento;
    private LocalDate dataInicial;
    private LocalDate dataEvento;
    private LocalTime horaEvento;
    private String tipoEvento;
    private String tipoTransacao;
    private String numeroSerieLeitor;
    private String codigoTransacao;
    private String codigoPedido;
    private BigDecimal valorTotal;
    private BigDecimal valorParcelaOuLiquido;
    private String pagamento;
    private String plano;
    private String parcela;
    private int quantidadeParcelas;
    private LocalDate dataPrevistaPagamento;
    private BigDecimal taxaParcelamentoComprador;
    private BigDecimal tarifaBoletoComprador;
    private BigDecimal valorOriginal;
    private BigDecimal taxaParcelamentoVendedor;
    private BigDecimal taxaIntermediacao;
    private BigDecimal tarifaIntermediacao;
    private BigDecimal tarifaBoletoVendedor;
    private BigDecimal repasseAplicacao;
    private BigDecimal valorLiquido;
    private String statusPagamento;
    private String filler;
    private String meioPagamento;
    private String instituicaoBandeira;
    private String canalEntrada;
    private String leitor;
    private String meioCaptura;
    private String numeroLogico;
    private String nsu;
    private String filler2;
    private String cartaoBin;
    private String cartaoHolder;
    private String codigoAutorizacao;
    private String codigoCV;
    private String reservado;

    public static Transacao fromLine(String linha) {
        if (linha.length() < 392) {
            linha = String.format("%-392s", linha);
        }
        DateTimeFormatter dateFmt = DateTimeFormatter.BASIC_ISO_DATE;
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HHmmss");
        Transacao t = new Transacao();

        t.setCodigoRegistro(linha.substring(0, 1));
        t.setEstabelecimento(linha.substring(1, 11).trim());
        t.setDataInicial(LocalDate.parse(linha.substring(11, 19), dateFmt));
        t.setDataEvento(LocalDate.parse(linha.substring(19, 27), dateFmt));
        t.setHoraEvento(LocalTime.parse(linha.substring(27, 33), timeFmt));
        t.setTipoEvento(linha.substring(33, 35));
        t.setTipoTransacao(linha.substring(35, 37));
        t.setNumeroSerieLeitor(linha.substring(37, 45).trim());
        t.setCodigoTransacao(linha.substring(45, 77).trim());
        t.setCodigoPedido(linha.substring(78, 98).trim());
        t.setValorTotal(new BigDecimal(linha.substring(97, 110).trim()).movePointLeft(2));
        t.setValorParcelaOuLiquido(new BigDecimal(linha.substring(110, 123).trim()).movePointLeft(2));
        t.setPagamento(linha.substring(123, 124));
        t.setPlano(linha.substring(124, 126));
        t.setParcela(linha.substring(126, 128));
        t.setQuantidadeParcelas(Integer.parseInt(linha.substring(128, 130).trim()));
        t.setDataPrevistaPagamento(LocalDate.parse(linha.substring(130, 138), dateFmt));
        t.setTaxaParcelamentoComprador(new BigDecimal(linha.substring(138, 151).trim()).movePointLeft(2));
        t.setTarifaBoletoComprador(new BigDecimal(linha.substring(151, 164).trim()).movePointLeft(2));
        t.setValorOriginal(new BigDecimal(linha.substring(164, 177).trim()).movePointLeft(2));
        t.setTaxaParcelamentoVendedor(new BigDecimal(linha.substring(177, 190).trim()).movePointLeft(2));
        t.setTaxaIntermediacao(new BigDecimal(linha.substring(190, 203).trim()).movePointLeft(2));
        t.setTarifaIntermediacao(new BigDecimal(linha.substring(203, 216).trim()).movePointLeft(2));
        t.setTarifaBoletoVendedor(new BigDecimal(linha.substring(216, 229).trim()).movePointLeft(2));
        t.setRepasseAplicacao(new BigDecimal(linha.substring(229, 242).trim()).movePointLeft(2));
        t.setValorLiquido(new BigDecimal(linha.substring(242, 255).trim()).movePointLeft(2));
        t.setStatusPagamento(linha.substring(255, 257));
        t.setFiller(linha.substring(257, 259));
        t.setMeioPagamento(linha.substring(259, 261));
        t.setInstituicaoBandeira(linha.substring(261, 291).trim());
        t.setCanalEntrada(linha.substring(291, 293));
        t.setLeitor(linha.substring(293, 295).trim());
        t.setMeioCaptura(linha.substring(295, 297).trim());
        t.setNumeroLogico(linha.substring(297, 329).trim());
        t.setNsu(linha.substring(329, 340).trim());
        t.setFiller2(linha.substring(340, 343));
        t.setCartaoBin(linha.substring(343, 349));
        t.setCartaoHolder(linha.substring(349, 353));
        t.setCodigoAutorizacao(linha.substring(353, 359));
        t.setCodigoCV(linha.substring(359, 392).trim());
        t.setReservado(linha.length() > 392 ? linha.substring(392).trim() : "");

        return t;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getCanalEntrada() {
        return canalEntrada;
    }

    public void setCanalEntrada(String canalEntrada) {
        this.canalEntrada = canalEntrada;
    }

    public String getCartaoBin() {
        return cartaoBin;
    }

    public void setCartaoBin(String cartaoBin) {
        this.cartaoBin = cartaoBin;
    }

    public String getCartaoHolder() {
        return cartaoHolder;
    }

    public void setCartaoHolder(String cartaoHolder) {
        this.cartaoHolder = cartaoHolder;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }

    public String getCodigoCV() {
        return codigoCV;
    }

    public void setCodigoCV(String codigoCV) {
        this.codigoCV = codigoCV;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getCodigoTransacao() {
        return codigoTransacao;
    }

    public void setCodigoTransacao(String codigoTransacao) {
        this.codigoTransacao = codigoTransacao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataPrevistaPagamento() {
        return dataPrevistaPagamento;
    }

    public void setDataPrevistaPagamento(LocalDate dataPrevistaPagamento) {
        this.dataPrevistaPagamento = dataPrevistaPagamento;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public LocalTime getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(LocalTime horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getInstituicaoBandeira() {
        return instituicaoBandeira;
    }

    public void setInstituicaoBandeira(String instituicaoBandeira) {
        this.instituicaoBandeira = instituicaoBandeira;
    }

    public String getLeitor() {
        return leitor;
    }

    public void setLeitor(String leitor) {
        this.leitor = leitor;
    }

    public String getMeioCaptura() {
        return meioCaptura;
    }

    public void setMeioCaptura(String meioCaptura) {
        this.meioCaptura = meioCaptura;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public String getNsu() {
        return nsu;
    }

    public void setNsu(String nsu) {
        this.nsu = nsu;
    }

    public String getNumeroLogico() {
        return numeroLogico;
    }

    public void setNumeroLogico(String numeroLogico) {
        this.numeroLogico = numeroLogico;
    }

    public String getNumeroSerieLeitor() {
        return numeroSerieLeitor;
    }

    public void setNumeroSerieLeitor(String numeroSerieLeitor) {
        this.numeroSerieLeitor = numeroSerieLeitor;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public BigDecimal getRepasseAplicacao() {
        return repasseAplicacao;
    }

    public void setRepasseAplicacao(BigDecimal repasseAplicacao) {
        this.repasseAplicacao = repasseAplicacao;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public BigDecimal getTarifaBoletoComprador() {
        return tarifaBoletoComprador;
    }

    public void setTarifaBoletoComprador(BigDecimal tarifaBoletoComprador) {
        this.tarifaBoletoComprador = tarifaBoletoComprador;
    }

    public BigDecimal getTarifaBoletoVendedor() {
        return tarifaBoletoVendedor;
    }

    public void setTarifaBoletoVendedor(BigDecimal tarifaBoletoVendedor) {
        this.tarifaBoletoVendedor = tarifaBoletoVendedor;
    }

    public BigDecimal getTarifaIntermediacao() {
        return tarifaIntermediacao;
    }

    public void setTarifaIntermediacao(BigDecimal tarifaIntermediacao) {
        this.tarifaIntermediacao = tarifaIntermediacao;
    }

    public BigDecimal getTaxaIntermediacao() {
        return taxaIntermediacao;
    }

    public void setTaxaIntermediacao(BigDecimal taxaIntermediacao) {
        this.taxaIntermediacao = taxaIntermediacao;
    }

    public BigDecimal getTaxaParcelamentoComprador() {
        return taxaParcelamentoComprador;
    }

    public void setTaxaParcelamentoComprador(BigDecimal taxaParcelamentoComprador) {
        this.taxaParcelamentoComprador = taxaParcelamentoComprador;
    }

    public BigDecimal getTaxaParcelamentoVendedor() {
        return taxaParcelamentoVendedor;
    }

    public void setTaxaParcelamentoVendedor(BigDecimal taxaParcelamentoVendedor) {
        this.taxaParcelamentoVendedor = taxaParcelamentoVendedor;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorParcelaOuLiquido() {
        return valorParcelaOuLiquido;
    }

    public void setValorParcelaOuLiquido(BigDecimal valorParcelaOuLiquido) {
        this.valorParcelaOuLiquido = valorParcelaOuLiquido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
