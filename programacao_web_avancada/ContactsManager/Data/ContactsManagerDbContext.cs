using Microsoft.EntityFrameworkCore;

public class ContactsManagerDbContext : DbContext
{
    public ContactsManagerDbContext(
        DbContextOptions<ContactsManagerDbContext> options
        ) : base(options)
    {
    }

    public DbSet<ContactsManager.Models.Contact> Contact { get; set; } = default!;
    //In Entity Framework terminology, an entity set typically corresponds to a database table. An entity corresponds to a row in the table.
}