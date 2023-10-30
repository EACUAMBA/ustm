using System.Text;
using DbContexts;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
var builder = WebApplication.CreateBuilder(args);

//Getting values from appsettings.json file
var jwtIssuer = builder.Configuration.GetSection("Jwt:Issuer").Get<string>();
var jwtKey = builder.Configuration.GetSection("Jwt:Key").Get<string>();
var jwtKeyAsUTF8Bytes = Encoding.UTF8.GetBytes(jwtKey);
var jwtKeyAsSymmetricSecurityKey = new SymmetricSecurityKey(jwtKeyAsUTF8Bytes);

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
.AddJwtBearer((options) =>
{
    options.TokenValidationParameters = new Microsoft.IdentityModel.Tokens.TokenValidationParameters
    {
        ValidateIssuer = true,
        ValidateAudience = false,
        ValidateLifetime = true,
        ValidateIssuerSigningKey = true,

        ValidIssuer = jwtIssuer,
        IssuerSigningKey = jwtKeyAsSymmetricSecurityKey
    };
});

var options = new DbContextOptionsBuilder<SqliteDbContext>()
.UseSqlite(connectionString: builder.Configuration.GetSection("ConnectionStrings:sqlite").Get<string>())
.Options;

builder.Services.AddSingleton(new SqliteDbContext(options));
builder.Services.AddControllers();
var app = builder.Build();
app.MapControllers();
app.Run();
