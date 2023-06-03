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

namespace Teste3.ViewModels
{
    public partial class ContasViewModel : ObservableObject, INavigationAware
    {
        private bool _isInitialized = false;
        private ContaFormWindow _contaFormWindow;
        private ContaDepositarFormWindow _contaDepositarFormWindow;
        private ContaLevantarFormWindow _contaLevantarFormWindow;
        
        
        [ObservableProperty]
        private Conta _selectedConta;

        public ContasViewModel(
            ContaService contaService, 
            ContaFormWindow contaFormWindow, 
            ContaDepositarFormWindow contaDepositarFormWindow, 
            ContaLevantarFormWindow contaLevantarFormWindow)
        {
            _contaService = contaService;
            _contaFormWindow = contaFormWindow;
            _contaDepositarFormWindow = contaDepositarFormWindow;
            _contaLevantarFormWindow = contaLevantarFormWindow;
        }

        private ContaService _contaService;

        [ObservableProperty]
        private ObservableCollection<Conta> _contas;

        public void OnNavigatedTo()
        {
            if (!_isInitialized)
                InitializeViewModel();
        }

        public void OnNavigatedFrom()
        {
        }

        private void InitializeViewModel()
        {
            _contas = new ObservableCollection<Conta>(_contaService.Contas);
            OnPropertyChanged(nameof(this.Contas));
        }

        [RelayCommand]
        private void OnRegistar()
        {
            this._contaFormWindow.ViewModel.Conta = new Conta();
            this._contaFormWindow.ViewModel.IsActualizar = false;
            this._contaFormWindow.ViewModel.IsNotActualizar = true;
            this._contaFormWindow.ViewModel.ShowActualizarButton = Visibility.Collapsed;
            this._contaFormWindow.ViewModel.ShowRegistarButton = Visibility.Visible;
            this._contaFormWindow.ShowDialog();
            this.InitializeViewModel();
        }
        
        [RelayCommand]
        private void OnActualizar(Conta conta)
        {
            this._contaFormWindow.ViewModel.Conta = this.SelectedConta;
            this._contaFormWindow.ViewModel.IsActualizar = true;
            this._contaFormWindow.ViewModel.IsNotActualizar = false;
            this._contaFormWindow.ViewModel.ShowActualizarButton = Visibility.Visible;
            this._contaFormWindow.ViewModel.ShowRegistarButton = Visibility.Collapsed;
            this._contaFormWindow.ShowDialog();
        }
        
        [RelayCommand]
        private void OnRemover(Conta conta)
        {
            this._contaFormWindow.ViewModel.Conta = this.SelectedConta;
            this._contaFormWindow.ShowDialog();
        }
        
        [RelayCommand]
        private void OnDepositar(Conta conta)
        {
            this._contaDepositarFormWindow.ViewModel.Conta = conta;
            this._contaDepositarFormWindow.ShowDialog();
            this.InitializeViewModel();
        }
        
        [RelayCommand]
        private void OnLevantar(Conta conta)
        {
            this._contaLevantarFormWindow.ViewModel.Conta = conta;
            this._contaLevantarFormWindow.ShowDialog();
            this.InitializeViewModel();
        }
    }
}
