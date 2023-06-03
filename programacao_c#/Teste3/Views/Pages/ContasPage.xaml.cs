using Wpf.Ui.Common.Interfaces;

namespace Teste3.Views.Pages
{
    /// <summary>
    /// Interaction logic for DataView.xaml
    /// </summary>
    public partial class ContasPage : INavigableView<ViewModels.ContasViewModel>
    {
        public ViewModels.ContasViewModel ViewModel
        {
            get;
        }

        public ContasPage(ViewModels.ContasViewModel viewModel)
        {
            ViewModel = viewModel;

            InitializeComponent();
        }
    }
}
