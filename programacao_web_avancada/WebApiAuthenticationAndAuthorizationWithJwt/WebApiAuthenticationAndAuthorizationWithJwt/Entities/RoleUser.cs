using Microsoft.AspNetCore.Identity;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Entities;

public class RoleUser : IdentityUserRole<string>
{
    public string? RoleId { get; set; }
    public string? UserId { get; set; }
}