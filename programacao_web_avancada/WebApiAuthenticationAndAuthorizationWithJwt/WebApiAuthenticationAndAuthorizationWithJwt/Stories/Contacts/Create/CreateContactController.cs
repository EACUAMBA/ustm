using System.Net.Mime;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.Create;

[Authorize(Roles = "Administrator")]
[Authorize]
[ApiController]
[Route("api/contacts")]
public class CreateContactController : ControllerBase
{
    private readonly CreateContactService _createContactService;

    public CreateContactController(CreateContactService createContactService)
    {
        this._createContactService = createContactService;
    }

    [HttpPost]
    [Consumes(MediaTypeNames.Application.Json)]
    [Produces(MediaTypeNames.Application.Json)]
    public ActionResult Create(CreateContactRequest createContactRequest)
    {
        return StatusCode(200, this._createContactService.Create(createContactRequest));
    }
}