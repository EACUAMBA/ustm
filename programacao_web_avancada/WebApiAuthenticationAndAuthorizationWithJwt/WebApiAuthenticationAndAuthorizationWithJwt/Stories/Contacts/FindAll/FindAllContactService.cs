using Entities;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.FindAll;

public class FindAllContactService
{
    private readonly SqliteDbContext _sqliteDbContext;

    public FindAllContactService(SqliteDbContext sqliteDbContext)
    {
        this._sqliteDbContext = sqliteDbContext;
    }

    public List<FindAllContactResponse> FindAll()
    {
        List<Contact> contactList = this._sqliteDbContext.Contacts.ToList();

        return contactList.Select(contact =>
        {
            return new FindAllContactResponse()
            {
                Id = contact.Id!,
                Name = contact.Name,
                Email = contact.Email,
                PhoneNumber = contact.PhoneNumber
            };
        }).ToList();
    }
}