using CommunityToolkit.Mvvm.ComponentModel;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Runtime.CompilerServices;
using System.Windows;
using System.Windows.Media;
using CommunityToolkit.Mvvm.Input;
using Teste3.Models;
using Teste3.Services;
using Teste3.Views.Windows;
using Wpf.Ui.Common.Interfaces;
using Wpf.Ui.Controls.Navigation;

namespace Teste3.ViewModels
{
    public partial class ContaDepositarFormViewModel : ObservableObject, INavigationAware
    {
        private bool _isInitialized = false;

        [ObservableProperty] private Conta _conta;
        [ObservableProperty] private Double _valor;

        public ContaDepositarFormViewModel(
            ContaService contaService
        )
        {
            _contaService = contaService;
        }

        private ContaService _contaService;

        public void OnNavigatedTo()
        {
            if (!_isInitialized)
                InitializeViewModel();
        }

        public void OnNavigatedFrom()
        {
        }

        public void InitializeViewModel()
        {
        }

        [RelayCommand]
        private void OnDepositar()
        {
            _contaService.Depositar(this.Conta, this.Valor);
            this.Valor = 0;
        }
    }
}