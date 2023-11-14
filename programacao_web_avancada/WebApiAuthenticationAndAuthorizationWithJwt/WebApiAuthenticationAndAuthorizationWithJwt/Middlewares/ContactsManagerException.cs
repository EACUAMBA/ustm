namespace WebApiAuthenticationAndAuthorizationWithJwt.Middlewares
{
    public class ContactsManagerException : SystemException
    {
        private readonly string _cause;
        private readonly int _statusCodes;

        public ContactsManagerException(int statusCodes, string cause)
        {
            this._statusCodes = statusCodes;
            this._cause = cause;
        }


        public int StatusCodes
        {
            get { return this._statusCodes; }
        }

        public string Cause
        {
            get { return this._cause; }
        }
    }
}