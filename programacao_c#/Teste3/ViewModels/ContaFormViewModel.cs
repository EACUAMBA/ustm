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
    public partial class ContaFormViewModel : ObservableObject, INavigationAware
    {
        private bool _isInitialized = false;
        [ObservableProperty] private bool _isActualizar = false;
        [ObservableProperty] private bool _isNotActualizar = true;
        [ObservableProperty] private Visibility _showRegistarButton = Visibility.Visible;
        [ObservableProperty] private Visibility _showActualizarButton = Visibility.Collapsed;

        [ObservableProperty] private Conta _conta;

        public ContaFormViewModel(
            ContaService contaService
        )
        {
            _contaService = contaService;
        }

        private ContaService _contaService;

        [ObservableProperty] private ObservableCollection<Conta> _contas;

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
            _contas = new ObservableCollection<Conta>(_contaService.Contas);
            OnPropertyChanged(nameof(this.Contas));
        }

        [RelayCommand]
        private void OnRegistar()
        {
            _contaService.AdicionarConta(this.Conta);
            this.Conta = new();
        }

        [RelayCommand]
        private void OnActualizar()
        {
            _contaService.ActualizarConta(this.Conta);
            this.ShowActualizarButton = Visibility.Visible;
            this.ShowRegistarButton = Visibility.Collapsed;
        }
    }
}