using System.Text;
using AuthDemo.Models;
using AutoMapper;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using WebApiAuthenticationAndAuthorizationWithJwt;
using WebApiAuthenticationAndAuthorizationWithJwt.Entities;
using WebApiAuthenticationAndAuthorizationWithJwt.Middlewares;
using WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.Create;
using WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.Delete;
using WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.FindAll;
using WebApiAuthenticationAndAuthorizationWithJwt.Stories.Contacts.Update;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.


// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

//Getting values from appsettings.json file
var jwtIssuer = builder.Configuration.GetSection("Jwt:Issuer").Get<string>();
string jwtKey = builder.Configuration.GetSection("Jwt:Key").Get<string>()!;
var jwtKeyAsUTF8Bytes = Encoding.UTF8.GetBytes(jwtKey);
var jwtKeyAsSymmetricSecurityKey = new SymmetricSecurityKey(jwtKeyAsUTF8Bytes);

//DB
var rootPathASString = builder.Environment.ContentRootPath;
var options = new DbContextOptionsBuilder<SqliteDbContext>()
    .UseSqlite(connectionString: "Data source =" + rootPathASString + "/WebApiAuthenticationAndAuthorizationWithJwt.db")
    .Options;
builder.Services.AddSingleton(new SqliteDbContext(options));

//AuthenticationAndAuthorization
builder.Services.AddIdentity<User, Role>().AddEntityFrameworkStores<SqliteDbContext>();
builder.Services.Configure<IdentityOptions>(options =>
{
    // Password settings
    options.Password.RequireDigit = false;
    options.Password.RequireLowercase = false;
    options.Password.RequireUppercase = false;
    options.Password.RequireNonAlphanumeric = false;
    options.Password.RequiredLength = 4;

    // Lockout settings
    options.Lockout.DefaultLockoutTimeSpan = TimeSpan.FromMinutes(5);
    options.Lockout.MaxFailedAccessAttempts = 5;
    options.Lockout.AllowedForNewUsers = true;

    // User settings
    options.User.RequireUniqueEmail = true;
});

// Auto Mapper Configurations
var mappingConfig = new MapperConfiguration(mc => { mc.AddProfile(new MappingProfile()); });
IMapper mapper = mappingConfig.CreateMapper();
builder.Services.AddSingleton(mapper);

builder.Services.AddAuthentication(options =>
{
    options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
}).AddJwtBearer(options =>
{
    options.TokenValidationParameters = new TokenValidationParameters
    {
        ValidateIssuer = true,
        ValidateAudience = false,
        ValidateLifetime = true,
        ValidateIssuerSigningKey = true,

        ValidIssuer = jwtIssuer,
        IssuerSigningKey = jwtKeyAsSymmetricSecurityKey
    };
});

//create contact
builder.Services.AddSingleton<CreateContactService>();
builder.Services.AddSingleton<UpdateContactService>();
builder.Services.AddSingleton<FindAllContactService>();
builder.Services.AddSingleton<DeleteContactService>();

builder.Services.AddControllers();
var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

//app.UseHttpsRedirection();

// app.UseAuthentication();
// app.UseAuthorization();

app.MapControllers();

app.UseMiddleware<ExceptionHandlerMiddleware>();

app.Run();