using System.Net.Mime;
using Microsoft.AspNetCore.Mvc;

namespace Stories.Contacts.Create;

[ApiController]
[Route("api/contacts")]
public class CreateContactController : ControllerBase
{

    [HttpPost]
    [Consumes(MediaTypeNames.Application.Json)]
    [Produces(MediaTypeNames.Application.Json)]
    public ActionResult create(CreateContactRequest createContactRequest)
    {
        return StatusCode(200, createContactRequest);
    }
}