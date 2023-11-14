using Entities;
using Microsoft.IdentityModel.Tokens;
using WebApiAuthenticationAndAuthorizationWithJwt.Middlewares;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.Create;

public class CreateContactService
{
    private readonly SqliteDbContext _sqliteDbContext;

    public CreateContactService(SqliteDbContext sqliteDbContext)
    {
        this._sqliteDbContext = sqliteDbContext;
    }

    public int? Create(CreateContactRequest createContactRequest)
    {
        List<Contact> contactListWithSamePhoneNumber = this._sqliteDbContext.Contacts
            .Where(c => c.PhoneNumber.Equals(createContactRequest.PhoneNumber)).ToList();

        if (!contactListWithSamePhoneNumber.IsNullOrEmpty())
        {
            Contact contactWithSamePhoneNumber = contactListWithSamePhoneNumber.First();
            throw new ContactsManagerException(409,
                $"Contacto com o número ({contactWithSamePhoneNumber.PhoneNumber}) já existe, é do ({contactWithSamePhoneNumber.Name}) com email ({contactWithSamePhoneNumber.Email}).");
        }

        Contact contact = new Contact
        {
            Name = createContactRequest.Name,
            Email = createContactRequest.Email,
            PhoneNumber = createContactRequest.PhoneNumber
        };

        this._sqliteDbContext.Contacts.Add(contact);
        this._sqliteDbContext.SaveChanges();

        return contact.Id;
    }
}