using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using WebApiAuthenticationAndAuthorizationWithJwt.Entities;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Stories.Users.UserLogin;

[ApiController]
[Route("api/users/login")]
public class UserLoginController : ControllerBase
{
    private readonly IConfigurationSection _jwtSettings;
    private readonly UserManager<User> _userManager;

    public UserLoginController(UserManager<User> userManager, IConfiguration configuration)
    {
        _userManager = userManager;
        _jwtSettings = configuration.GetSection("Jwt");
    }

    [HttpPost]
    public async Task<IActionResult> Login(UserLoginModel userModel)
    {
        var user = await _userManager.FindByEmailAsync(userModel.Email);
        if (user != null && await _userManager.CheckPasswordAsync(user, userModel.Password))
        {
            var jwtSecurityToken = this.GenerateTokenOptions(GetSigningCredentials(), await GetClaims(user: user));
            var token = new JwtSecurityTokenHandler().WriteToken(jwtSecurityToken);

            Response.Headers.Authorization = $"Bearer {token}";
            return StatusCode(200, $"Your token is at the headers. Authorization: Bearer {token}");
        }

        return Unauthorized("Invalid Authentication");
    }

    private SigningCredentials GetSigningCredentials()
    {
        var jwtKey = this._jwtSettings.GetSection("Key").Value;
        var jwtKeyAsUTF8Bytes = Encoding.UTF8.GetBytes(jwtKey);
        var jwtKeyAsSymmetricSecurityKey = new SymmetricSecurityKey(jwtKeyAsUTF8Bytes);
        var jwtKeyAsSigningCredentials =
            new SigningCredentials(jwtKeyAsSymmetricSecurityKey, SecurityAlgorithms.HmacSha256);
        return jwtKeyAsSigningCredentials;
    }

    private JwtSecurityToken GenerateTokenOptions(SigningCredentials signingCredentials, List<Claim> claims)
    {
        var tokenOptions = new JwtSecurityToken(
            this._jwtSettings.GetSection("Issuer").Value,
            this._jwtSettings.GetSection("Issuer").Value,
            claims: claims,
            expires: DateTime.Now.AddDays(2),
            signingCredentials: signingCredentials);
        return tokenOptions;
    }

    private async Task<List<Claim>> GetClaims(User user)
    {
        var claims = new List<Claim>
        {
            new Claim(ClaimTypes.Name, user.Email)
        };
        var roles = await _userManager.GetRolesAsync(user);
        foreach (var role in roles)
        {
            claims.Add(new Claim(ClaimTypes.Role, role));
        }

        return claims;
    }
}