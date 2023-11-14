using System.Net.Mime;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.Update;

[Authorize(Roles = "Administrator")]
[Authorize]
[ApiController]
[Route("api/contacts/{id:int}")]
public class UpdateContactController : ControllerBase
{
    private readonly UpdateContactService _updateContactService;

    public UpdateContactController(UpdateContactService updateContactService)
    {
        this._updateContactService = updateContactService;
    }

    [HttpPut]
    [Consumes(MediaTypeNames.Application.Json)]
    [Produces(MediaTypeNames.Application.Json)]
    public ActionResult Update(Int32 id, UpdateContactRequest updateContactRequest)
    {
        return StatusCode(200, this._updateContactService.Update(id, updateContactRequest));
    }
}