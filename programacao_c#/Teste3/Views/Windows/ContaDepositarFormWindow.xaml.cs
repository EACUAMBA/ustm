using System.ComponentModel;
using System.Windows;
using Teste3.ViewModels;
using Wpf.Ui.Controls;
using System.Text.RegularExpressions;
using System.Windows.Input;

namespace Teste3.Views.Windows;

public partial class ContaDepositarFormWindow : UiWindow
{
    public ViewModels.ContaDepositarFormViewModel ViewModel { get; }

    public ContaDepositarFormWindow(
        ViewModels.ContaDepositarFormViewModel contaDepositarFormViewModel
    )
    {
        ViewModel = contaDepositarFormViewModel;
        
        DataContext = this;
        InitializeComponent();
    }

    private void ContaDepositarFormWindow_OnClosing(object? sender, CancelEventArgs e)
    {
        e.Cancel = true;
        this.Visibility = Visibility.Hidden;
    }

    private void ContaDepositarFormWindow_OnLoaded(object sender, RoutedEventArgs e)
    {
        ViewModel.InitializeViewModel();
    }

    private void ContaDepositarFormWindow_OnIsVisibleChanged(object sender, DependencyPropertyChangedEventArgs e)
    {
        ViewModel.InitializeViewModel();
    }
    
    
    private void NumberValidationTextBox(object sender, TextCompositionEventArgs e)
    {
        Regex regex = new Regex("[^0-9]+");
        e.Handled = regex.IsMatch(e.Text);
    }
}