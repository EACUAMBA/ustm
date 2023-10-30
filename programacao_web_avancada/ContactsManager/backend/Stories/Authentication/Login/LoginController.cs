using System.IdentityModel.Tokens.Jwt;
using System.Text;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;

namespace Stories.Authentication.Login;

[ApiController]
[Route("api/authentication/login")]
public class LoginController : ControllerBase
{

    private IConfiguration _iConfiguration;
    public LoginController(IConfiguration configuration)
    {
        this._iConfiguration = configuration;
    }

    [HttpPost]
    public IActionResult Login(LoginRequest loginRequest)
    {
        string email = "edilsoncuamba@gmail.com";
        string password = "P4ssw0rd";

        if (!email.Equals(loginRequest.email) && !password.Equals(loginRequest.password))
        {
            return StatusCode(401, "Credentials incorrect!");
        }

        var jwtKey = this._iConfiguration["Jwt:Key"];
        var jwtKeyAsUTF8Bytes = Encoding.UTF8.GetBytes(jwtKey);
        var jwtKeyAsSymmetricSecurityKey = new SymmetricSecurityKey(jwtKeyAsUTF8Bytes);
        var jwtKeyAsSigningCredentials = new SigningCredentials(jwtKeyAsSymmetricSecurityKey, SecurityAlgorithms.HmacSha256);

        var jwtSecutiryToken = new JwtSecurityToken(
            this._iConfiguration["Jwt:Issuer"],
            this._iConfiguration["Jwt:Issuer"],
            null,
            expires: DateTime.Now.AddMinutes(30),
            signingCredentials: jwtKeyAsSigningCredentials
        );

        var token = new JwtSecurityTokenHandler().WriteToken(jwtSecutiryToken);

        Response.Headers.Authorization = $"Bearer {token}";
        return StatusCode(200, "Your token is at the headers. Authorization: Bearer abc...xyz");
    }
}