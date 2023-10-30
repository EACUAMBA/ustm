using System.Text;
using backend.Stories.Contacts.Create;
using backend.Stories.Contacts.Delete;
using backend.Stories.Contacts.FindAll;
using backend.Stories.Contacts.Update;
using DbContexts;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Diagnostics;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Middlewares;

var builder = WebApplication.CreateBuilder(args);

//Getting values from appsettings.json file
var jwtIssuer = builder.Configuration.GetSection("Jwt:Issuer").Get<string>();
string jwtKey = builder.Configuration.GetSection("Jwt:Key").Get<string>()!;
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

//create contact
builder.Services.AddSingleton<CreateContactService>();
builder.Services.AddSingleton<UpdateContactService>();
builder.Services.AddSingleton<FindAllContactService>();
builder.Services.AddSingleton<DeleteContactService>();

builder.Services.AddControllers();
var app = builder.Build();

app.UseMiddleware<Middlewares.ExceptionHandlerMiddleware>();

app.MapControllers();
app.Run();
