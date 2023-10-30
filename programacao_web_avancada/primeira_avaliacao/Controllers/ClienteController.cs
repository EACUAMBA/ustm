using Microsoft.AspNetCore.Mvc;

namespace primeira_avaliacao.Controllers;

public class ClienteController : Controller
{
    // GET
    public IActionResult Index()
    {
        return View("Index");
    }
}