using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

public class LoginRequest{
    [EmailAddress]
    public string email {get; set;} = string.Empty;

    [PasswordPropertyText]
    public string password {get; set;} = string.Empty;
}