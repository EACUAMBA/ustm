using Microsoft.EntityFrameworkCore;

namespace Database;

public class SqliteDBContext:DbContext{
    public SqliteDBContext(DbContextOptions<SqliteDBContext> dbContextOptions): base(dbContextOptions){

    }
}