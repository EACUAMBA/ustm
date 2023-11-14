using System.ComponentModel.DataAnnotations;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.Update;

public class UpdateContactRequest
{
    [Required] public string Name { get; set; } = string.Empty;

    public string? Email { get; set; }

    [Required] public string PhoneNumber { get; set; } = string.Empty;
}