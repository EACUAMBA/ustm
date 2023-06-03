using System.ComponentModel;
using System.Windows;
using Teste3.ViewModels;
using Wpf.Ui.Controls;
using System.Text.RegularExpressions;
using System.Windows.Input;

namespace Teste3.Views.Windows;

public partial class ContaLevantarFormWindow : UiWindow
{
    public ViewModels.ContaLevantarFormViewModel ViewModel { get; }

    public ContaLevantarFormWindow(
        ViewModels.ContaLevantarFormViewModel contaDepositarFormViewModel
    )
    {
        ViewModel = contaDepositarFormViewModel;
        
        DataContext = this;
        InitializeComponent();
    }

    private void ContaLevantarFormViewModel_OnClosing(object? sender, CancelEventArgs e)
    {
        e.Cancel = true;
        this.Visibility = Visibility.Hidden;
    }

    private void ContaLevantarFormViewModel_OnLoaded(object sender, RoutedEventArgs e)
    {
        ViewModel.InitializeViewModel();
    }

    private void ContaLevantarFormViewModel_OnIsVisibleChanged(object sender, DependencyPropertyChangedEventArgs e)
    {
        ViewModel.InitializeViewModel();
    }
    
    
    private void NumberValidationTextBox(object sender, TextCompositionEventArgs e)
    {
        Regex regex = new Regex("[^0-9]+");
        e.Handled = regex.IsMatch(e.Text);
    }
}