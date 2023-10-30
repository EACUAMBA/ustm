using backend.Stories.Contacts.Create;
using DbContexts;
using Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Middlewares;

namespace backend.Stories.Contacts.Delete;

public class DeleteContactService
{

    private readonly SqliteDbContext _sqliteDbContext;

    public DeleteContactService(SqliteDbContext sqliteDbContext){
        this._sqliteDbContext = sqliteDbContext;
    }
    public void Delete(int id)
    {

        List<Contact> contactListWithSameId = this._sqliteDbContext.Contacts.Where(c => c.Id.Equals(id)).ToList();

        if (contactListWithSameId.IsNullOrEmpty())
        {
            throw new ContactsManagerException(404, $"Contacto com o id {id} não foi encontrado!");
        }
        Contact contactToUpdate = contactListWithSameId.First();
        

        this._sqliteDbContext.Contacts.Remove(contactToUpdate);
        this._sqliteDbContext.SaveChanges();
    }
}