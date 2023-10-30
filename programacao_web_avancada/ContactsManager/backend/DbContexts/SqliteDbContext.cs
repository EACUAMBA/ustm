using Entities;
using Microsoft.EntityFrameworkCore;

namespace DbContexts;

public class SqliteDbContext:DbContext{
    public SqliteDbContext(DbContextOptions<SqliteDbContext> dbContextOptions): base(dbContextOptions){

    }

    public DbSet<Contact> Contacts {get; private set;}
}