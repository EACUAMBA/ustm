using AutoMapper;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using WebApiAuthenticationAndAuthorizationWithJwt.Entities;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Stories.Users.UserRegistration;

[ApiController]
[Route("api/users/register")]
public class UserRegistrationController : ControllerBase
{
    private readonly IConfigurationSection _jwtSettings;
    private readonly IMapper _mapper;
    private readonly UserManager<User> _userManager;

    public UserRegistrationController(IMapper mapper, UserManager<User> userManager, IConfiguration configuration)
    {
        _mapper = mapper;
        _userManager = userManager;
        _jwtSettings = configuration.GetSection("JwtSettings");
    }

    [HttpPost]
    public async Task<ActionResult> Register(UserRegistrationModel userModel)
    {
        var user = _mapper.Map<User>(userModel);
        var result = await _userManager.CreateAsync(user, userModel.Password);
        if (!result.Succeeded)
        {
            return BadRequest(result.Errors);
        }

        await _userManager.AddToRoleAsync(user, userModel.Role);
        return StatusCode(201);
    }
}