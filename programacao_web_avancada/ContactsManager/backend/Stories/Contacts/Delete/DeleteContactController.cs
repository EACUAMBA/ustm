using System.Net.Mime;
using backend.Stories.Contacts.Create;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.Stories.Contacts.Delete;

[Authorize]
[ApiController]
[Route("api/contacts/{id}")]
public class DeleteContactController : ControllerBase
{

    private readonly DeleteContactService _deleteContactService;

    public DeleteContactController(DeleteContactService deleteContactService){
        this._deleteContactService = deleteContactService;
    }

    [HttpDelete]
    [Consumes(MediaTypeNames.Application.Json)]
    [Produces(MediaTypeNames.Application.Json)]
    public ActionResult Delete(int id)
    {
        this._deleteContactService.Delete(id);
        return StatusCode(200, "Contacto apagado com sucesso!");
    }
}