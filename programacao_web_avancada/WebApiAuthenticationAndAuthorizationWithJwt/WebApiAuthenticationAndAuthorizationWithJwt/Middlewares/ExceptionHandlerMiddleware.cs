namespace WebApiAuthenticationAndAuthorizationWithJwt.Middlewares;

public class ExceptionHandlerMiddleware
{
    private readonly RequestDelegate _requestDelegate;

    public ExceptionHandlerMiddleware(RequestDelegate next)
    {
        this._requestDelegate = next;
    }

    public async Task InvokeAsync(HttpContext httpContext)
    {
        try
        {
            await this._requestDelegate(httpContext);
        }
        catch (Exception exception)
        {
            await HandleExceptionAsync(httpContext, exception);
        }
    }

    private async Task HandleExceptionAsync(HttpContext httpContext, Exception exception)
    {
        if (exception is ContactsManagerException contactsManagerException)
        {
            httpContext.Response.StatusCode = contactsManagerException.StatusCodes;
            await httpContext.Response.WriteAsync(contactsManagerException.Cause);
        }
        else
        {
            httpContext.Response.StatusCode = StatusCodes.Status500InternalServerError;
            await httpContext.Response.WriteAsync(exception.Message);
        }
    }
}