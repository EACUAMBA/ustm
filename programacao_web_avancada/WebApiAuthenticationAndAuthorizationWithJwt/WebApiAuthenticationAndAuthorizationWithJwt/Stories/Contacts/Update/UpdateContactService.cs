using Entities;
using Microsoft.IdentityModel.Tokens;
using WebApiAuthenticationAndAuthorizationWithJwt.Middlewares;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.Update;

public class UpdateContactService
{
    private readonly SqliteDbContext _sqliteDbContext;

    public UpdateContactService(SqliteDbContext sqliteDbContext)
    {
        this._sqliteDbContext = sqliteDbContext;
    }

    public int? Update(Int32 id, UpdateContactRequest updateContactRequest)
    {
        List<Contact> contactListWithSameId = this._sqliteDbContext.Contacts.Where(c => c.Id.Equals(id)).ToList();

        if (contactListWithSameId.IsNullOrEmpty())
        {
            throw new ContactsManagerException(404, $"Contacto com o id {id} não foi encontrado!");
        }

        Contact contactToUpdate = contactListWithSameId.First();


        List<Contact> contactListWithSamePhoneNumber = this._sqliteDbContext.Contacts
            .Where(c => c.PhoneNumber.Equals(updateContactRequest.PhoneNumber)).ToList();
        if (!contactListWithSamePhoneNumber.IsNullOrEmpty())
        {
            Contact contactWithSamePhoneNumber = contactListWithSamePhoneNumber.First();
            if (!contactWithSamePhoneNumber.Id.Equals(id))
            {
                throw new ContactsManagerException(409,
                    $"Contacto com o número ({contactWithSamePhoneNumber.PhoneNumber}) já existe, é do ({contactWithSamePhoneNumber.Name}) com email ({contactWithSamePhoneNumber.Email}).");
            }
        }

        contactToUpdate.Name = updateContactRequest.Name;
        contactToUpdate.Email = updateContactRequest.Email;
        contactToUpdate.PhoneNumber = updateContactRequest.PhoneNumber;

        this._sqliteDbContext.Contacts.Update(contactToUpdate);
        this._sqliteDbContext.SaveChanges();

        return contactToUpdate.Id;
    }
}