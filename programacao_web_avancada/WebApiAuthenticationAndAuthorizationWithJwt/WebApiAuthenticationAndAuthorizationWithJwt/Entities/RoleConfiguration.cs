using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace WebApiAuthenticationAndAuthorizationWithJwt.Entities;

public class RoleConfiguration : IEntityTypeConfiguration<Role>
{
    public void Configure(EntityTypeBuilder<Role> builder)
    {
        builder.HasData(
            new Role()
            {
                Name = "Seller",
                NormalizedName = "SELLER"
            },
            new Role()
            {
                Name = "Administrator",
                NormalizedName = "ADMINISTRATOR"
            });
    }
}