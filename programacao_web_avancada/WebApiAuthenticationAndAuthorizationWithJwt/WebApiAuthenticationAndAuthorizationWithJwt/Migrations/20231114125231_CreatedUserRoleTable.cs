using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace WebApiAuthenticationAndAuthorizationWithJwt.Migrations
{
    /// <inheritdoc />
    public partial class CreatedUserRoleTable : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "4b5af778-88e8-4c12-a23c-ad95bd9bca78");

            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "93344b48-5cba-4a34-a4e6-f81bc30c151f");

            migrationBuilder.InsertData(
                table: "AspNetRoles",
                columns: new[] { "Id", "ConcurrencyStamp", "Name", "NormalizedName" },
                values: new object[,]
                {
                    { "674e4615-207e-488c-9d7b-cdf3be49967b", null, "Administrator", "ADMINISTRATOR" },
                    { "f51ad0b2-417d-40a3-86fc-e22c6de39eee", null, "Seller", "SELLER" }
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "674e4615-207e-488c-9d7b-cdf3be49967b");

            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "f51ad0b2-417d-40a3-86fc-e22c6de39eee");

            migrationBuilder.InsertData(
                table: "AspNetRoles",
                columns: new[] { "Id", "ConcurrencyStamp", "Name", "NormalizedName" },
                values: new object[,]
                {
                    { "4b5af778-88e8-4c12-a23c-ad95bd9bca78", null, "Administrator", "ADMINISTRATOR" },
                    { "93344b48-5cba-4a34-a4e6-f81bc30c151f", null, "Seller", "SELLER" }
                });
        }
    }
}
