using System.Net.Mime;
using backend.Stories.Contacts.Update;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.Stories.Contacts.FindAll;

[Authorize]
[ApiController]
[Route("api/contacts")]
public class FindAllContactController : ControllerBase
{

    private readonly FindAllContactService _findAllContactService;

    public FindAllContactController(FindAllContactService findAllContactService){
        this._findAllContactService = findAllContactService;
    }

    [HttpGet]
    [Consumes(MediaTypeNames.Application.Json)]
    [Produces(MediaTypeNames.Application.Json)]
    public ActionResult FindAll()
    {
        List<FindAllContactResponse> findAllContactResponseList = this._findAllContactService.FindAll();
        return StatusCode(200, findAllContactResponseList);
    }
}