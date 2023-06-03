using System;
using System.Collections.Generic;
using Teste3.Models;

namespace Teste3.Services;

public class ContaService
{
    private readonly List<Conta> _contas = new();

    public List<Conta> Contas
    {
        get
        {
            return _contas;
        }
    }

    public void AdicionarConta(Conta conta)
    {
        this.Contas.Add(conta);
    }

    public void ActualizarConta(Conta conta)
    {
        Conta myAccount = this.Contas.Find(c => c.Numero.Equals(conta.Numero))!;
    }
    
    public void ApagarConta(Conta conta)
    {
        Conta myAccount = this.Contas.Find(c => c.Numero.Equals(conta.Numero))!;
        this.Contas.Remove(myAccount);
    }
    
    public void Levantar(Conta conta, Double valor)
    {
        Conta myAccount = this.Contas.Find(c => c.Numero.Equals(conta.Numero))!;
        myAccount.Saldo -= (valor + 5);
    }
    
    public void Depositar(Conta conta, Double valor)
    {
        Conta myAccount = this.Contas.Find(c => c.Numero.Equals(conta.Numero))!;
        myAccount.Saldo += valor;
    }
}