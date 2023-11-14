using AutoMapper;
using WebApiAuthenticationAndAuthorizationWithJwt.Entities;
using WebApiAuthenticationAndAuthorizationWithJwt.Stories.Users.UserRegistration;

namespace AuthDemo.Models
{
    public class MappingProfile : Profile
    {
        public MappingProfile()
        {
            CreateMap<UserRegistrationModel, User>()
                .ForMember(u => u.UserName, opt => opt.MapFrom(x => x.Email));
        }
    }
}