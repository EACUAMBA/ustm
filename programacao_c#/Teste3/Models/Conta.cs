using System;

namespace Teste3.Models;

public class Conta
{
    private String _numero = Guid.NewGuid().ToString();
    private String _titular = String.Empty;
    private Double _saldo = 0;
    private Boolean _definirSaldoInicial = false;
    private DateTime _dataDeCriacao = DateTime.Now;
    private DateTime? _dataDoUltimoMovimento;

    public string Numero
    {
        get => _numero;
        set => _numero = value ?? throw new ArgumentNullException(nameof(value));
    }

    public string Titular
    {
        get => _titular;
        set => _titular = value ?? throw new ArgumentNullException(nameof(value));
    }

    public double Saldo
    {
        get => _saldo;
        set => _saldo = value;
    }

    public DateTime DataDeCriacao
    {
        get => _dataDeCriacao;
        set => _dataDeCriacao = value;
    }

    public DateTime? DataDoUltimoMovimento
    {
        get => _dataDoUltimoMovimento;
        set => _dataDoUltimoMovimento = value;
    }

    public bool DefinirSaldoInicial
    {
        get => _definirSaldoInicial;
        set
        {
            this._saldo = 0;
            _definirSaldoInicial = value;
        }
    }
}