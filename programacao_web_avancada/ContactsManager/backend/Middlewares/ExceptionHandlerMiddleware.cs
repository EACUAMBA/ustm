namespace Middlewares;

using System.Text;
using Middlewares;

public class ExceptionHandlerMiddleware
{
    private readonly RequestDelegate _requestDelegate;

    public ExceptionHandlerMiddleware(RequestDelegate requestDelegate)
    {
        this._requestDelegate = requestDelegate;
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

            var causeAsByteArray = Encoding.UTF8.GetBytes(contactsManagerException.Cause);

            using (MemoryStream memoryStream = new MemoryStream(causeAsByteArray))
            {
                httpContext.Response.Body = memoryStream;
            }
        }else{
            httpContext.Response.StatusCode = StatusCodes.Status500InternalServerError;
            httpContext.Response.WriteAsync(exception.Message);
        }
    }
}